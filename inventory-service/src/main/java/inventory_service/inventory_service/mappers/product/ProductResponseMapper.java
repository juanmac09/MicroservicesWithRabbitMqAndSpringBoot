package inventory_service.inventory_service.mappers.product;

import inventory_service.inventory_service.dto.product.ProductResponse;
import inventory_service.inventory_service.entities.Product;
import inventory_service.inventory_service.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseMapper implements Mapper<ProductResponse, Product> {

    @Override
    public ProductResponse map(Product e) {
        if (e == null) return null;

        ProductResponse dto = new ProductResponse();
        dto.setId(e.getId());
        dto.setItemName(e.getItemName());
        dto.setItemSku(e.getItemSku());
        dto.setQuantity(e.getQuantity());

        return dto;
    }
}
