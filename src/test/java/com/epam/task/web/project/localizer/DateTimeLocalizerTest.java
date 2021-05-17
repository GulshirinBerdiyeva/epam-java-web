package com.epam.task.web.project.localizer;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class DateTimeLocalizerTest {

    private static final String US_LOCALE = "us";
    private static final String FR_LOCALE = "fr";
    private static final String RU_LOCALE = "ru";
    private static final String DATE_TIME = "2021-05-05T18:18:18";
    private static final String EXPECTED_US_DATE_TIME = "May 5, 2021 6:18:18 PM";
    private static final String EXPECTED_FR_DATE_TIME = "5 mai 2021 18:18:18";
    private static final String EXPECTED_RU_DATE_TIME = "05.05.2021 18:18:18";

    private final LocalDateTime dateTime = LocalDateTime.parse(DATE_TIME);

    @Test
    public void localizeDateTimeShouldReturnUsDateTimeWhenUsLocaleApplied() {
        DateTimeLocalizer localizer = new DateTimeLocalizer(US_LOCALE);

        String actual = localizer.localizeDateTime(dateTime);

        Assert.assertEquals(EXPECTED_US_DATE_TIME, actual);
    }

    @Test
    public void localizeDateTimeShouldReturnFrDateTimeWhenFrLocaleApplied() {
        DateTimeLocalizer localizer = new DateTimeLocalizer(FR_LOCALE);

        String actual = localizer.localizeDateTime(dateTime);

        Assert.assertEquals(EXPECTED_FR_DATE_TIME, actual);
    }

    @Test
    public void localizeDateTimeShouldReturnRuDateTimeWhenRuLocaleApplied() {
        DateTimeLocalizer localizer = new DateTimeLocalizer(RU_LOCALE);

        String actual = localizer.localizeDateTime(dateTime);

        Assert.assertEquals(EXPECTED_RU_DATE_TIME, actual);
    }

    @Test
    public void localizeDateTimeShouldReturnUsDateTimeWhenNullApplied() {
        DateTimeLocalizer localizer = new DateTimeLocalizer(null);

        String actual = localizer.localizeDateTime(dateTime);

        Assert.assertEquals(EXPECTED_US_DATE_TIME, actual);
    }

}