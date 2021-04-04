package com.epam.task.web.project.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyConverter {

    private static final String FRANCE_LOCAL = "FR";
    private static final String RUSSIAN_LOCAL = "RU";

    private static final BigDecimal EURO_EXCHANGE_RATE = new BigDecimal(0.85);
    private static final BigDecimal RUBLE_EXCHANGE_RATE = new BigDecimal(76.5);

    public BigDecimal convert(String localType, BigDecimal currency) {

        switch (localType) {
            case FRANCE_LOCAL:
                return currency.multiply(EURO_EXCHANGE_RATE).setScale(2, RoundingMode.HALF_UP);
            case RUSSIAN_LOCAL:
                return currency.multiply(RUBLE_EXCHANGE_RATE).setScale(2, RoundingMode.HALF_UP);
            default:
                return currency;
        }

    }

}
