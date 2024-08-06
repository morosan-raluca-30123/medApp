package com.proiect.practica.medapp.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoriesRepository;

    public List<Category> getAllCategories() {
        return categoriesRepository.findAll();
    }

    public Optional<Category> getCategoryById(Integer id) {
        return categoriesRepository.findById(id);
    }

    public Category saveCategory(Category category) {
        return categoriesRepository.save(category);
    }

    public void deleteCategory(Integer id) {
        categoriesRepository.deleteById(id);
    }
}