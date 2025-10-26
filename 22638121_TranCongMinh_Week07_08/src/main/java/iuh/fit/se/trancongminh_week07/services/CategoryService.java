package iuh.fit.se.trancongminh_week07.services;

import iuh.fit.se.trancongminh_week07.entities.Category;
import iuh.fit.se.trancongminh_week07.entities.Comment;
import iuh.fit.se.trancongminh_week07.reposities.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // Lấy category theo ID
    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Integer id, Category newCategory) {
        return categoryRepository.findById(id)
                .map(existing -> {
                    existing.setName(newCategory.getName());
                    existing.setProducts(newCategory.getProducts());
                    return categoryRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Category not found by id: " + id));

    }

    public void delete(Integer id){
        if (!categoryRepository.existsById(id)){
            throw new RuntimeException("Category not found by id: "+ id);
        }
        categoryRepository.deleteById(id);
    }
}
