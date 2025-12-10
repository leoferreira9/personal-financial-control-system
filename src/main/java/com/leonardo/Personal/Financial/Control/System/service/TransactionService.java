package com.leonardo.Personal.Financial.Control.System.service;

import com.leonardo.Personal.Financial.Control.System.dto.*;
import com.leonardo.Personal.Financial.Control.System.entity.Account;
import com.leonardo.Personal.Financial.Control.System.entity.Category;
import com.leonardo.Personal.Financial.Control.System.entity.Transaction;
import com.leonardo.Personal.Financial.Control.System.enums.TransactionType;
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

        if(account.getUser().getId() != dto.getUserId()) throw new RuntimeException("Account does not belong to this user!");
        if(category.getUser().getId() != dto.getUserId()) throw new RuntimeException("Category does not belong to this user!");

        if(dto.getTransactionType().equals(TransactionType.INCOME)){
            account.setInitialBalance(account.getInitialBalance().add(dto.getAmount()));
            accountRepository.save(account);
        } else if (dto.getTransactionType().equals(TransactionType.EXPENSE)) {
            if(account.getInitialBalance().compareTo(dto.getAmount()) >= 0){
                account.setInitialBalance(account.getInitialBalance().subtract(dto.getAmount()));
                accountRepository.save(account);
            }else {
                throw new RuntimeException("insufficient balance!");
            }
        }

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

    public void deleteTransaction(Long id){
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new EntityNotFound("Transaction not found with ID: " + id));
        transactionRepository.delete(transaction);
    }
}
