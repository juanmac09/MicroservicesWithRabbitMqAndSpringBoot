package inventory_service.inventory_service.messaging.messages;

import java.util.List;

public class OrderCreatedEvent {

    private String orderUuid;
    private List<OrderItem> items;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(String orderUuid, List<OrderItem> items) {
        this.orderUuid = orderUuid;
        this.items = items;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public static class OrderItem {
        private String itemSku;
        private int quantity;

        public OrderItem() {
        }

        public OrderItem(String itemSku, int quantity) {
            this.itemSku = itemSku;
            this.quantity = quantity;
        }

        public String getItemSku() {
            return itemSku;
        }

        public void setItemSku(String itemSku) {
            this.itemSku = itemSku;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
