package bath.springcontroller.order;

import bath.blservice.order.OrderBlService;
import bath.exception.IntegralDeficiencyException;
import bath.exception.LowStocksException;
import bath.exception.NotExistException;
import bath.parameters.order.OrderCreateParameters;
import bath.parameters.order.SettleParameters;
import bath.response.InfoResponse;
import bath.response.Response;
import bath.response.WrongResponse;
import bath.response.order.OrderListResponse;
import bath.response.order.OrderResponse;
import bath.response.order.SettleResponse;
import bath.response.order.WxPayResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderBlService orderBlService;
    @Autowired
    public OrderController(OrderBlService orderBlService){
        this.orderBlService=orderBlService;
    }

    @ApiOperation(value="结算",notes="结算")
    @RequestMapping(value = "/settle", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = SettleResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> settle(@RequestBody SettleParameters settleParameters) throws NotExistException {
        return new ResponseEntity<>(orderBlService.settle(settleParameters.getOrderItems()),HttpStatus.OK);
    }

    @ApiOperation(value="用户提交订单",notes="用户提交订单")
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = WxPayResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> createOrder(@RequestBody OrderCreateParameters orderCreateParameters) throws NotExistException {
        return new ResponseEntity<>(orderBlService.createOrder(orderCreateParameters.getOpenid(),orderCreateParameters.getOrderItems(),orderCreateParameters.getTotal()),HttpStatus.OK);
    }


    @ApiOperation(value="此接口用户接收微信支付后台的支付结果通知",notes="此接口用户接收微信支付后台的支付结果通知")
    @RequestMapping(value = "/getWxPayResult", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public String getWxPayResult(HttpServletRequest request){
        return orderBlService.getWxPayResult(request);
    }


    @ApiOperation(value="通过id查找订单",notes="通过id查找订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="订单编号",required = true,dataType = "String")
    })
    @RequestMapping(value = "/view",method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OrderResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getOrderById(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(orderBlService.findOrderById(id),HttpStatus.OK);
    }

    @ApiOperation(value="查看所有订单",notes="查看所有订单")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OrderListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getALL(){
        return new ResponseEntity<>(orderBlService.getAll(),HttpStatus.OK);
    }


    @ApiOperation(value = "用户通过openid查看自己的所有订单", notes = "用户通过openid查看自己的所有订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户编号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/MyOrder", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OrderListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findByUser(@RequestParam(name = "openid") String openid) throws NotExistException {
        return new ResponseEntity<>(orderBlService.findByUser(openid), HttpStatus.OK);
    }



}
