package inventory_service.inventory_service.services;

import inventory_service.inventory_service.dto.product.ProductResponse;
import inventory_service.inventory_service.entities.Product;

public interface ProductUpdateQuantityInterface {

    public ProductResponse productUpdateQuantity(Product product,Integer quantity);
}
