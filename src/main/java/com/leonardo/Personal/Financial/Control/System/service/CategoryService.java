package com.leonardo.Personal.Financial.Control.System.service;

import com.leonardo.Personal.Financial.Control.System.dto.CategoryCreateDTO;
import com.leonardo.Personal.Financial.Control.System.dto.CategoryDTO;
import com.leonardo.Personal.Financial.Control.System.entity.Category;
import com.leonardo.Personal.Financial.Control.System.entity.User;
import com.leonardo.Personal.Financial.Control.System.exception.EntityNotFound;
import com.leonardo.Personal.Financial.Control.System.repository.CategoryRepository;
import com.leonardo.Personal.Financial.Control.System.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository){
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public CategoryDTO createCategory(CategoryCreateDTO dto){
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new EntityNotFound("User not found with ID: " + dto.getUserId()));
        Category category = new Category(dto.getName(), user);
        Category saved = categoryRepository.save(category);
        return new CategoryDTO(saved);
    }

    public CategoryDTO findCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFound("Category not found with ID: " + id));
        return new CategoryDTO(category);
    }

    public List<CategoryDTO> findAllCategories(){
        return categoryRepository.findAll()
                .stream().map(CategoryDTO::new)
                .toList();
    }

    public CategoryDTO updateCategory(Long id, CategoryCreateDTO dto){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFound("Category not found with ID: " + id));
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new EntityNotFound("User not found with ID: " + dto.getUserId()));
        category.setName(dto.getName());
        category.setUser(user);

        Category saved = categoryRepository.save(category);
        return new CategoryDTO(saved);
    }

    public void deleteCategory(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFound("Category not found with ID: " + id));
        categoryRepository.delete(category);
    }
}
