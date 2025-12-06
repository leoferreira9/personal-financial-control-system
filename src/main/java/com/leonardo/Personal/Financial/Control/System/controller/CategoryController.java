package com.leonardo.Personal.Financial.Control.System.controller;

import com.leonardo.Personal.Financial.Control.System.dto.CategoryCreateDTO;
import com.leonardo.Personal.Financial.Control.System.dto.CategoryDTO;
import com.leonardo.Personal.Financial.Control.System.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid CategoryCreateDTO dto){
        return ResponseEntity.status(201).body(categoryService.createCategory(dto));
    }

    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable Long id){
        return categoryService.findCategoryById(id);
    }

    public List<CategoryDTO> findAll(){
        return categoryService.findAllCategories();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody @Valid CategoryCreateDTO dto){
        return ResponseEntity.status(200).body(categoryService.updateCategory(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
