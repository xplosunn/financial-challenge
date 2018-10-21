package com.github.xplosunn.financialchallenge.service;

import com.github.xplosunn.financialchallenge.model.Transaction;
import com.github.xplosunn.financialchallenge.repository.TransactionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTests {

    private TransactionRepository mockTransactionRepository(List<Transaction> result) {
        return (userId, startDateInclusive, endDateInclusive, filter) -> result;
    }

    @Test
    public void get() {
        TransactionRepository transactionRepository = mockTransactionRepository(new ArrayList<>());
        TransactionService transactionService = new TransactionServiceImpl(transactionRepository);
        List<Transaction> transactions =
                transactionService.userTransactions(0, LocalDate.now(), LocalDate.now(), Optional.empty());
        Assert.assertTrue(transactions.isEmpty());
    }

}
