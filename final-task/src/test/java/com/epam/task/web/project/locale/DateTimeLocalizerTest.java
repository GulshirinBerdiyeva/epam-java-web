package com.epam.task.web.project.locale;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Optional;

public class DateTimeLocalizerTest {

    private static final String EN = "en";
    private static final String FR = "fr";
    private static final String RU = "ru";
    private static final String DATE_TIME = "2021-05-05T18:18:18";
    private static final String EXPECTED_EN_DATE_TIME = "May 5, 2021 6:18:18 PM";
    private static final String EXPECTED_FR_DATE_TIME = "5 mai 2021 18:18:18";
    private static final String EXPECTED_RU_DATE_TIME = "05.05.2021 18:18:18";

    private final LocalDateTime dateTime = LocalDateTime.parse(DATE_TIME);
    private final Localizer dateTimeLocalizer = new DateTimeLocalizer();

    @Test
    public void localizeShouldReturnEnDateTimeWhenEnLocaleApplied() {
        Optional<String> actual = dateTimeLocalizer.localize(dateTime, EN);

        Assert.assertEquals(EXPECTED_EN_DATE_TIME, actual.get());
    }

    @Test
    public void localizeShouldReturnFrDateTimeWhenFrLocaleApplied() {
        Optional<String> actual = dateTimeLocalizer.localize(dateTime, FR);

        Assert.assertEquals(EXPECTED_FR_DATE_TIME, actual.get());
    }

    @Test
    public void localizeShouldReturnRuDateTimeWhenRuLocaleApplied() {
        Optional<String> actual = dateTimeLocalizer.localize(dateTime, RU);

        Assert.assertEquals(EXPECTED_RU_DATE_TIME, actual.get());
    }

    @Test
    public void localizeShouldReturnOptionalEmptyWhenValueNull() {
        Optional<String> actual = dateTimeLocalizer.localize(null, EN);

        Assert.assertFalse(actual.isPresent());
    }

    @Test
    public void localizeShouldReturnEnDateTimeWhenLocaleNull() {
        Optional<String> actual = dateTimeLocalizer.localize(dateTime, null);

        Assert.assertEquals(EXPECTED_EN_DATE_TIME, actual.get());
    }

}