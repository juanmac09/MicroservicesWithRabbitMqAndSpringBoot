package inventory_service.inventory_service.controllers;

import inventory_service.inventory_service.common.ApiResponse;
import inventory_service.inventory_service.dto.product.ProductRequestDto;
import inventory_service.inventory_service.dto.product.ProductResponse;
import inventory_service.inventory_service.services.ProductCreateInterface;
import inventory_service.inventory_service.services.ProductReadInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductCreateInterface productCreateInterface;
    private final ProductReadInterface productReadInterface;

    public ProductController(ProductCreateInterface productCreateInterface, ProductReadInterface productReadInterface) {
        this.productCreateInterface = productCreateInterface;
        this.productReadInterface = productReadInterface;
    }

    @Operation(
            summary = "Crear un nuevo producto",
            description = "Crea un producto en el inventario a partir de un DTO de entrada válido"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Producto creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProductResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida: datos faltantes o incorrectos",
                    content = @Content
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProductResponse>> productCreate(
            @Valid @RequestBody ProductRequestDto productRequestDto) {

        ApiResponse<ProductResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Product created successfully");
        response.setData(productCreateInterface.productCreate(productRequestDto));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Obtener todos los productos paginados",
            description = "Devuelve una lista paginada de productos disponibles en el inventario"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Productos obtenidos correctamente",
                    content = @Content(schema = @Schema(implementation = ProductResponse.class))
            )
    })
    @GetMapping("/all/page/{page}/size/{size}")
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> getAllProducts(
            @PathVariable("page") int page,
            @PathVariable("size") int size) {

        Pageable pageable = PageRequest.of(page, size);
        ApiResponse<Page<ProductResponse>> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("All products retrieved successfully");
        response.setData(this.productReadInterface.getAllProduct(pageable));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
