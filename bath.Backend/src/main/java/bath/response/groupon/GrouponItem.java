package bath.response.groupon;

import bath.entity.groupon.Groupon;

import java.util.Date;

public class GrouponItem {
    private String id;
    private String name;
    private double originalPrice;
    private double price;
    private Date takeEffectTime;
    private Date loseEffectTime;
    private Date putOnShelveTime;
    private Date pullOffShelveTime;
    private String description;
    private int amount;

    public GrouponItem() {
    }

    public GrouponItem(Groupon groupon){
        this.id=groupon.getId();
        this.name=groupon.getName();
        this.originalPrice=groupon.getOriginalPrice();
        this.price=groupon.getPrice();
        this.takeEffectTime=groupon.getTakeEffectTime();
        this.loseEffectTime=groupon.getLoseEffectTime();
        this.putOnShelveTime=groupon.getPutOnShelveTime();
        this.pullOffShelveTime=groupon.getPullOffShelveTime();
        this.description=groupon.getDescription();
        this.amount=groupon.getAmount();
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
