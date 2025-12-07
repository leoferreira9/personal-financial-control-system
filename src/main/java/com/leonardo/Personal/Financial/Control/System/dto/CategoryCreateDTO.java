package com.leonardo.Personal.Financial.Control.System.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryCreateDTO {

    @NotBlank
    @Size(max = 150)
    private String name;

    @NotNull
    private Long userId;

    public CategoryCreateDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
