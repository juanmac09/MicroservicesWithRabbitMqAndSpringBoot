package inventory_service.inventory_service.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequestDto {

    @NotBlank(message = "Product name must not be blank")
    @Schema(description = "Nombre del producto", example = "Camiseta")
    private String itemName;

    @NotNull(message = "Quantity must not be null")
    @Min(value = 0, message = "Quantity must be equal to or greater than 0")
    @Schema(description = "Cantidad del producto", example = "100")
    private Integer quantity;

    public ProductRequestDto() {
    }

    public ProductRequestDto(String itemName, Integer quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
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