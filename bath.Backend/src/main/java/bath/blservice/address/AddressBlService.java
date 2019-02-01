package bath.blservice.address;

import bath.exception.NotExistException;
import bath.response.AddResponse;
import bath.response.InfoResponse;
import bath.response.address.AddressListResponse;
import bath.response.address.AddressResponse;

public interface AddressBlService {
    /**
     * 用户添加地址
     * @param openid
     * @param receiver
     * @param phone
     * @param zone
     * @param detailAddress
     * @param postcode
     * @return 是否成功
     */
    AddResponse addAddress(String openid, String receiver, String phone, String zone, String detailAddress, String postcode)throws NotExistException;
    /**
     * 用户删除自己的地址
     * @param id
     * @return 是否成功
     */
    InfoResponse deleteAddress(int id)throws NotExistException;
    /**
     * 用户更新自己的某一条地址
     * @param id
     * @param receiver
     * @param phone
     * @param zone
     * @param detailAddress
     * @param postcode
     * @return 是否成功
     */
    InfoResponse updateAddress(int id,String receiver, String phone, String zone, String detailAddress, String postcode)throws NotExistException;
    /**
     * 通过id查找地址
     * @param id
     * @return 地址
     */
    AddressResponse findAddressById(int id)throws NotExistException;

    AddressListResponse findByUser(String openid) throws NotExistException;
}
