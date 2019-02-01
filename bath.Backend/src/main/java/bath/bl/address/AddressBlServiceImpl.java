package bath.bl.address;

import bath.blservice.address.AddressBlService;
import bath.dataservice.address.AddressDataService;
import bath.dataservice.user.UserDataService;
import bath.entity.address.Address;
import bath.entity.user.User;
import bath.exception.NotExistException;
import bath.response.AddResponse;
import bath.response.InfoResponse;
import bath.response.address.AddressListResponse;
import bath.response.address.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressBlServiceImpl implements AddressBlService {
    private final AddressDataService addressDataService;
    private final UserDataService userDataService;
    @Autowired
    public AddressBlServiceImpl(AddressDataService addressDataService,UserDataService userDataService){
        this.addressDataService=addressDataService;
        this.userDataService=userDataService;
    }
    @Override
    public AddResponse addAddress(String openid, String receiver, String phone, String zone, String detailAddress, String postcode) throws NotExistException {
        Address address=new Address(userDataService.findByOpenid(openid),receiver,phone,zone,detailAddress,postcode);
        return new AddResponse(addressDataService.add(address));
    }

    @Override
    @Transactional
    public InfoResponse deleteAddress(int id) throws NotExistException {
        addressDataService.deleteById(id);
        return new InfoResponse();
    }

    @Override
    public InfoResponse updateAddress(int id,String receiver, String phone, String zone, String detailAddress, String postcode) throws NotExistException {
        Address address=addressDataService.findById(id);
        address.setReceiver(receiver);
        address.setPostcode(postcode);
        address.setZone(zone);
        address.setPhone(phone);
        address.setDetailAddress(detailAddress);
        addressDataService.update(address);
        return new InfoResponse();
    }

    @Override
    public AddressResponse findAddressById(int id) throws NotExistException {
        return new AddressResponse(addressDataService.findById(id));
    }

    @Override
    public AddressListResponse findByUser(String openid) throws NotExistException {
        User user=userDataService.findByOpenid(openid);
        return new AddressListResponse(addressDataService.findByUser(user));
    }
}
