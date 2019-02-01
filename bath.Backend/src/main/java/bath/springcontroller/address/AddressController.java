package bath.springcontroller.address;

import bath.blservice.address.AddressBlService;
import bath.exception.NotExistException;
import bath.response.InfoResponse;
import bath.response.Response;
import bath.response.WrongResponse;
import bath.response.coupon.CouponListResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressBlService addressBlService;
    @Autowired
    public AddressController(AddressBlService addressBlService){
        this.addressBlService=addressBlService;
    }
    @ApiOperation(value="用户添加地址",notes="用户添加地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name="openid",value="用户编号",required = true,dataType = "String"),
            @ApiImplicitParam(name="receiver",value="收件人姓名",required = true,dataType = "String"),
            @ApiImplicitParam(name="phone",value="收件人电话",required = true,dataType = "String"),
            @ApiImplicitParam(name="zone",value="地区信息",required = true,dataType = "String"),
            @ApiImplicitParam(name="detailAddress",value="详细地址",required = true,dataType = "String"),
            @ApiImplicitParam(name="postcode",value="邮政编码",required = true,dataType = "String")
    })
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addAddress(@RequestParam(name="openid")String openid, @RequestParam(name="receiver")String receiver, @RequestParam(name="phone")String phone, @RequestParam(name="zone")String zone, @RequestParam(name="detailAddress")String detailAddress, @RequestParam(name="postcode")String postcode)throws NotExistException {
        return new ResponseEntity<>(addressBlService.addAddress(openid,receiver,phone,zone,detailAddress,postcode),HttpStatus.OK);
    }

    @ApiOperation(value="用户删除地址",notes="用户删除地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="地址编号",required = true,dataType = "Integer"),
    })
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteAddress(@RequestParam(name="id")int id)throws NotExistException{
        return new ResponseEntity<>(addressBlService.deleteAddress(id),HttpStatus.OK);
    }

    @ApiOperation(value="用户更新地址",notes="用户更新地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="地址编号",required = true,dataType = "Integer"),
            @ApiImplicitParam(name="receiver",value="收件人姓名",required = true,dataType = "String"),
            @ApiImplicitParam(name="phone",value="收件人电话",required = true,dataType = "String"),
            @ApiImplicitParam(name="zone",value="地区信息",required = true,dataType = "String"),
            @ApiImplicitParam(name="detailAddress",value="详细地址",required = true,dataType = "String"),
            @ApiImplicitParam(name="postcode",value="邮政编码",required = true,dataType = "String")
    })
    @RequestMapping(value="/update",method=RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateAddress(@RequestParam(name="id")int id,@RequestParam(name="receiver")String receiver,@RequestParam(name="phone")String phone,@RequestParam(name="zone")String zone,@RequestParam(name="detailAddress")String detailAddress,@RequestParam(name="postcode")String postcode)throws NotExistException{
        return new ResponseEntity<>(addressBlService.updateAddress(id,receiver,phone,zone,detailAddress,postcode),HttpStatus.OK);
    }

    @ApiOperation(value="通过id获取地址",notes="通过id获取地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="地址编号",required = true,dataType = "Integer")
    })
    @RequestMapping(value="/view",method=RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CouponListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findAddressById(@RequestParam(name="id")int id) throws NotExistException {
        return new ResponseEntity<>(addressBlService.findAddressById(id),HttpStatus.OK);
    }

    @ApiOperation(value = "用户获取地址列表", notes = "用户获取地址列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户编号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/myAddress", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CouponListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findByUser(@RequestParam(name = "openid") String openid) throws NotExistException {
        return new ResponseEntity<>(addressBlService.findByUser(openid), HttpStatus.OK);
    }


}
