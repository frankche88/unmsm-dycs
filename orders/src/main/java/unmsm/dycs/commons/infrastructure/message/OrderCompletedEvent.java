package unmsm.dycs.commons.infrastructure.message;

public class OrderCompletedEvent {
    
    public OrderCompletedEvent(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long buyerId;

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String toString() {
        return "" + buyerId;
    }

}
