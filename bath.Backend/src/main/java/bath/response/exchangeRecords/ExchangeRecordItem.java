package bath.response.exchangeRecords;

import bath.entity.groupon.Groupon;
import bath.entity.integral.ExchangeRecord;
import bath.response.groupon.GrouponItem;

public class ExchangeRecordItem {
    private int id;
    private String openid;
    private GrouponItem groupon;

    public ExchangeRecordItem(){}

    public ExchangeRecordItem(ExchangeRecord exchangeRecord){
        this.id=exchangeRecord.getId();
        this.openid=exchangeRecord.getUser().getOpenid();
        this.groupon=new GrouponItem(exchangeRecord.getGroupOn());
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

    public GrouponItem getGroupon() {
        return groupon;
    }

    public void setGroupon(GrouponItem groupon) {
        this.groupon = groupon;
    }
}
