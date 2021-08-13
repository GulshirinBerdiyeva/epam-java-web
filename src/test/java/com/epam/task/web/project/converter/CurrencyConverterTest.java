package com.epam.task.web.project.converter;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

public class CurrencyConverterTest {

    private static final String EN = "en";
    private static final String FR = "fr";
    private static final String RU = "ru";
    private static final BigDecimal CURRENCY = new BigDecimal("100");
    private static final BigDecimal EXPECTED_DOLLAR = new BigDecimal("100.00");
    private static final BigDecimal EXPECTED_EURO = new BigDecimal("80.00");
    private static final BigDecimal EXPECTED_RUBLE = new BigDecimal("7000.00");

    private final CurrencyConverter currencyConverter = new CurrencyConverter();

    @Test
    public void convertShouldReturnSameCurrencyWhenEnLocaleApplied() {
        Optional<BigDecimal> actual = currencyConverter.convert(CURRENCY, EN);

        Assert.assertEquals(EXPECTED_DOLLAR, actual.get());
    }

    @Test
    public void convertShouldReturnDollarConvertedToEuroWhenFrLocaleApplied() {
        Optional<BigDecimal> actual = currencyConverter.convert(CURRENCY, FR);

        Assert.assertEquals(EXPECTED_EURO, actual.get());
    }

    @Test
    public void convertShouldReturnDollarConvertedToRoubleWhenRuLocaleApplied() {
        Optional<BigDecimal> actual = currencyConverter.convert(CURRENCY, RU);

        Assert.assertEquals(EXPECTED_RUBLE, actual.get());
    }

    @Test
    public void convertCurrencyShouldReturnOptionalEmptyWhenCurrencyValueNull() {
        Optional<BigDecimal> actual = currencyConverter.convert(null, EN);

        Assert.assertFalse(actual.isPresent());
    }

 @Test
    public void convertCurrencyShouldReturnSameCurrencyWhenLocaleNullApplied() {
        Optional<BigDecimal> actual = currencyConverter.convert(CURRENCY, null);

        Assert.assertEquals(CURRENCY, actual.get());
    }

}