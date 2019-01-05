package bath.bl.groupon;

import bath.blservice.groupon.GrouponBlService;
import bath.dataservice.groupon.GrouponDataService;
import bath.entity.groupon.Groupon;
import bath.exception.NotExistException;
import bath.response.groupon.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class GrouponBlServiceImpl implements GrouponBlService {
    private GrouponDataService grouponDataService;
    @Autowired
    public GrouponBlServiceImpl(GrouponDataService grouponDataService){
        this.grouponDataService=grouponDataService;
    }
    @Override
    public GrouponAddResponse addGroupon(String name, double originalPrice, double price, Date takeEffectTime, Date loseEffectTime, Date putOnShelvesTime, Date pullOffShelvesTime, String instruction, String description, int amount) {
        Groupon groupon=new Groupon();
        groupon.setName(name);
        groupon.setOriginalPrice(originalPrice);
        groupon.setPrice(price);
        groupon.setTakeEffectTime(takeEffectTime);
        groupon.setLoseEffectTime(loseEffectTime);
        groupon.setPutOnShelveTime(putOnShelvesTime);
        groupon.setPullOffShelveTime(pullOffShelvesTime);
        groupon.setAmount(amount);
        String id=UUID.randomUUID().toString();
        groupon.setId(id);
        grouponDataService.addGroupon(groupon);
        return new GrouponAddResponse(id);
    }

    @Override
    public GrouponDeleteResponse deleteGroupon(String id) throws NotExistException {
        grouponDataService.deleteGrouponById(id);
        return new GrouponDeleteResponse(id);
    }

    @Override
    public GrouponUpdateResponse updateGroupon(String id, String name, double originalPrice, double price, Date takeEffectTime, Date loseEffectTime,Date putOnShelvesTime,Date pullOffShelvesTime, String instruction, String description, int amount) throws NotExistException {
        Groupon groupon=grouponDataService.findById(id);
        groupon.setName(name);
        groupon.setOriginalPrice(originalPrice);
        groupon.setPrice(price);
        groupon.setTakeEffectTime(takeEffectTime);
        groupon.setLoseEffectTime(loseEffectTime);
        groupon.setPutOnShelveTime(putOnShelvesTime);
        groupon.setPullOffShelveTime(pullOffShelvesTime);
        groupon.setInstruction(instruction);
        groupon.setDescription(description);
        groupon.setAmount(amount);
        grouponDataService.updateGroupon(groupon);
        return new GrouponUpdateResponse(id);
    }

    @Override
    public GrouponUpdateResponse putOnShelves(String id) throws NotExistException {
        Groupon groupon=grouponDataService.findById(id);
        groupon.setPutOnShelveTime(new Date());
        groupon.setPullOffShelveTime(null);
        grouponDataService.updateGroupon(groupon);
        return new GrouponUpdateResponse(id);
    }

    @Override
    public GrouponUpdateResponse pullOffshelves(String id) throws NotExistException {
        Groupon groupon=grouponDataService.findById(id);
        groupon.setPullOffShelveTime(new Date());
        grouponDataService.updateGroupon(groupon);
        return new GrouponUpdateResponse(id);
    }

    @Override
    public GrouponResponse findById(String id) throws NotExistException {
        return new GrouponResponse(grouponDataService.findById(id));
    }

    @Override
    public GrouponListResponse getAll() throws NotExistException {
        return new GrouponListResponse(grouponDataService.getAll());
    }
}
