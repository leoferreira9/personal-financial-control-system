package com.leonardo.Personal.Financial.Control.System.repository;

import com.leonardo.Personal.Financial.Control.System.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {}
