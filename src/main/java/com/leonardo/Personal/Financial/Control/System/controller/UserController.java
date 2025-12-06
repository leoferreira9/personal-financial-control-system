package com.leonardo.Personal.Financial.Control.System.controller;

import com.leonardo.Personal.Financial.Control.System.dto.UserCreateDTO;
import com.leonardo.Personal.Financial.Control.System.dto.UserDTO;
import com.leonardo.Personal.Financial.Control.System.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserCreateDTO dto){
        UserDTO createdUser = userService.createUser(dto);
        return ResponseEntity.status(201).body(createdUser);
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @GetMapping
    public List<UserDTO> findAll(){
        return userService.findAllUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserCreateDTO dto){
        UserDTO updatedUser = userService.updateUser(id, dto);
        return ResponseEntity.status(200).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
