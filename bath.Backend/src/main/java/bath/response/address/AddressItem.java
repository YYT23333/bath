package bath.response.address;

import bath.entity.address.Address;

public class AddressItem {
    private int id;
    private String openid;
    private String receiver;//收货人姓名
    private String phone;//收货人电话
    private String zone;//地区信息
    private String detailAddress;//详细地址
    private String postcode;//邮政编码

    public AddressItem() {
    }
    public AddressItem(Address address){
        this.id=address.getId();
        this.openid=address.getUser().getOpenid();
        this.receiver=address.getReceiver();
        this.phone=address.getPhone();
        this.zone=address.getZone();
        this.detailAddress=address.getDetailAddress();
        this.phone=address.getPostcode();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}

