package ecom_order.order.Service;


import ecom_order.order.DTO.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(Long userId);
}
