package ecom_order.order.Controller;


import ecom_order.order.DTO.CartRequest;
import ecom_order.order.Model.CartItem;
import ecom_order.order.Service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;


    @PostMapping
    public ResponseEntity<Void> addToCart(
            @RequestHeader("X-User-ID") Long userId,
            @RequestBody CartRequest request) {
        cartService.addProductToCart(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeFromCart(
            @RequestHeader("X-User-ID") Long userId,
            @PathVariable Long productId) {
        boolean deleted = cartService.deleteItemFromCart(userId, productId);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(
            @RequestHeader("X-User-ID") Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }


}