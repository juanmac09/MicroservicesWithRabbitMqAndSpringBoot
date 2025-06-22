package inventory_service.inventory_service.services.implementations;

import inventory_service.inventory_service.dto.product.ProductRequestDto;
import inventory_service.inventory_service.dto.product.ProductResponse;
import inventory_service.inventory_service.entities.Product;
import inventory_service.inventory_service.mappers.product.ProductResponseMapper;
import inventory_service.inventory_service.repositories.product.ProductRepository;
import inventory_service.inventory_service.services.ProductCreateInterface;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.UUID;
@Service
public class ProductCreateService implements ProductCreateInterface {


    private final ProductResponseMapper productResponseMapper;
    private final ProductRepository productRepository;

    public ProductCreateService(ProductResponseMapper productResponseMapper, ProductRepository productRepository) {
        this.productResponseMapper = productResponseMapper;
        this.productRepository = productRepository;
    }



    @Transactional
    @Override
    public ProductResponse productCreate(ProductRequestDto productRequestDto) {

        Product newProduct = new Product();
        newProduct.setItemName(productRequestDto.getItemName());
        newProduct.setItemSku(UUID.randomUUID().toString());
        newProduct.setQuantity(productRequestDto.getQuantity());

        this.productRepository.save(newProduct);

        return this.productResponseMapper.map(newProduct);
    }


}

