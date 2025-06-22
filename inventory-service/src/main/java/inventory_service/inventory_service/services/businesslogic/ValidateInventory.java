package inventory_service.inventory_service.services.businesslogic;

import inventory_service.inventory_service.entities.Product;
import inventory_service.inventory_service.exceptions.NotFoundException;
import inventory_service.inventory_service.services.ProductReadInterface;
import inventory_service.inventory_service.services.ProductUpdateQuantityInterface;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ValidateInventory {

    private final ProductReadInterface productReadService;
    private final ProductUpdateQuantityInterface productUpdateQuantityService;

    public ValidateInventory(ProductReadInterface productReadService,
                             ProductUpdateQuantityInterface productUpdateQuantityService) {
        this.productReadService = productReadService;
        this.productUpdateQuantityService = productUpdateQuantityService;
    }

    public ValidationResult validateStock(Map<String, Integer> requestedItems) {
        List<String> outOfStockItems = new ArrayList<>();
        Map<Product, Integer> productsToUpdate = new HashMap<>();

        for (Map.Entry<String, Integer> entry : requestedItems.entrySet()) {
            String sku = entry.getKey();
            int requestedQty = entry.getValue();

            try {
                Product product = productReadService.getProductBySku(sku);

                if (product.getQuantity() < requestedQty) {
                    outOfStockItems.add(sku + " (available: " + product.getQuantity() + ")");
                } else {
                    productsToUpdate.put(product, product.getQuantity() - requestedQty);
                }
            } catch (NotFoundException e) {
                outOfStockItems.add(sku + " (not found)");
            }
        }

        boolean success = outOfStockItems.isEmpty();

        if (success) {
            for (Map.Entry<Product, Integer> updateEntry : productsToUpdate.entrySet()) {
                productUpdateQuantityService.productUpdateQuantity(updateEntry.getKey(), updateEntry.getValue());
            }
        }

        return new ValidationResult(success, outOfStockItems);
    }

    public static class ValidationResult {
        private final boolean success;
        private final List<String> outOfStockItems;

        public ValidationResult(boolean success, List<String> outOfStockItems) {
            this.success = success;
            this.outOfStockItems = outOfStockItems;
        }

        public boolean isSuccess() {
            return success;
        }

        public List<String> getOutOfStockItems() {
            return outOfStockItems;
        }
    }
}
