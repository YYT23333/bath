package bath.springcontroller.cart;

import bath.blservice.cart.CartBlService;
import bath.exception.NotExistException;
import bath.response.AddResponse;
import bath.response.InfoResponse;
import bath.response.Response;
import bath.response.WrongResponse;
import bath.response.cart.CartItemResponse;
import bath.response.coupon.CouponListResponse;
import bath.response.order.OrderListResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/cart")
public class CartController{
    private final CartBlService cartBlService;
    @Autowired
    public CartController(CartBlService cartBlService){
        this.cartBlService=cartBlService;
    }
    @ApiOperation(value="用户添加商品到购物车",notes="用户添加商品到购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name="openid",value="用户编号",required = true,dataType = "String"),
            @ApiImplicitParam(name="grouponId",value ="团购Id",required = true,dataType = "String"),
            @ApiImplicitParam(name="amount",value="数量",required = true,dataType = "Integer")
    })
    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = AddResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addCartItem(@RequestParam(name="openid")String openid, @RequestParam(name="grouponId")String grouponId, @RequestParam(name="amount")int amount) throws NotExistException {
        return new ResponseEntity<>(cartBlService.addCartItem(openid,grouponId,amount),HttpStatus.OK);
    }

    @ApiOperation(value="用户修改购物车商品数量",notes="用户修改购物车商品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="购物车商品编号",required = true,dataType = "Integer"),
            @ApiImplicitParam(name="amount",value="数量",required = true,dataType = "Integer")
    })
    @RequestMapping(value="/update",method=RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateCartItem(@RequestParam(name="id")int id,@RequestParam(name="amount")int amount) throws NotExistException {
        return new ResponseEntity<>(cartBlService.updateCartItem(id,amount),HttpStatus.OK);
    }


    @ApiOperation(value="用户删除购物车商品",notes="用户删除购物车商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="购物车商品编号",required = true,dataType = "Integer")
    })
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteCartItem(@RequestParam(name="id")int id) throws NotExistException {
        return new ResponseEntity<>(cartBlService.deleteCartItem(id),HttpStatus.OK);
    }

    @ApiOperation(value="用户查看购物车商品详情",notes = "用户查看购物车商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="购物车商品编号",required = true,dataType = "Integer")
    })
    @RequestMapping(value="/view",method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CartItemResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findById(@RequestParam(name="id")int id) throws NotExistException {
        return new ResponseEntity<>(cartBlService.findById(id),HttpStatus.OK);
    }

    @ApiOperation(value = "用户获取购物车列表", notes = "用户获取购物车列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户编号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/myCart", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CouponListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findByUser(@RequestParam(name = "openid") String openid) throws NotExistException {
        return new ResponseEntity<>(cartBlService.findByUser(openid), HttpStatus.OK);
    }

}
