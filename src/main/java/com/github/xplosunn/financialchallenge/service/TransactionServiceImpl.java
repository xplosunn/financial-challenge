package com.github.xplosunn.financialchallenge.service;

import com.github.xplosunn.financialchallenge.model.Transaction;
import com.github.xplosunn.financialchallenge.repository.AmountFilter;
import com.github.xplosunn.financialchallenge.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component("transactionService")
public final class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> userTransactions(long userId, LocalDate startDate, LocalDate endDate, Optional<AmountFilter> filter) {
        return transactionRepository.get(userId, startDate, endDate, filter);
    }
}

