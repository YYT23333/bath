package bath.bl.groupon;

import bath.blservice.groupon.GrouponBlService;
import bath.dataservice.groupon.GrouponDataService;
import bath.entity.groupon.Groupon;
import bath.exception.NotExistException;
import bath.publicdatas.grouponType.GrouponType;
import bath.response.AddResponse;
import bath.response.InfoResponse;
import bath.response.groupon.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GrouponBlServiceImpl implements GrouponBlService {
    private final GrouponDataService grouponDataService;
    @Autowired
    public GrouponBlServiceImpl(GrouponDataService grouponDataService){
        this.grouponDataService=grouponDataService;
    }
    @Override
    public AddResponse addGroupon(String name, double originalPrice, double price, Date takeEffectTime, Date loseEffectTime, Date putOnShelvesTime, Date pullOffShelvesTime, String description, int amount, String type,String image) {
        Groupon groupon=new Groupon();
        groupon.setName(name);
        groupon.setOriginalPrice(originalPrice);
        groupon.setPrice(price);
        groupon.setTakeEffectTime(takeEffectTime);
        groupon.setLoseEffectTime(loseEffectTime);
        groupon.setPutOnShelveTime(putOnShelvesTime);
        groupon.setPullOffShelveTime(pullOffShelvesTime);
        groupon.setDescription(description);
        groupon.setAmount(amount);
        groupon.setType(GrouponType.valueOf(type));
        groupon.setImage(image);
        String id=UUID.randomUUID().toString();
        groupon.setId(id);
        grouponDataService.add(groupon);
        return new AddResponse(id);
    }

    @Override
    public InfoResponse deleteGroupon(String id) throws NotExistException {
        grouponDataService.deleteById(id);
        return new InfoResponse();
    }

    @Override
    public InfoResponse updateGroupon(String id, String name, double originalPrice, double price, Date takeEffectTime, Date loseEffectTime,Date putOnShelvesTime,Date pullOffShelvesTime, String description, int amount,String type,String image) throws NotExistException {
        Groupon groupon=grouponDataService.findById(id);
        groupon.setName(name);
        groupon.setOriginalPrice(originalPrice);
        groupon.setPrice(price);
        groupon.setTakeEffectTime(takeEffectTime);
        groupon.setLoseEffectTime(loseEffectTime);
        groupon.setPutOnShelveTime(putOnShelvesTime);
        groupon.setPullOffShelveTime(pullOffShelvesTime);
        groupon.setDescription(description);
        groupon.setAmount(amount);
        groupon.setType(GrouponType.valueOf(type));
        groupon.setImage(image);
        grouponDataService.update(groupon);
        return new InfoResponse();
    }

    @Override
    public InfoResponse putOnShelves(String id) throws NotExistException {
        Groupon groupon=grouponDataService.findById(id);
        groupon.setPutOnShelveTime(new Date());
        groupon.setPullOffShelveTime(null);
        grouponDataService.update(groupon);
        return new InfoResponse();
    }

    @Override
    public InfoResponse pullOffShelves(String id) throws NotExistException {
        Groupon groupon=grouponDataService.findById(id);
        groupon.setPullOffShelveTime(new Date());
        grouponDataService.update(groupon);
        return new InfoResponse();
    }

    @Override
    public GrouponResponse findById(String id) throws NotExistException {
        return new GrouponResponse(grouponDataService.findById(id));
    }

    @Override
    public GrouponListResponse getAllOrdinary() {
        return new GrouponListResponse(grouponDataService.findByType(GrouponType.ORDINARY));
    }

    @Override
    public GrouponListResponse findByName(String name) {
        return new GrouponListResponse(grouponDataService.findByName(name));
    }

    @Override
    public GrouponListResponse findByKeyword(String keyword) {
        return new GrouponListResponse(grouponDataService.findByKeyword(keyword));
    }

    @Override
    public GrouponListResponse getAllIntegral() {
        return new GrouponListResponse(grouponDataService.findByType(GrouponType.INTEGRAL));
    }

    @Override
    public GrouponListResponse getAvailableOrdinaryGroupon() throws NotExistException {
        List<Groupon> grouponList=grouponDataService.findByType(GrouponType.ORDINARY);
        List<Groupon> availableList=new ArrayList<>();
        Date currentDate=new Date();
        for(Groupon temp:grouponList){
            if(temp.getPutOnShelveTime().before(currentDate) && (temp.getPullOffShelveTime()==null || temp.getPullOffShelveTime().after(currentDate))){
                availableList.add(temp);
            }
        }
        if(availableList==null || availableList.size()<1)
            throw new NotExistException("正在销售的团购","");
        return new GrouponListResponse(availableList);
    }

    @Override
    public GrouponListResponse getAvailableIntegralGroupon() throws NotExistException {
        List<Groupon> grouponList=grouponDataService.findByType(GrouponType.INTEGRAL);
        List<Groupon> availableList=new ArrayList<>();
        Date currentDate=new Date();
        for(Groupon temp:grouponList){
            if(temp.getPutOnShelveTime().before(currentDate) && (temp.getPullOffShelveTime()==null || temp.getPullOffShelveTime().after(currentDate))){
                availableList.add(temp);
            }
        }
        if(availableList==null || availableList.size()<1)
            throw new NotExistException("可兑换的团购","");
        return new GrouponListResponse(availableList);
    }


}
