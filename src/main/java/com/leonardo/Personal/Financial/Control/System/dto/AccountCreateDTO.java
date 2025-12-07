package com.leonardo.Personal.Financial.Control.System.dto;

import com.leonardo.Personal.Financial.Control.System.enums.AccountType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class AccountCreateDTO {

    @NotBlank
    @Size(max = 150)
    private String name;

    @NotNull
    private AccountType type;

    @Nullable
    private BigDecimal initialBalance;

    @NotNull
    private Long userId;

    public AccountCreateDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
