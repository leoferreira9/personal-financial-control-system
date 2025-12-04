package com.leonardo.Personal.Financial.Control.System.dto;

import com.leonardo.Personal.Financial.Control.System.entity.User;

public record UserDTO(Long id, String name, String email) {

    public UserDTO(User user){
        this(user.getId(), user.getName(), user.getEmail());
    }
}
