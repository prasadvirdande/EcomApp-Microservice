package ecom_order.order.Service;



import ecom_order.order.DTO.CartRequest;
import ecom_order.order.Model.CartItem;

import java.util.List;

public interface CartService   {
  void addProductToCart(Long userId, CartRequest cartRequest);


    boolean deleteItemFromCart(Long userId, Long productId);

    List<CartItem> getCart(Long userId);

    void clearCart(Long userId);
}
