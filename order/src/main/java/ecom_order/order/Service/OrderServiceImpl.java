package ecom_order.order.Service;


import ecom_order.order.DTO.OrderItemDTO;
import ecom_order.order.DTO.OrderResponse;
import ecom_order.order.Enum.OrderStatus;
import ecom_order.order.Model.CartItem;
import ecom_order.order.Model.Order;
import ecom_order.order.Model.OrderItem;
import ecom_order.order.Repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
//    private final USerRepository userRepository;

    @Override
    public OrderResponse createOrder(Long userId) {
        List<CartItem> cartItems= cartService.getCart(userId);
        if(cartItems.isEmpty()){

        }
//       Optional<User> user=userRepository.findById(Long.valueOf(userId));
//        if(user.isEmpty()){
//            throw new RuntimeException("User not found with id " + userId);
//        }
        BigDecimal totalAmount= cartItems.stream()
                .map(cartItem->cartItem.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order=new Order();
        order.setUserId(String.valueOf(userId));
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.CONFIRMED);
        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> {
                    OrderItem oi = new OrderItem();
                 //   oi.setProductId(item.getProductId());
                    oi.setQuantity(item.getQuantity());
                    oi.setPrice(BigDecimal.valueOf(100.00));
                    oi.setProductId(item.getProductId());

                   // order.getOrderItems().add(oi);
                    oi.setCreatedAt(item.getCreatedAt());
                    oi.setUpdatedAt(item.getUpdatedAt());
                    oi.setOrder(order);
                    return oi;
                })
                .toList();



        order.setOrderItems(orderItems);
        Order save=orderRepository.save(order);

      //  cartService.clearCart(userId);


        return orderResponse(save);
    }

    private OrderResponse orderResponse(Order save) {
        return new OrderResponse(
                save.getId(),
                save.getTotalAmount(),
                save.getStatus(),
                save.getOrderItems()
                        .stream()
                        .map(orderItem -> new OrderItemDTO(
                                orderItem.getId(),
                                orderItem.getQuantity(),
                                orderItem.getPrice(),
                                String.valueOf(orderItem.getProductId()),
                                orderItem.getPrice()
                                        .multiply(BigDecimal.valueOf(orderItem.getQuantity()))
                        ))
                        .toList(),
                save.getCreatedAt()
        );
    }



    }


