package bath.response.groupon;

import bath.entity.groupon.Groupon;
import bath.response.Response;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class GrouponResponse extends Response {

    private GrouponItem groupon;

    public GrouponResponse() {
    }

    public GrouponResponse(Groupon groupon) {
        this.groupon = new GrouponItem(groupon);
    }

    public GrouponItem getGroupon() {
        return groupon;
    }

    public void setGroupon(GrouponItem groupon) {
        this.groupon = groupon;
    }
}
