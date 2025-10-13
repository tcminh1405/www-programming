package iuh.fit.se.trancongminh_week07.services;

import iuh.fit.se.trancongminh_week07.entities.Product;
import iuh.fit.se.trancongminh_week07.reposities.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Integer id){
        return productRepository.findById(id);
    }

    //thêm sp mới
    public Product create(Product product) {
        return productRepository.save(product);
    }

    //cập nhật sp
    public Product update(Integer id, Product newProduct) {
        return productRepository.findById(id)
                .map(existing ->{
                    existing.setName(newProduct.getName());
                    existing.setPrice(newProduct.getPrice());
                    existing.setInStock(newProduct.isInStock());
                    existing.setCategory(newProduct.getCategory());
                    return  productRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // Xóa sản phẩm theo ID
    public void deleteById(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
