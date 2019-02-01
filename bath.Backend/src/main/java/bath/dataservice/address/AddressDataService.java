package bath.dataservice.address;

import bath.entity.address.Address;
import bath.entity.user.User;
import bath.exception.NotExistException;

import java.util.List;

public interface AddressDataService {
    String add(Address address);
    void update(Address address) throws NotExistException;
    void deleteById(int id) throws NotExistException;
    Address findById(int id)throws NotExistException;
    List<Address> findByUser(User user);
}
