package bath.springcontroller.coupon;

import bath.blservice.coupon.CouponBlService;
import bath.exception.NotExistException;
import bath.response.Response;
import bath.response.WrongResponse;
import bath.response.coupon.CouponListResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
public class CouponController {
    private final CouponBlService couponBlService;
    @Autowired
    public CouponController(CouponBlService couponBlService){
        this.couponBlService=couponBlService;
    }
    @ApiOperation(value="通过id获取优惠码",notes="通过id获取优惠码")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="优惠码编号",required = true,dataType = "String")
    })
    @RequestMapping(value="/view",method=RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CouponListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findCouponById(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(couponBlService.findCouponById(id),HttpStatus.OK);
    }

    @ApiOperation(value="通过id使用优惠码",notes="通过id使用优惠码")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="优惠码编号",required = true,dataType = "String")
    })
    @RequestMapping(value="/use",method=RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CouponListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> useCouponById(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(couponBlService.useCoupon(id),HttpStatus.OK);
    }

    @ApiOperation(value = "用户获取优惠码列表", notes = "用户获取优惠码列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户编号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/myCoupon", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CouponListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findByUser(@RequestParam(name = "openid") String openid) throws NotExistException {
        return new ResponseEntity<>(couponBlService.findByUser(openid), HttpStatus.OK);
    }

}
