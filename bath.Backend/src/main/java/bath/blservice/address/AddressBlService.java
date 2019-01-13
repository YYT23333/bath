package bath.blservice.address;

import bath.exception.NotExistException;
import bath.response.InfoResponse;
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
    InfoResponse addAddress(String openid,String receiver, String phone, String zone, String detailAddress, String postcode)throws NotExistException;
    /**
     * 用户删除自己的地址
     * @param id
     * @return 是否成功
     */
    InfoResponse deleteAddress(String id)throws NotExistException;
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
    InfoResponse updateAddress(String id,String receiver, String phone, String zone, String detailAddress, String postcode)throws NotExistException;
    /**
     * 通过id查找地址
     * @param id
     * @return 地址
     */
    AddressResponse findAddressById(String id)throws NotExistException;
}
