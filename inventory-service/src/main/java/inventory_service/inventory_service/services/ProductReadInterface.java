package inventory_service.inventory_service.services;

import inventory_service.inventory_service.dto.product.ProductResponse;
import inventory_service.inventory_service.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductReadInterface {
    public Page<ProductResponse> getAllProduct(Pageable pageable);
    public Product getProductBySku(String sku);
}
