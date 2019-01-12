package bath.dataservice.groupon;

import bath.entity.groupon.Groupon;
import bath.exception.NotExistException;

import java.util.List;


public interface GrouponDataService {
    void add(Groupon groupon);
    Groupon findById(String id) throws NotExistException;
    List<Groupon> getAll();
    void update(Groupon groupon) throws NotExistException;
    void deleteById(String id) throws NotExistException;
}
