package test_technical.order_service.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test_technical.order_service.common.ApiResponse;
import test_technical.order_service.dtos.order.OrderRequestDto;
import test_technical.order_service.dtos.order.OrderResponse;
import test_technical.order_service.services.order.OrderReadInterface;
import test_technical.order_service.services.order.OrderWriteInterface;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderWriteInterface orderWriteService;
    private final OrderReadInterface orderReadService;

    public OrderController(OrderWriteInterface orderWriteService, OrderReadInterface orderReadService) {
        this.orderWriteService = orderWriteService;
        this.orderReadService = orderReadService;
    }

    @Operation(summary = "Crear una nueva orden", description = "Crea una nueva orden con una lista de ítems")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Orden creada exitosamente",
                    content = @Content(schema = @Schema(implementation = OrderResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida"
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            @Valid
            @RequestBody(
                    description = "Datos para crear una orden",
                    required = true,
                    content = @Content(schema = @Schema(implementation = OrderRequestDto.class))
            )
            @NotNull @org.springframework.web.bind.annotation.RequestBody OrderRequestDto orderRequestDto) {

        ApiResponse<OrderResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Order created successfully");
        response.setData(this.orderWriteService.createOrder(orderRequestDto));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener una orden por UUID", description = "Devuelve una orden y sus ítems asociados")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Orden encontrada",
                    content = @Content(schema = @Schema(implementation = OrderResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Orden no encontrada"
            )
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderByUuid(@PathVariable String uuid) {
        ApiResponse<OrderResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Order found");
        response.setData(this.orderReadService.getItemByUuid(uuid));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
