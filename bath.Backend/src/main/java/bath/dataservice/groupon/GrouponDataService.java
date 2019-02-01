package bath.dataservice.groupon;

import bath.entity.groupon.Groupon;
import bath.exception.NotExistException;
import bath.publicdatas.grouponType.GrouponType;

import java.util.List;


public interface GrouponDataService {
    String add(Groupon groupon);
    Groupon findById(String id) throws NotExistException;
    List<Groupon> findByType(GrouponType type);
    void update(Groupon groupon) throws NotExistException;
    void deleteById(String id) throws NotExistException;
}
