package delivery_service.delivery_service.controllers;

import delivery_service.delivery_service.common.ApiResponse;
import delivery_service.delivery_service.dtos.delivery.DeliveryResponse;
import delivery_service.delivery_service.services.DeliveryReadInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryReadInterface deliveryReadService;

    public DeliveryController(DeliveryReadInterface deliveryReadService) {
        this.deliveryReadService = deliveryReadService;
    }

    @Operation(
            summary = "Obtener todas las entregas paginadas",
            description = "Devuelve una lista paginada con todos los registros de entregas existentes"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Entregas encontradas correctamente",
                    content = @Content(schema = @Schema(implementation = DeliveryResponse.class))
            )
    })
    @GetMapping("/all/page/{page}/size/{size}")
    public ResponseEntity<ApiResponse<Page<DeliveryResponse>>> page(
            @PathVariable("page") int page,
            @PathVariable("size") int size) {

        Pageable pageable = PageRequest.of(page, size);
        ApiResponse<Page<DeliveryResponse>> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("All deliveries found");
        response.setData(this.deliveryReadService.deliveryGetAll(pageable));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Buscar entrega por UUID de orden",
            description = "Devuelve los datos de entrega asociados al UUID de la orden"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Entrega encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = DeliveryResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Entrega no encontrada para el UUID especificado",
                    content = @Content
            )
    })
    @GetMapping("/{order_uuid}")
    public ResponseEntity<ApiResponse<DeliveryResponse>> order(
            @PathVariable("order_uuid") String orderUuid) {

        ApiResponse<DeliveryResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Delivery order found");
        response.setData(this.deliveryReadService.deliveryGetByOrderUuid(orderUuid));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
