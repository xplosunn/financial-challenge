package com.github.xplosunn.financialchallenge.controller;

import com.github.xplosunn.financialchallenge.controller.exception.Invalid;
import com.github.xplosunn.financialchallenge.controller.exception.NotFound;
import com.github.xplosunn.financialchallenge.controller.exception.Unsupported;
import com.github.xplosunn.financialchallenge.model.Transaction;
import com.github.xplosunn.financialchallenge.repository.AmountFilter;
import com.github.xplosunn.financialchallenge.repository.AmountFilters;
import com.github.xplosunn.financialchallenge.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public final class TransactionHistoryController {

    private final TransactionService transactionService;

    public TransactionHistoryController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path = "/user/{userId}/history")
    @ResponseBody
    public List<Transaction> userTransactionHistory(
            @PathVariable("userId") final long userId,
            @RequestParam(value = "days") final Optional<Integer> daysParameter,
            @RequestParam(value = "biggerThan") final Optional<BigDecimal> amountBiggerThan,
            @RequestParam(value = "lowerThan") final Optional<BigDecimal> amountLowerThan,
            @RequestParam(value = "equal") final Optional<BigDecimal> amountEqual) {
        int days = daysParameter.orElse(1);
        if( days > 31) {
            throw new Unsupported.NumberOfDays();
        } else if(days <= 0) {
            throw new Invalid.NumberOfDays();
        } else if (userId <= 0) {
            throw new NotFound.User();
        } else {
            Optional<AmountFilter> filter = filter(amountBiggerThan, amountLowerThan, amountEqual);
            LocalDate since = LocalDate.now().minusDays(days - 1);
            return transactionService.userTransactions(userId, since, filter);
        }
    }

    private Optional<AmountFilter> filter(
            Optional<BigDecimal> amountBiggerThan,
            Optional<BigDecimal> amountLowerThan,
            Optional<BigDecimal> amountEqual) {
        if(amountEqual.isPresent()) {
            return amountEqual.map(AmountFilters::equal);
        } else if(amountBiggerThan.isPresent() && amountLowerThan.isPresent()) {
            Optional.of(AmountFilters.between(amountBiggerThan.get(), amountLowerThan.get()));
        } else if(amountBiggerThan.isPresent()) {
            return amountBiggerThan.map(AmountFilters::biggerThan);
        }
        return amountLowerThan.map(AmountFilters::lowerThan);
    }

}
