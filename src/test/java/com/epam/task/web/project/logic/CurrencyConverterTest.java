package com.epam.task.web.project.logic;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CurrencyConverterTest {

    private static final String FR = "fr";
    private static final String RU = "ru";
    private static final BigDecimal CURRENCY = new BigDecimal("42");
    private static final BigDecimal EXPECTED_EURO = new BigDecimal("35.70");
    private static final BigDecimal EXPECTED_RUBLE = new BigDecimal("3213.00");

    @Test
    public void convertCurrencyShouldReturnEuroWhenFRLocaleApplied() {
        BigDecimal actual = CurrencyConverter.convertCurrency(FR, CURRENCY);

        Assert.assertEquals(EXPECTED_EURO, actual);
    }

    @Test
    public void convertCurrencyShouldReturnRubleWhenRULocaleApplied() {
        BigDecimal actual = CurrencyConverter.convertCurrency(RU, CURRENCY);

        Assert.assertEquals(EXPECTED_RUBLE, actual);
    }

    @Test
    public void convertCurrencyShouldReturnCurrencyWhenNullApplied() {
        BigDecimal actual = CurrencyConverter.convertCurrency(null, CURRENCY);

        Assert.assertEquals(CURRENCY, actual);
    }

}
