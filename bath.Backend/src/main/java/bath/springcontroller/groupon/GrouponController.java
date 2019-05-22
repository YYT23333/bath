package bath.springcontroller.groupon;

import bath.blservice.groupon.GrouponBlService;
import bath.exception.NotExistException;
import bath.response.AddResponse;
import bath.response.InfoResponse;
import bath.response.Response;
import bath.response.WrongResponse;
import bath.response.groupon.*;
import bath.util.FormatDateTime;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/groupon")
public class GrouponController {
    private final GrouponBlService grouponBlService;
    @Autowired
    public GrouponController(GrouponBlService grouponBlService){
        this.grouponBlService=grouponBlService;
    }

    @ApiOperation(value = "上传团购图片", notes = "上传团购图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "image", value = "图片", required = true, dataType = "MultipartFile")
    })
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public String uploadImage(@RequestParam("image") MultipartFile image){
        Map<String, Object> map = new HashMap<String, Object>();
        if (image.isEmpty()) {
            map.put("result", "error");
            map.put("msg", "上传文件不能为空");
            return "上传文件不能为空";
        } else {

            // 获取文件名
            String fileName = image.getOriginalFilename();
            // 获取文件后缀

            // 用uuid作为文件名，防止生成的临时文件重复
            // MultipartFile to File
            //你的业务逻辑
            int bytesum = 0;
            int byteread = 0;
            InputStream inStream = null;    //读入原文件
            try {
                inStream = image.getInputStream();
                FileOutputStream fs = new FileOutputStream(fileName);
                byte[] buffer = new byte[200000000];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;            //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            File file = new File(fileName);
            String[] temp = fileName.split("\\.");
            String thePath = "record/groupon/" + uuid + "." + temp[1];
            String path = "record/groupon/" + uuid + "." + temp[1];
            File tempfile = new File(path);
            if (tempfile.exists() && tempfile.isFile()) {
                tempfile.delete();
            }
            bytesum = 0;
            byteread = 0;
            try {
                inStream = new FileInputStream(fileName);
                FileOutputStream fs = new FileOutputStream(path);
                byte[] buffer = new byte[20000000];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;            //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            return thePath;
        }
    }

    @ApiOperation(value = "管理员新建团购", notes = "管理员新建团购")
    @RequestMapping(value = "/add", method = POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="团购名称",required = true,dataType = "String"),
            @ApiImplicitParam(name="originalPrice",value="原价",required = true,dataType = "double"),
            @ApiImplicitParam(name="price",value="售价",required = true,dataType = "double"),
            @ApiImplicitParam(name="takeEffectTime",value="生效时间",required = true,dataType = "String"),
            @ApiImplicitParam(name="loseEffectTime",value="失效时间",required = true,dataType = "String"),
            @ApiImplicitParam(name="putOnShelvesTime",value="上架时间",required = true,dataType = "String"),
            @ApiImplicitParam(name="pullOffShelvesTime",value="下架编码",required = true,dataType = "String"),
            @ApiImplicitParam(name="description",value="描述",required = true,dataType = "String"),
            @ApiImplicitParam(name="amount",value="数量",required = true,dataType = "int"),
            @ApiImplicitParam(name="type",value="类型",required = true,dataType = "String"),
            @ApiImplicitParam(name="image",value="图片地址",required = true,dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = AddResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addGroupon(@RequestParam(name="name")String name,@RequestParam(name="originalPrice")double originalPrice,@RequestParam(name="price")double price,@RequestParam(name="takeEffectTime")String takeEffectTime,@RequestParam(name="loseEffectTime")String loseEffectTime,@RequestParam(name="putOnShelvesTime")String putOnShelvesTime,@RequestParam(name="pullOffShelvesTime")String pullOffShelvesTime,@RequestParam(name="description")String description,@RequestParam(name="amount")int amount,@RequestParam(name="type")String type,@RequestParam(name="image")String image) {
        return new ResponseEntity<>(grouponBlService.addGroupon(name,originalPrice,price, FormatDateTime.fromShortDateString(takeEffectTime),FormatDateTime.fromShortDateString(loseEffectTime),FormatDateTime.fromShortDateString(putOnShelvesTime),FormatDateTime.fromShortDateString(pullOffShelvesTime),description,amount,type,image), HttpStatus.OK);
    }

    @ApiOperation(value="管理员更改团购",notes="管理员更改团购")
    @RequestMapping(value="/update",method = POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="团购id",required = true,dataType = "String"),
            @ApiImplicitParam(name="name",value="团购名称",required = true,dataType = "String"),
            @ApiImplicitParam(name="originalPrice",value="原价",required = true,dataType = "double"),
            @ApiImplicitParam(name="price",value="售价",required = true,dataType = "double"),
            @ApiImplicitParam(name="takeEffectTime",value="生效时间",required = true,dataType = "String"),
            @ApiImplicitParam(name="loseEffectTime",value="失效时间",required = true,dataType = "String"),
            @ApiImplicitParam(name="putOnShelvesTime",value="上架时间",required = true,dataType = "String"),
            @ApiImplicitParam(name="pullOffShelvesTime",value="下架编码",required = true,dataType = "String"),
            @ApiImplicitParam(name="description",value="描述",required = true,dataType = "String"),
            @ApiImplicitParam(name="amount",value="数量",required = true,dataType = "int"),
            @ApiImplicitParam(name="type",value="类型",required = true,dataType = "String"),
            @ApiImplicitParam(name="image",value="图片地址",required = true,dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateGroupon(@RequestParam(name="id")String id,@RequestParam(name="name")String name,@RequestParam(name="originalPrice")double originalPrice,@RequestParam(name="price")double price,@RequestParam(name="takeEffectTime")String takeEffectTime,@RequestParam(name="loseEffectTime")String loseEffectTime,@RequestParam(name="putOnShelvesTime")String putOnShelvesTime,@RequestParam(name="pullOffShelvesTime")String pullOffShelvesTime,@RequestParam(name="description")String description,@RequestParam(name="amount")int amount,@RequestParam(name="type")String type,@RequestParam(name="image")String image)throws NotExistException{
        return new ResponseEntity<>(grouponBlService.updateGroupon(id,name,originalPrice,price, FormatDateTime.fromShortDateString(takeEffectTime),FormatDateTime.fromShortDateString(loseEffectTime),FormatDateTime.fromShortDateString(putOnShelvesTime),FormatDateTime.fromShortDateString(pullOffShelvesTime),description,amount,type,image), HttpStatus.OK);
    }

    @ApiOperation(value="管理员删除团购",notes="管理员删除团购")
    @RequestMapping(value="/delete",method = POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "团购id", required = true, dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteGroupon(@RequestParam(name="id")String id)throws NotExistException{
        return new ResponseEntity<>(grouponBlService.deleteGroupon(id),HttpStatus.OK);
    }

    @ApiOperation(value="获取所有普通团购",notes="获取所有普通团购")
    @RequestMapping(value="/ordinary",method = GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GrouponListResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAllOrdinary()throws NotExistException{
        return new ResponseEntity<>(grouponBlService.getAllOrdinary(),HttpStatus.OK);
    }

    @ApiOperation(value="获取所有积分兑换团购",notes="获取所有积分兑换团购")
    @RequestMapping(value="/integral",method = GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GrouponListResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAllIntegral()throws NotExistException{
        return new ResponseEntity<>(grouponBlService.getAllIntegral(),HttpStatus.OK);
    }

    @ApiOperation(value="通过id获取团购",notes="通过id获取团购")
    @RequestMapping(value="/view",method = POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "团购id", required = true, dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GrouponResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getGroupon(@RequestParam(name="id")String id)throws NotExistException{
        return new ResponseEntity<>(grouponBlService.findById(id),HttpStatus.OK);
    }

    @ApiOperation(value="获取正在销售的普通团购",notes="获取正在销售的普通团购")
    @RequestMapping(value="/ordinary/available",method = GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GrouponListResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAvailableOrdinaryGroupon()throws NotExistException{
        return new ResponseEntity<>(grouponBlService.getAvailableOrdinaryGroupon(),HttpStatus.OK);
    }

    @ApiOperation(value="获取正在兑换的团购",notes="获取正在兑换的团购")
    @RequestMapping(value="/integral/available",method = GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GrouponListResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAvailableIntegralGroupon()throws NotExistException{
        return new ResponseEntity<>(grouponBlService.getAvailableIntegralGroupon(),HttpStatus.OK);
    }

    @ApiOperation(value="管理员通过id上架团购",notes="管理员通过id上架团购")
    @RequestMapping(value="/putOnShelves",method = POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "团购id", required = true, dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> putOnShelves(@RequestParam(name="id")String id)throws NotExistException{
        return new ResponseEntity<>(grouponBlService.putOnShelves(id),HttpStatus.OK);
    }

    @ApiOperation(value="管理员通过id下架团购",notes="管理员通过id下架团购")
    @RequestMapping(value="/pullOffShelves",method = POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "团购id", required = true, dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> pullOffShelves(@RequestParam(name="id")String id)throws NotExistException{
        return new ResponseEntity<>(grouponBlService.pullOffShelves(id),HttpStatus.OK);
    }

    @ApiOperation(value="通过名称查找团购",notes="通过名称查找团购")
    @RequestMapping(value="/find/name",method = POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "团购名称", required = true, dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findByName(@RequestParam(name="name")String name){
        return new ResponseEntity<>(grouponBlService.findByName(name),HttpStatus.OK);
    }

    @ApiOperation(value="通过关键字查找团购",notes="通过关键字查找团购")
    @RequestMapping(value="/find/keyword",method = POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", required = true, dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = InfoResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> findByKeyword(@RequestParam(name="keyword")String keyword){
        return new ResponseEntity<>(grouponBlService.findByKeyword(keyword),HttpStatus.OK);
    }

}
