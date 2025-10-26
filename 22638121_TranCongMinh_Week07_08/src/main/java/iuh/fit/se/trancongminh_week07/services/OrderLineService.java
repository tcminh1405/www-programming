package iuh.fit.se.trancongminh_week07.services;

import iuh.fit.se.trancongminh_week07.entities.OrderLine;
import iuh.fit.se.trancongminh_week07.reposities.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;

    public List<OrderLine> findAll() {
        return orderLineRepository.findAll();
    }

    public List<OrderLine> findByOrderId(Integer orderId) {
        return orderLineRepository.findByOrderId(orderId);
    }

    public Optional<OrderLine> findById(Integer id) {
        return orderLineRepository.findById(id);
    }

    public OrderLine create(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    public OrderLine update(Integer id, OrderLine newLine) {
        return orderLineRepository.findById(id)
                .map(line -> {
                    line.setAmount(newLine.getAmount());
                    line.setPurchasePrice(newLine.getPurchasePrice());
                    line.setProduct(newLine.getProduct());
                    return orderLineRepository.save(line);
                })
                .orElseThrow(() -> new RuntimeException("OrderLine not found by id: " + id));
    }

    public void delete(Integer id) {
        if (!orderLineRepository.existsById(id))
            throw new RuntimeException("OrderLine not found by id: " + id);
        orderLineRepository.deleteById(id);
    }
}
