package ecom_order.order.Controller;



import ecom_order.order.DTO.OrderResponse;
import ecom_order.order.Service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createrOrder(@RequestHeader("X-User-Id") Long userId) {

        OrderResponse order = orderService.createOrder(userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(order);
    }

}
