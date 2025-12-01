package ecom_order.order.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
  //  @ManyToOne
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;
    private String productId;
  //  @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    private Integer quantity;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public OrderItem(
           // Product product,
            Integer quantity, BigDecimal price, Order order, LocalDateTime createdAt, LocalDateTime updatedAt) {
    }
}
