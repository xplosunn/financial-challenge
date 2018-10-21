package com.github.xplosunn.financialchallenge.service;

import com.github.xplosunn.financialchallenge.model.Transaction;
import com.github.xplosunn.financialchallenge.repository.AmountFilter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionService {

    List<Transaction> userTransactions(long userId, LocalDate startDate, LocalDate endDate, Optional<AmountFilter> filter);

    default List<Transaction> userTransactions(long userId, LocalDate since, Optional<AmountFilter> filter) {
        return userTransactions(userId, since, LocalDate.now(), filter);
    }
}
