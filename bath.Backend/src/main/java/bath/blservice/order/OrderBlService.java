package bath.blservice.order;

import bath.entity.order.OrderGrouponItem;
import bath.exception.NotExistException;
import bath.response.order.OrderListResponse;
import bath.response.order.OrderResponse;
import bath.response.order.SettleResponse;
import bath.response.order.WxPayResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderBlService {
    /**
     * 结算
     * @param orderItems
     * @return
     */
    SettleResponse settle(List<OrderGrouponItem> orderItems)throws NotExistException;

    /**
     * 用户创建订单
     * @param openid
     * @param orderItems
     * @return
     */
    WxPayResponse createOrder(String openid, List<OrderGrouponItem> orderItems, double total/*,int integration,double coupon,double actualCost*/)throws NotExistException;

    /**
     * 获得支付结果
     * @param request
     * @return 是否成功
     */
    String getWxPayResult(HttpServletRequest request);

    /**
     * 根据订单编号查找订单
     * @param orderId
     * @return 订单
     */
    OrderResponse findOrderById(String orderId)throws NotExistException;

    /**
     * 根据用户openid查找订单
     * @param openid
     * @return 订单列表
     */
    OrderListResponse findByOpenid(String openid) throws NotExistException;

    /**
     * 根据用户和订单状态查找订单
     * @param openid
     * @param state
     * @return 订单列表
     */
    OrderListResponse findByOpenidAndState(String openid,String state) throws NotExistException;

    /**
     * 获取所有订单
     * @param
     * @return 订单列表
     */
    OrderListResponse getAll();


}
