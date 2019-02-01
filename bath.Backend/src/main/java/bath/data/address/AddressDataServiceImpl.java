package bath.data.address;

import bath.data.dao.address.AddressDao;
import bath.dataservice.address.AddressDataService;
import bath.entity.address.Address;
import bath.entity.user.User;
import bath.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressDataServiceImpl implements AddressDataService {
    private final AddressDao addressDao;
    @Autowired
    public AddressDataServiceImpl(AddressDao addressDao){
        this.addressDao=addressDao;
    }
    @Override
    public String add(Address address) {
        return addressDao.save(address).getId()+"";
    }

    @Override
    public void update(Address address) throws NotExistException {
        if(addressDao.findById(address.getId()).isPresent()){
            addressDao.save(address);
        }else{
            throw new NotExistException("Address ID",address.getId()+"");
        }
    }

    @Override
    public void deleteById(int id) throws NotExistException {
        if(addressDao.findById(id).isPresent()){
            addressDao.deleteById(id);
        }else{
            throw new NotExistException("Address ID",id+"");
        }
    }

    @Override
    public Address findById(int id) throws NotExistException {
        Optional<Address> addressOptional=addressDao.findById(id);
        if(addressOptional.isPresent()){
            return addressOptional.get();
        }else{
            throw new NotExistException("Address ID",id+"");
        }
    }

    @Override
    public List<Address> findByUser(User user) {
        return addressDao.findByUser(user);
    }
}
