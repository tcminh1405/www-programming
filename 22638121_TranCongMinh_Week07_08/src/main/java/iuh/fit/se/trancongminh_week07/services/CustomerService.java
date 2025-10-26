package iuh.fit.se.trancongminh_week07.services;

import iuh.fit.se.trancongminh_week07.entities.Customer;
import iuh.fit.se.trancongminh_week07.reposities.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    //Lấy tất cả khách hàng
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    // Tìm theo ID
    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    //Thêm khách hàng mới
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    //Cập nhật thông tin khách hàng
    public Customer update(Integer id, Customer newCustomer) {
        return customerRepository.findById(id)
                .map(c -> {
                    c.setName(newCustomer.getName());
                    c.setCustomerSince(newCustomer.getCustomerSince());

                    return customerRepository.save(c);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found by id: " + id));
    }

    //Xóa khách hàng
    public void delete(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found by id: " + id);
        }
        customerRepository.deleteById(id);
    }

    public Customer getCurrentCustomer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return customerRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng với username: " + username));
    }
}
