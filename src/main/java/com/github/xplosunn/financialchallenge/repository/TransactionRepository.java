package com.github.xplosunn.financialchallenge.repository;

import com.github.xplosunn.financialchallenge.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    List<Transaction> get(long userId, LocalDate startDateInclusive, LocalDate endDateInclusive, Optional<AmountFilter> filter);

}

