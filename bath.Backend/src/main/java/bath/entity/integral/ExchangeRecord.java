package bath.entity.integral;

import bath.entity.groupon.Groupon;
import bath.entity.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class ExchangeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupon_id")
    private Groupon groupOn;

    @Column(name="time")
    private Date time;

    public ExchangeRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Groupon getGroupOn() {
        return groupOn;
    }

    public void setGroupOn(Groupon groupOn) {
        this.groupOn = groupOn;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
