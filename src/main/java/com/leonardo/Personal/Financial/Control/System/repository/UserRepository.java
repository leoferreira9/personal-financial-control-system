package com.leonardo.Personal.Financial.Control.System.repository;

import com.leonardo.Personal.Financial.Control.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
