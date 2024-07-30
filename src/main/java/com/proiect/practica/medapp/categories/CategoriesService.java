package com.proiect.practica.medapp.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    public Optional<Categories> getCategoryById(Integer id) {
        return categoriesRepository.findById(id);
    }

    public Categories saveCategory(Categories category) {
        return categoriesRepository.save(category);
    }

    public void deleteCategory(Integer id) {
        categoriesRepository.deleteById(id);
    }
}