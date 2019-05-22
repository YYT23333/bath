package bath.data.groupon;

import bath.data.dao.groupon.GrouponDao;
import bath.dataservice.groupon.GrouponDataService;
import bath.entity.groupon.Groupon;
import bath.exception.NotExistException;
import bath.publicdatas.grouponType.GrouponType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GrouponDataServiceImpl implements GrouponDataService {
    private final GrouponDao grouponDao;

    @Autowired
    public GrouponDataServiceImpl(GrouponDao grouponDao) {
        this.grouponDao = grouponDao;
    }

    @Override
    public String add(Groupon groupon) {
        return grouponDao.save(groupon).getId();
    }

    @Override
    public Groupon findById(String id) throws NotExistException {
        Optional<Groupon> optional = grouponDao.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NotExistException("Groupon ID", id);
        }
    }

    @Override
    public List<Groupon> findByType(GrouponType type) {
        return grouponDao.findByType(type);
    }

    @Override
    public List<Groupon> findByName(String name) {
        return grouponDao.findByName(name);
    }

    @Override
    public List<Groupon> findByKeyword(String keyword) {
        List<Groupon> list = grouponDao.findAll();
        List<Groupon> result = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (Groupon temp : list) {
                if (temp.getDescription().contains(keyword)) {
                    result.add(temp);
                }
            }
        }
        return result;
    }

    @Override
    public void update(Groupon groupon) throws NotExistException {
        if (grouponDao.findById(groupon.getId()).isPresent()) {
            grouponDao.save(groupon);
        } else {
            throw new NotExistException("Groupon ID", groupon.getId());
        }

    }

    @Override
    public void deleteById(String id) throws NotExistException {
        if (grouponDao.findById(id).isPresent()) {
            grouponDao.deleteById(id);
        } else {
            throw new NotExistException("Groupon ID", id);
        }
    }
}
