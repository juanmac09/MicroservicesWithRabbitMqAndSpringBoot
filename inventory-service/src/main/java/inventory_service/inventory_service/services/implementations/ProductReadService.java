package inventory_service.inventory_service.services.implementations;

import inventory_service.inventory_service.dto.product.ProductResponse;
import inventory_service.inventory_service.entities.Product;
import inventory_service.inventory_service.exceptions.NotFoundException;
import inventory_service.inventory_service.mappers.product.ProductResponseMapper;
import inventory_service.inventory_service.repositories.product.ProductRepository;
import inventory_service.inventory_service.services.ProductReadInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductReadService implements ProductReadInterface {

    private final ProductResponseMapper productResponseMapper;
    private final ProductRepository productRepository;

    public ProductReadService(ProductResponseMapper productResponseMapper, ProductRepository productRepository) {
        this.productResponseMapper = productResponseMapper;
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductResponse> getAllProduct(Pageable pageable) {
        Page<Product> products = this.productRepository.findAll(pageable);
        return products.map(productResponseMapper::map);
    }

    @Override
    public Product getProductBySku(String sku) {
        List<Product> products = this.productRepository.findProductByItemSku(sku);

        if (products == null || products.isEmpty()) {
            throw new NotFoundException("Product with SKU " + sku + " not found");
        }

        return products.getLast();
    }

}
