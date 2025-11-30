package com.leonardo.Personal.Financial.Control.System.dto;

import com.leonardo.Personal.Financial.Control.System.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(Long id,
                             String description,
                             BigDecimal amount,
                             TransactionType transactionType,
                             LocalDateTime date, AccountDTO account, CategoryDTO category, Long userId) {}
