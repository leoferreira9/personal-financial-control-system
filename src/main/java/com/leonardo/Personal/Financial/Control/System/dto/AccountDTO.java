package com.leonardo.Personal.Financial.Control.System.dto;

import com.leonardo.Personal.Financial.Control.System.entity.Account;
import com.leonardo.Personal.Financial.Control.System.enums.AccountType;

import java.math.BigDecimal;

public record AccountDTO(long id, String name, AccountType type, BigDecimal initialBalance, UserDTO user) {

    public AccountDTO(Account account){
        this(account.getId(), account.getName(), account.getType(), account.getInitialBalance(), new UserDTO(account.getUser()));
    }
}
