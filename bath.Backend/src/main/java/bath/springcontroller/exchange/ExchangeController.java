package bath.springcontroller.exchange;

import bath.blservice.exchange.ExchangeBlService;
import bath.exception.IntegralDeficiencyException;
import bath.exception.LowStocksException;
import bath.exception.NotExistException;
import bath.response.InfoResponse;
import bath.response.Response;
import bath.response.WrongResponse;
import bath.response.exchangeRecords.ExchangeRecordListResponse;
import bath.response.exchangeRecords.ExchangeRecordResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    private final ExchangeBlService exchangeBlService;
    @Autowired
    public ExchangeController(ExchangeBlService exchangeBlService){
        this.exchangeBlService=exchangeBlService;
    }
    @ApiOperation(value="用户使用积分兑换商品",notes="用户使用积分兑换商品")
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> exchange(@RequestParam(name="openid")String openid, @RequestParam("grouponId")String grouponId) throws NotExistException, LowStocksException, IntegralDeficiencyException {
        return new ResponseEntity<>(exchangeBlService.exchange(openid,grouponId),HttpStatus.OK);
    }

    @ApiOperation(value="获得所有积分兑换记录",notes="获得所有积分兑换记录")
    @RequestMapping(value = "/record/all",method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ExchangeRecordListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAllExchangeRecord(){
        return new ResponseEntity<>(exchangeBlService.getAll(),HttpStatus.OK);
    }

    @ApiOperation(value="通过id查找积分兑换记录",notes="通过id查找积分兑换记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="兑换记录编号",required = true,dataType = "Integer")
    })
    @RequestMapping(value = "/record/view",method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ExchangeRecordResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getOrderById(@RequestParam(name="id")int id) throws NotExistException {
        return new ResponseEntity<>(exchangeBlService.findById(id),HttpStatus.OK);
    }
    @ApiOperation(value = "用户通过openid查看自己的所有积分兑换记录", notes = "用户通过openid查看自己的所有积分兑换记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户编号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/myExchangeRecord", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ExchangeRecordListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findByUser(@RequestParam(name = "openid") String openid) throws NotExistException {
        return new ResponseEntity<>(exchangeBlService.findByUser(openid), HttpStatus.OK);
    }
}
