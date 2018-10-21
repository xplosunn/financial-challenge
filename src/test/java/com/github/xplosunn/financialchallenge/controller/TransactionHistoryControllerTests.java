package com.github.xplosunn.financialchallenge.controller;


import com.github.xplosunn.financialchallenge.controller.exception.Invalid;
import com.github.xplosunn.financialchallenge.controller.exception.NotFound;
import com.github.xplosunn.financialchallenge.controller.exception.Unsupported;
import com.github.xplosunn.financialchallenge.model.Transaction;
import com.github.xplosunn.financialchallenge.service.TransactionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionHistoryControllerTests {
    private TransactionService mockTransactionService(List<Transaction> result) {
        return (userId, startDate, endDate, filter) -> result;
    }

    private TransactionService zeroInteractionsMockTransactionService() {
        return (userId, startDate, endDate, filter) -> {
            Assert.fail("Did not expect call");
            return null;
        };
    }

    @Test(expected = Unsupported.NumberOfDays.class)
    public void userTransactionHistory32days() {
        TransactionHistoryController transactionHistoryController =
                new TransactionHistoryController(zeroInteractionsMockTransactionService());

        transactionHistoryController.userTransactionHistory(
                1, Optional.of(32), Optional.empty(), Optional.empty(), Optional.empty());
    }

    @Test(expected = Invalid.NumberOfDays.class)
    public void userTransactionHistory0days() {
        TransactionHistoryController transactionHistoryController =
                new TransactionHistoryController(zeroInteractionsMockTransactionService());

        transactionHistoryController.userTransactionHistory(
                1, Optional.of(0), Optional.empty(), Optional.empty(), Optional.empty());
    }

    @Test(expected = NotFound.User.class)
    public void userTransactionHistoryUser0() {
        TransactionHistoryController transactionHistoryController =
                new TransactionHistoryController(zeroInteractionsMockTransactionService());

        transactionHistoryController.userTransactionHistory(
                0, Optional.of(1), Optional.empty(), Optional.empty(), Optional.empty());
    }

    @Test()
    public void userTransactionHistoryEmptyList() {
        TransactionHistoryController transactionHistoryController =
                new TransactionHistoryController(mockTransactionService(new ArrayList<>()));

        List<Transaction> transactions =
                transactionHistoryController.userTransactionHistory(
                        1, Optional.of(1), Optional.empty(), Optional.empty(), Optional.empty());

        Assert.assertTrue(transactions.isEmpty());
    }
}
