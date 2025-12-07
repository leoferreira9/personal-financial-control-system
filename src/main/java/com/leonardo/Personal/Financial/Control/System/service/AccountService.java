package com.leonardo.Personal.Financial.Control.System.service;

import com.leonardo.Personal.Financial.Control.System.dto.AccountCreateDTO;
import com.leonardo.Personal.Financial.Control.System.dto.AccountDTO;
import com.leonardo.Personal.Financial.Control.System.entity.Account;
import com.leonardo.Personal.Financial.Control.System.entity.User;
import com.leonardo.Personal.Financial.Control.System.exception.EntityNotFound;
import com.leonardo.Personal.Financial.Control.System.repository.AccountRepository;
import com.leonardo.Personal.Financial.Control.System.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository){
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public AccountDTO createAccount(AccountCreateDTO dto){
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new EntityNotFound("User not found with ID: " + dto.getUserId()));

        BigDecimal balance = dto.getInitialBalance() != null
                ? dto.getInitialBalance()
                : BigDecimal.ZERO;

        Account account = new Account(dto.getName(), dto.getType(), balance, user);
        Account saved = accountRepository.save(account);
        return new AccountDTO(saved);
    }

    public AccountDTO findAccountById(Long id){
        Account account = accountRepository.findById(id).orElseThrow(() -> new EntityNotFound("Account not found with ID: " + id));
        return new AccountDTO(account);
    }

    public List<AccountDTO> findAllAccounts(){
        return accountRepository.findAll().stream()
                .map(AccountDTO::new).toList();
    }

    public AccountDTO updateAccount(Long id, AccountCreateDTO dto){
        Account account = accountRepository.findById(id).orElseThrow(() -> new EntityNotFound("Account not found with ID: " + id));
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new EntityNotFound("User not found with ID: " + dto.getUserId()));

        account.setName(dto.getName());
        account.setType(dto.getType());
        account.setInitialBalance(dto.getInitialBalance());
        account.setUser(user);

        Account saved = accountRepository.save(account);
        return new AccountDTO(saved);
    }

    public void deleteAccount(Long id){
        Account account = accountRepository.findById(id).orElseThrow(() -> new EntityNotFound("Account not found with ID: " + id));
        accountRepository.delete(account);
    }
}
