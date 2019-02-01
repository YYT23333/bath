package bath.response.address;

import bath.entity.address.Address;
import bath.response.Response;

import java.util.ArrayList;
import java.util.List;

public class AddressListResponse extends Response {
    private List<AddressItem> addressItemList;

    public AddressListResponse() {
    }

    public AddressListResponse(List<Address> addressList) {
        this.addressItemList=new ArrayList<>();
        if(addressList!=null && addressList.size()>0){
            for(Address temp:addressList){
                this.addressItemList.add(new AddressItem(temp));
            }
        }
    }

    public List<AddressItem> getAddressItemList() {
        return addressItemList;
    }

    public void setAddressItemList(List<AddressItem> addressItemList) {
        this.addressItemList = addressItemList;
    }
}
