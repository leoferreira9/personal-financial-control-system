package com.leonardo.Personal.Financial.Control.System.service;

import com.leonardo.Personal.Financial.Control.System.dto.*;
import com.leonardo.Personal.Financial.Control.System.entity.Account;
import com.leonardo.Personal.Financial.Control.System.entity.Category;
import com.leonardo.Personal.Financial.Control.System.entity.Transaction;
import com.leonardo.Personal.Financial.Control.System.exception.EntityNotFound;
import com.leonardo.Personal.Financial.Control.System.repository.AccountRepository;
import com.leonardo.Personal.Financial.Control.System.repository.CategoryRepository;
import com.leonardo.Personal.Financial.Control.System.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, CategoryRepository categoryRepository){
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
    }

    public TransactionDTO createTransaction(TransactionCreateDTO dto){
        Account account = accountRepository.findById(dto.getAccountId()).orElseThrow(() -> new EntityNotFound("Account not found with ID: " + dto.getAccountId()));
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new EntityNotFound("Category not found with ID: " + dto.getCategoryId()));

        Transaction transaction = new Transaction(dto.getDescription(), dto.getAmount(), dto.getTransactionType(), dto.getDate(), account, category);
        Transaction saved = transactionRepository.save(transaction);
        return new TransactionDTO(saved);
    }

    public TransactionDTO findTransactionById(Long id){
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new EntityNotFound("Transaction not found with ID: " + id));
        return new TransactionDTO(transaction);
    }

    public List<TransactionDTO> findAllTransactions(){
        return transactionRepository.findAll()
                .stream().map(TransactionDTO::new).toList();
    }

    public TransactionDTO updateTransaction(Long id, TransactionCreateDTO dto){
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new EntityNotFound("Transaction not found with ID: " + id));
        Account account = accountRepository.findById(dto.getAccountId()).orElseThrow(() -> new EntityNotFound("Account not found with ID: " + dto.getAccountId()));
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new EntityNotFound("Category not found with ID: " + dto.getCategoryId()));

        transaction.setTransactionType(dto.getTransactionType());
        transaction.setAccount(account);
        transaction.setAmount(dto.getAmount());
        transaction.setDate(dto.getDate());
        transaction.setCategory(category);
        transaction.setDescription(dto.getDescription());

        Transaction saved = transactionRepository.save(transaction);
        return new TransactionDTO(saved);
    }

    public void deleteTransaction(Long id){
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new EntityNotFound("Transaction not found with ID: " + id));
        transactionRepository.delete(transaction);
    }
}
