package bath.dataservice.address;

import bath.entity.address.Address;
import bath.exception.NotExistException;

public interface AddressDataService {
    void add(Address address);
    void update(Address address) throws NotExistException;
    void deleteById(String id) throws NotExistException;
    Address findById(String id)throws NotExistException;
}
