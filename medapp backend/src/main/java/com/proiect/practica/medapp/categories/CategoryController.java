package com.proiect.practica.medapp.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoriesService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoriesService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        Optional<Category> category = categoriesService.getCategoryById(id);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoriesService.saveCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody Category categoryDetails) {
        Optional<Category> existingCategory = categoriesService.getCategoryById(id);

        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            category.setName(categoryDetails.getName());
            category.setDescription(categoryDetails.getDescription());
            Category updatedCategory = categoriesService.saveCategory(category);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Integer id) {
        categoriesService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}