package com.leonardo.Personal.Financial.Control.System.controller;

import com.leonardo.Personal.Financial.Control.System.dto.AccountCreateDTO;
import com.leonardo.Personal.Financial.Control.System.dto.AccountDTO;
import com.leonardo.Personal.Financial.Control.System.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> create(@RequestBody @Valid AccountCreateDTO dto){
        return ResponseEntity.status(201).body(accountService.createAccount(dto));
    }

    @GetMapping("/{id}")
    public AccountDTO findById(@PathVariable Long id){
        return accountService.findAccountById(id);
    }

    @GetMapping
    public List<AccountDTO> findAll(){
        return accountService.findAllAccounts();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> update(@PathVariable Long id, @RequestBody @Valid AccountCreateDTO dto){
        return ResponseEntity.status(200).body(accountService.updateAccount(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
