package com.example.CategoryService.service;

import com.example.CategoryService.model.Category;
import com.example.CategoryService.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Category createCategory(Category category) {
        // check if category with same name already exists
        Optional<Category> existing = categoryRepo.findByName(category.getName());
        if (existing.isPresent()) {
            throw new RuntimeException("Category already exists: " + category.getName());
        }
        return categoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setName(updatedCategory.getName());
        return categoryRepo.save(category);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepo.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepo.deleteById(id);
    }
}
