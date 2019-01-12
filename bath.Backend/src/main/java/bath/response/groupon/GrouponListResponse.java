package bath.response.groupon;

import bath.entity.groupon.Groupon;
import bath.response.Response;

import java.util.ArrayList;
import java.util.List;

public class GrouponListResponse extends Response {

    private List<GrouponItem> groupons;

    public GrouponListResponse() {
    }

    public GrouponListResponse(List<Groupon> groupons) {
        if (groupons != null && groupons.size() > 0) {
            this.groupons = new ArrayList<>();
            for (Groupon temp : groupons) {
                this.groupons.add(new GrouponItem(temp));
            }
        }
    }

    public List<GrouponItem> getGroupons() {
        return groupons;
    }

    public void setGroupons(List<GrouponItem> groupons) {
        this.groupons = groupons;
    }
}

