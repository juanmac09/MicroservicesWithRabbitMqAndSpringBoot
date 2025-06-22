package test_technical.order_service.dtos.orderitems;

public class OrderItemResponse {

    private String itemSku;
    private Integer quantity;

    // Getters and Setters

    public String getItemSku() {
        return itemSku;
    }

    public void setItemSku(String itemSku) {
        this.itemSku = itemSku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}