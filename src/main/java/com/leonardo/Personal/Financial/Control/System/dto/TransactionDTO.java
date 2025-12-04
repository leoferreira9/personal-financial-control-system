package com.leonardo.Personal.Financial.Control.System.dto;

import com.leonardo.Personal.Financial.Control.System.entity.Transaction;
import com.leonardo.Personal.Financial.Control.System.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(Long id,
                             String description,
                             BigDecimal amount,
                             TransactionType transactionType,
                             LocalDateTime date, AccountDTO account, CategoryDTO category, Long userId) {

    public TransactionDTO(Transaction transaction){
        this(transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getDate(),
                new AccountDTO(transaction.getAccount()),
                new CategoryDTO(transaction.getCategory()),
                transaction.getAccount().getUser().getId());
    }
}
