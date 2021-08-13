package com.epam.task.web.project.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class CurrencyConverter {

    private static final String EN = "en";
    private static final String FR = "fr";
    private static final String RU = "ru";
    private static final BigDecimal EURO_EXCHANGE_RATE = new BigDecimal("0.80");
    private static final BigDecimal RUBLE_EXCHANGE_RATE = new BigDecimal("70");

    public Optional<BigDecimal> convert(BigDecimal currency, String locale) {
        if (currency == null) {
            return Optional.empty();
        }

        if (locale == null) {
            return Optional.of(currency);
        }

        switch (locale) {
            case FR:
                return Optional.of(currency.multiply(EURO_EXCHANGE_RATE).setScale(2, RoundingMode.HALF_UP));
            case RU:
                return Optional.of(currency.multiply(RUBLE_EXCHANGE_RATE).setScale(2, RoundingMode.HALF_UP));
            case EN:
                return Optional.of(currency.setScale(2, RoundingMode.HALF_UP));
            default:
                throw new IllegalArgumentException("Unknown type of locale to convert currency");
        }

    }

}
