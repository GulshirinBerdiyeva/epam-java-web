package com.epam.task.web.project.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyConverter {

    private static final String FRANCE_LOCAL = "FR";
    private static final String RUSSIAN_LOCAL = "RU";

    private static final BigDecimal EURO_EXCHANGE_RATE = new BigDecimal("0.85");
    private static final BigDecimal RUBLE_EXCHANGE_RATE = new BigDecimal("76.5");

    public BigDecimal convertPrice(String local, BigDecimal price) {
        if (local == null) {
            return price;
        }
        switch (local) {
            case FRANCE_LOCAL:
                return price.multiply(EURO_EXCHANGE_RATE).setScale(2, RoundingMode.HALF_UP);
            case RUSSIAN_LOCAL:
                return price.multiply(RUBLE_EXCHANGE_RATE).setScale(2, RoundingMode.HALF_UP);
            default:
                return price;
        }
    }

}
