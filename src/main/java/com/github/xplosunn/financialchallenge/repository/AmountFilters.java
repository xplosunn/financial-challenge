package com.github.xplosunn.financialchallenge.repository;

import java.math.BigDecimal;

public final class AmountFilters {

    private AmountFilters() {}

    public static AmountFilter biggerThan(final BigDecimal value) {
        return () -> " AND amount > " + value;
    }

    public static AmountFilter lowerThan(final BigDecimal value) {
        return () -> " AND amount < " + value;
    }

    public static AmountFilter between(final BigDecimal min, final BigDecimal max) {
        return () -> biggerThan(max).filterSql() + lowerThan(min).filterSql();
    }

    public static AmountFilter equal(final BigDecimal value) {
        return () -> " AND amount = " + value;
    }
}
