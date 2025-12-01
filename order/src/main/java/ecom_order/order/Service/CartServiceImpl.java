package ecom_order.order.Service;


import ecom_order.order.DTO.CartRequest;
import ecom_order.order.Model.CartItem;

import ecom_order.order.Repository.CartRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;


    @Override
    public void addProductToCart(String userId, CartRequest cartRequest) {

//        User user = userRepository.findById(Long.valueOf(userId))
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Product product = productRepository.findById(cartRequest.getProductId())
//                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingItem = cartRepository.findByUserAndProductId(userId, cartRequest.getProductId());

        if (existingItem != null) {
            existingItem.setQuantity((int) (existingItem.getQuantity()+cartRequest.getQuantity()));
            existingItem.setPrice(
                    BigDecimal.valueOf(100.00)
            );

            cartRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setUserId(userId);
            newItem.setProductId(newItem.getProductId());
            newItem.setQuantity(Math.toIntExact(cartRequest.getQuantity()));
            newItem.setPrice(
                    BigDecimal.valueOf(100.00)
            );

            cartRepository.save(newItem);
        }


    }
    @Override
    public boolean deleteItemFromCart(String userId, String productId) {
//        Optional<User> user = Optional.ofNullable(userRepository.findById(Long.valueOf(userId))
//                .orElseThrow(() -> new RuntimeException("User not found")));
//
//       Optional< Product> product = Optional.ofNullable(productRepository.findById(Long.valueOf(productId))
//               .orElseThrow(() -> new RuntimeException("Product not found")));
//        if(user.isPresent() && product.isPresent()){
//            cartRepository.deleteByUserAndProduct(user.get(), product.get());
//         return  true;
//        }
//        return false;
//
//    }
      CartItem cartItem=  cartRepository.deleteByUserAndProductId(userId, productId);
      if(cartItem!=null){
          cartRepository.delete(cartItem);
          return true;
      }
        return false;
    }
    @Override
    public List<CartItem> getCart(String userId) {

//        User user = userRepository.findById(Long.valueOf(userId))
//                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepository.findByUserId(userId);
    }


    @Override
    public void clearCart(String userId) {

        cartRepository.deleteByUserId(userId);
//        userRepository.findById(Long.valueOf(userId)).ifPresent(
//                cartRepository::deleteByUser
//        );

    }


}

