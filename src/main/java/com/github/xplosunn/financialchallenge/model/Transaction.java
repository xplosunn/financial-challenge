package com.github.xplosunn.financialchallenge.model;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public final class Transaction {
    public final long id;
    public final long userId;
    public final BigDecimal amount;
    public final ZonedDateTime timestamp;

    public Transaction(long id, long userId, BigDecimal amount, ZonedDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Transaction[userId=" + userId + ", amount=" + amount.toString() + "]";
    }

    public static final RowMapper<Transaction> rowMapper =
            (resultSet, i) ->
                    new Transaction(
                            resultSet.getLong("id"),
                            resultSet.getLong("user_id"),
                            resultSet.getBigDecimal("amount"),
                            DatabaseConversions.zonedDateTime(resultSet.getTimestamp("timestamp")));
}
