package delivery_service.delivery_service.messaging.messages;

import java.util.List;

public class InventoryValidatedEvent {
    private String orderUuid;
    private boolean success;
    private String reason;
    private List<String> outOfStockItems;

    public InventoryValidatedEvent() {}

    // Getters y setters

    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<String> getOutOfStockItems() {
        return outOfStockItems;
    }

    public void setOutOfStockItems(List<String> outOfStockItems) {
        this.outOfStockItems = outOfStockItems;
    }

}