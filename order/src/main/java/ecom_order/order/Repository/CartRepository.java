package ecom_order.order.Repository;


import ecom_order.order.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserIdAndProductId(Long userId, Long productId);


    List<CartItem> findByUserId(Long userId);

    CartItem deleteByUserIdAndProductId(Long userId, Long productId);

    void deleteByUserId(Long userId);
}
