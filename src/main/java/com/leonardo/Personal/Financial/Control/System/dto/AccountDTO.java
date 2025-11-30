package com.leonardo.Personal.Financial.Control.System.dto;

import com.leonardo.Personal.Financial.Control.System.enums.AccountType;

import java.math.BigDecimal;

public record AccountDTO(long id, String name, AccountType type, BigDecimal initialBalance, UserDTO user) {}
