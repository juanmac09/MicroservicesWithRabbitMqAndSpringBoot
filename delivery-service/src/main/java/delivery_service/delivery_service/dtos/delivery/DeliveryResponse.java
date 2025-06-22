package delivery_service.delivery_service.dtos.delivery;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Respuesta de una entrega")
public class DeliveryResponse {

    @Schema(description = "ID interno de la entrega", example = "1")
    private Long id;

    @Schema(description = "UUID de la orden asociada", example = "123e4567-e89b-12d3-a456-426614174000")
    private String orderUuid;

    @Schema(description = "Estado actual de la entrega", example = "PENDING")
    private String deliveryStatus;

    @Schema(description = "Dirección de entrega", example = "Calle 123 #45-67")
    private String address;

    @Schema(description = "Fecha de creación", example = "2024-06-22T14:55:00Z")
    private Instant createdAt;

    @Schema(description = "Fecha de última actualización", example = "2024-06-23T10:20:00Z")
    private Instant updatedAt;

    public DeliveryResponse() {
    }

    public DeliveryResponse(Long id, String orderUuid, String deliveryStatus, String address, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.orderUuid = orderUuid;
        this.deliveryStatus = deliveryStatus;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
