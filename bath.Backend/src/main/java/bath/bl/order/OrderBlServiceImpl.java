package bath.bl.order;

import bath.blservice.order.OrderBlService;
import bath.dataservice.coupon.CouponDataService;
import bath.dataservice.groupon.GrouponDataService;
import bath.dataservice.order.OrderDataService;
import bath.dataservice.user.UserDataService;
import bath.entity.coupon.Coupon;
import bath.entity.order.Order;
import bath.entity.order.OrderGrouponItem;
import bath.entity.user.User;
import bath.exception.NotExistException;
import bath.exception.SystemException;
import bath.response.order.*;
import bath.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class OrderBlServiceImpl implements OrderBlService {
    private final OrderDataService orderDataService;
    private final UserDataService userDataService;
    private final CouponDataService couponDataService;
    private final GrouponDataService grouponDataService;
    @Autowired
    public OrderBlServiceImpl(OrderDataService orderDataService, UserDataService userDataService,CouponDataService couponDataService,GrouponDataService grouponDataService){
        this.orderDataService=orderDataService;
        this.userDataService=userDataService;
        this.couponDataService=couponDataService;
        this.grouponDataService=grouponDataService;
    }

    @Override
    public SettleResponse settle(List<OrderGrouponItem> orderItems) throws NotExistException {
        double total=0;
        for(OrderGrouponItem temp:orderItems){
            total+=temp.getAmount()*temp.getPrice();
        }
        SettleResponse order=new SettleResponse();
        order.setTotal(total);
        order.setOrderItems(orderItems);
        return order;
    }

    @Value(value = "${wechat.order_url}")
    private String ORDER_URL;
    @Value(value = "${wechat.id}")
    private String APP_ID;
    @Value(value = "${wechat.mch_id}")
    private String MCH_ID;
    @Value(value = "${wechat.body}")
    private String BODY;
    @Value(value = "${wechat.device_info}")
    private String DEVICE_INFO;
    @Value(value = "${wechat.notify_url}")
    private String NOTIFY_URL;
    @Value(value = "${wechat.trade_type}")
    private String TRADE_TYPE;
    @Value(value = "${wechat.api_key}")
    private String API_KEY;
    @Value(value = "${wechat.sign_type}")
    private String SIGN_TYPE;

    @Override
    public WxPayResponse createOrder(String openid, List<OrderGrouponItem> orderItems, double total/*,int integration,double coupon,double actualCost*/) throws NotExistException {
        User user=userDataService.findByOpenid(openid);
        Order order=new Order(user,OrderUUIDUtil.generateUUID(),orderItems,total/*,integration,coupon,actualCost*/);
        orderDataService.add(order);

        SortedMap<String, String> packageParams = new TreeMap<>();
        packageParams.put("appid", APP_ID);
        packageParams.put("mch_id", MCH_ID);
        packageParams.put("nonce_str", RandomUtil.generateNonceStr());//时间戳
        packageParams.put("body", BODY);//支付主体
        packageParams.put("out_trade_no", order.getId() + "");//BuyCredit表编号
        packageParams.put("total_fee", order.getTotal() + "");//人民币价格
        packageParams.put("notify_url", NOTIFY_URL);//支付返回地址，服务器收到之后将订单状态从"waiting"改为"finished"或"failed"
        packageParams.put("trade_type", TRADE_TYPE);//这个api有，固定的
        packageParams.put("openid", openid);//openid
        //获取sign
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, API_KEY);//最后这个是自己设置的32位密钥
        packageParams.put("sign", sign);

        //发送请求，得到含有prepay_id的XML
        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        String resXml = null;
        try {
            resXml = HttpUtil.postData("https://api.mch.weixin.qq.com/pay/unifiedorder", requestXML);
        } catch (SystemException e) {
            order.setOrderState("stopped");
            orderDataService.update(order);
            e.printStackTrace();
        }

        //根据微信回复填写给小程序的回复
        String waitingTimeStamp = String.valueOf(System.currentTimeMillis()); //回复给微信小程序的时间戳
        String nonceStr = null;
        try {
            nonceStr = XMLUtil.parserXmlToGetNonceStr(resXml);
        } catch (SystemException e) {
            order.setOrderState("stopped");
            orderDataService.update(order);
            e.printStackTrace();
        }
        String packageContent = null;
        try {
            packageContent = "prepay_id=" + XMLUtil.parserXmlToGetPrepayId(resXml);
        } catch (SystemException e) {
            order.setOrderState("stopped");
            orderDataService.update(order);
            e.printStackTrace();
        }
        String signType = SIGN_TYPE;
        String apiKey = API_KEY;
        SortedMap<String, String> sortedMap = new TreeMap<>();
        sortedMap.put("appId", APP_ID);
        sortedMap.put("timeStamp", waitingTimeStamp);
        sortedMap.put("nonceStr", nonceStr);
        sortedMap.put("package", packageContent);
        sortedMap.put("signType", signType);
        String paySign = PayCommonUtil.createSign("UTF-8", sortedMap, apiKey);

        //根据数据库中buyCredit的记录
        order.setOrderState("waiting");
        order.setWaitingTimeStamp(waitingTimeStamp);
        orderDataService.update(order);

        //自动取消超时订单
        System.out.println("start waiting...");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("scheduled to checking");
                if (order.getOrderState().equals("init") || order.getOrderState().equals("waiting")) {
                    order.setOrderState("cancelled");
                    order.setFinalTimeStamp(String.valueOf(System.currentTimeMillis()));
                    try {
                        orderDataService.update(order);
                    } catch (NotExistException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 15*60*1000); //在15分钟后订单若仍为init或waiting则自动取消
        return new WxPayResponse(new WxPayItem(order.getId(),waitingTimeStamp,nonceStr,packageContent,signType,paySign));
    }

    @Override
    public String getWxPayResult(HttpServletRequest request) {
        System.out.println("Wx notification arrived");
        try {
            InputStream inStream = request.getInputStream();
            int _buffer_size = 1024;
            if (inStream != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[_buffer_size];
                int count = -1;
                while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
                    outStream.write(tempBytes, 0, count);
                }
                tempBytes = null;
                outStream.flush();

                String resultXML = new String(outStream.toByteArray(), StandardCharsets.UTF_8); //将流转换成字符串
                SortedMap<Object, Object> sortedMap = XMLUtil.getSortedMapFromXML(resultXML);
                if (PayCommonUtil.isTenpaySign("UTF-8", sortedMap, API_KEY)) {
                    Order order = orderDataService.findById((String)sortedMap.get("out_trade_no"));
                    if (sortedMap.get("return_code").equals("SUCCESS")) {
                        if (sortedMap.get("result_code").equals("SUCCESS")) {
                            if (order.getOrderState().equals("waiting")) {
                                order.setOrderState("finished");
                                order.setFinalTimeStamp(String.valueOf(System.currentTimeMillis()));
                                orderDataService.update(order);
                                User user=order.getUser();
                                for(OrderGrouponItem temp:order.getOrderItems()){
                                    for(int i=1;i<=temp.getAmount();i++){
                                        Coupon coupon=new Coupon(grouponDataService.findById(temp.getGrouponId()),user);
                                        couponDataService.add(coupon);
                                    }
                                }

                            } else {
                                System.err.println("订单状态已更新为finished！");
                            }
                        } else {
                            if (order.getOrderState().equals("waiting")) {
                                order.setOrderState("failed");
                                order.setFinalTimeStamp(String.valueOf(System.currentTimeMillis()));
                                orderDataService.update(order);
                            } else {
                                System.err.println("订单状态已更新为failed！");
                            }
                        }
                    } else {
                        throw new Exception("微信支付后台通信标识为FAIL！");
                    }
                } else {
                    throw new Exception("微信支付后台通信签名校验失败！");
                }
            }
            //通知微信支付系统接收到信息
            return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //如果失败返回错误，微信会再次发送支付信息
            return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[ERROR]]></return_msg></xml>";
        }
    }

    @Override
    public OrderResponse findOrderById(String orderId) throws NotExistException {
        return new OrderResponse(orderDataService.findById(orderId));
    }

    @Override
    public OrderListResponse findByOpenid(String openid) throws NotExistException {
        List<Order> orders=orderDataService.findByUser(userDataService.findByOpenid(openid));
        return new OrderListResponse(orders);
    }

    @Override
    public OrderListResponse findByOpenidAndState(String openid, String state) throws NotExistException {
        User user=userDataService.findByOpenid(openid);
        return new OrderListResponse(orderDataService.findByUserAndState(user,state));
    }

    @Override
    public OrderListResponse getAll() {
        return new OrderListResponse(orderDataService.getAll());
    }
}
