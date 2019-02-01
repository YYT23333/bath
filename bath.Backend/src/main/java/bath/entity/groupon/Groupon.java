package bath.entity.groupon;

import bath.publicdatas.grouponType.GrouponType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Groupon {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "originalPrice")
    private double originalPrice;

    @Column(name = "price")
    private double price;

    @Column(name = "takeEffectTime")
    private Date takeEffectTime;

    @Column(name = "loseEffectTime")
    private Date loseEffectTime;

    @Column(name="putOnShelvesTime")
    private Date putOnShelveTime;

    @Column(name="pullOffShelvesTime")
    private Date pullOffShelveTime;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private int amount;

    @Column(name="type")
    private GrouponType type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTakeEffectTime() {
        return takeEffectTime;
    }

    public void setTakeEffectTime(Date takeEffectTime) {
        this.takeEffectTime = takeEffectTime;
    }

    public Date getLoseEffectTime() {
        return loseEffectTime;
    }

    public void setLoseEffectTime(Date loseEffectTime) {
        this.loseEffectTime = loseEffectTime;
    }


    public Date getPutOnShelveTime() {
        return putOnShelveTime;
    }

    public void setPutOnShelveTime(Date putOnShelveTime) {
        this.putOnShelveTime = putOnShelveTime;
    }

    public Date getPullOffShelveTime() {
        return pullOffShelveTime;
    }

    public void setPullOffShelveTime(Date pullOffShelveTime) {
        this.pullOffShelveTime = pullOffShelveTime;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public GrouponType getType() {
        return type;
    }

    public void setType(GrouponType type) {
        this.type = type;
    }
}
