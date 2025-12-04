package com.leonardo.Personal.Financial.Control.System.service;

import com.leonardo.Personal.Financial.Control.System.dto.UserCreateDTO;
import com.leonardo.Personal.Financial.Control.System.dto.UserDTO;
import com.leonardo.Personal.Financial.Control.System.entity.User;
import com.leonardo.Personal.Financial.Control.System.exception.EntityNotFound;
import com.leonardo.Personal.Financial.Control.System.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserCreateDTO dto){
        User user = new User(dto.getName(), dto.getEmail(), dto.getPassword());
        userRepository.save(user);
        return new UserDTO(user);
    }

    public UserDTO findUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFound("User not found with ID: " + id));
        return new UserDTO(user);
    }

    public List<UserDTO> findAllUsers(){
        return userRepository.findAll()
                .stream().map(UserDTO::new)
                .toList();
    }

    public UserDTO updateUser(Long id, UserCreateDTO dto){
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFound("User not found with ID: " + id));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        User saved = userRepository.save(user);

        return new UserDTO(saved);
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFound("User not found with ID: " + id));
        userRepository.delete(user);
    }
}
