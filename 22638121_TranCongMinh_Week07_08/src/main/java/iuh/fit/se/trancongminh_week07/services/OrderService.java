package iuh.fit.se.trancongminh_week07.services;

import iuh.fit.se.trancongminh_week07.entities.Order;
import iuh.fit.se.trancongminh_week07.reposities.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public Order update(Integer id, Order newOrder) {
        return orderRepository.findById(id)
                .map(o -> {
                    o.setDate(newOrder.getDate());
                    o.setCustomer(newOrder.getCustomer());
                    o.setOrderLines(newOrder.getOrderLines());
                    return orderRepository.save(o);
                })
                .orElseThrow(() -> new RuntimeException("Order not found by id: " + id));
    }

    public void delete(Integer id) {
        if (!orderRepository.existsById(id))
            throw new RuntimeException("Order not found with id: " + id);
        orderRepository.deleteById(id);
    }
}
