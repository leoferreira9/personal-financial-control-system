package com.leonardo.Personal.Financial.Control.System.dto;

import com.leonardo.Personal.Financial.Control.System.entity.Category;

public record CategoryDTO(Long id, String name, Long userId) {

    public CategoryDTO(Category category){
        this(category.getId(), category.getName(),category.getUser().getId());
    }
}
