package ecom_order.order.Service;



import ecom_order.order.DTO.CartRequest;
import ecom_order.order.Model.CartItem;

import java.util.List;

public interface CartService   {
  void addProductToCart(String userId, CartRequest cartRequest);


    boolean deleteItemFromCart(String userId, String productId);

    List<CartItem> getCart(String userId);

    void clearCart(String userId);
}
