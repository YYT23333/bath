package bath.response.address;

import bath.entity.address.Address;
import bath.response.Response;

public class AddressResponse extends Response {
    private AddressItem addressItem;

    public AddressResponse() {
    }

    public AddressResponse(Address address) {
        this.addressItem = new AddressItem(address);
    }

    public AddressItem getAddressItem() {
        return addressItem;
    }

    public void setAddressItem(AddressItem addressItem) {
        this.addressItem = addressItem;
    }
}
