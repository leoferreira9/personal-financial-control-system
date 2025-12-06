package com.leonardo.Personal.Financial.Control.System.controller;

import com.leonardo.Personal.Financial.Control.System.dto.TransactionCreateDTO;
import com.leonardo.Personal.Financial.Control.System.dto.TransactionDTO;
import com.leonardo.Personal.Financial.Control.System.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> create(@RequestBody @Valid TransactionCreateDTO dto){
        return ResponseEntity.status(201).body(transactionService.createTransaction(dto));
    }

    @GetMapping("/{id}")
    public TransactionDTO findById(@PathVariable Long id){
        return transactionService.findTransactionById(id);
    }

    @GetMapping
    public List<TransactionDTO> findAll(){
        return transactionService.findAllTransactions();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> update(@PathVariable Long id, @RequestBody @Valid TransactionCreateDTO dto){
        return ResponseEntity.status(200).body(transactionService.updateTransaction(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
