package ecom_order.order.DTO;

import lombok.Data;

@Data
public class CartRequest {

    private String productId;
    private Long quantity;
}
