package inventory_service.inventory_service.services.implementations;

import inventory_service.inventory_service.dto.product.ProductResponse;
import inventory_service.inventory_service.entities.Product;
import inventory_service.inventory_service.mappers.product.ProductResponseMapper;
import inventory_service.inventory_service.repositories.product.ProductRepository;
import inventory_service.inventory_service.services.ProductReadInterface;
import inventory_service.inventory_service.services.ProductUpdateQuantityInterface;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUpdateQuantityService  implements ProductUpdateQuantityInterface {

    private ProductResponseMapper  productResponseMapper;
    private ProductRepository productRepository;
    public ProductUpdateQuantityService(ProductRepository productRepository, ProductResponseMapper productResponseMapper) {
        this.productRepository = productRepository;
        this.productResponseMapper = productResponseMapper;
    }

    @Transactional
    @Override
    public ProductResponse productUpdateQuantity(Product product, Integer quantity) {
        product.setQuantity(quantity);
        this.productRepository.save(product);
        return this.productResponseMapper.map(product);
    }
}
