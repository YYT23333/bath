package bath.response.order;

public class OrderGrouponItem {
    private String grouponId;
    private int amount;
    private double price;

    public OrderGrouponItem() {
    }

    public OrderGrouponItem(bath.entity.order.OrderGrouponItem orderItem){
        this.grouponId=orderItem.getGrouponId();
        this.amount=orderItem.getAmount();
        this.price=orderItem.getPrice();
    }

    public OrderGrouponItem(String grouponId, int amount, double price) {
        this.grouponId = grouponId;
        this.amount = amount;
        this.price = price;
    }

    public String getGrouponId() {
        return grouponId;
    }

    public void setGrouponId(String grouponId) {
        this.grouponId = grouponId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
