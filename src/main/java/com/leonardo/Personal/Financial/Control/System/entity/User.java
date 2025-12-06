package com.leonardo.Personal.Financial.Control.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String name;

    @NotBlank()
    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String password;

    public User(){}

    public User(String name, String email, String password) {
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
