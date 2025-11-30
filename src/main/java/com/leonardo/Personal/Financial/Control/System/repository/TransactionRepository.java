package com.leonardo.Personal.Financial.Control.System.repository;

import com.leonardo.Personal.Financial.Control.System.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
