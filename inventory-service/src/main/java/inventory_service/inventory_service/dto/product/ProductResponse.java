package inventory_service.inventory_service.dto.product;

public class ProductResponse {
    private Long id;
    private String itemSku;
    private String itemName;
    private Integer quantity;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String itemSku, String itemName, Integer quantity) {
        this.id = id;
        this.itemSku = itemSku;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemSku() {
        return itemSku;
    }

    public void setItemSku(String itemSku) {
        this.itemSku = itemSku;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
