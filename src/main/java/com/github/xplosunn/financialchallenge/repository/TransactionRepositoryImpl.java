package com.github.xplosunn.financialchallenge.repository;

import com.github.xplosunn.financialchallenge.model.DatabaseConversions;
import com.github.xplosunn.financialchallenge.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component("transactionRepository")
public final class TransactionRepositoryImpl implements TransactionRepository {

    private final JdbcTemplate jdbcTemplate;

    public TransactionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transaction> get(long userId, LocalDate startDateInclusive, LocalDate endDateInclusive, Optional<AmountFilter> filter) {
        String query =
                "SELECT * FROM transactions WHERE user_id = " + userId +
                        " AND timestamp >= '" + DatabaseConversions.timestampAtDayStart(startDateInclusive)  + "'" +
                        " AND timestamp <= '" + DatabaseConversions.timestampAtDayStart(endDateInclusive.plusDays(1)) + "'" +
                        filter.map(AmountFilter::filterSql).orElse("");
        return jdbcTemplate.query(query, Transaction.rowMapper);
    }


}


