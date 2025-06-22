package inventory_service.inventory_service.services;

import inventory_service.inventory_service.dto.product.ProductRequestDto;
import inventory_service.inventory_service.dto.product.ProductResponse;

public interface ProductCreateInterface {

    public ProductResponse productCreate(ProductRequestDto productRequestDto);
}
