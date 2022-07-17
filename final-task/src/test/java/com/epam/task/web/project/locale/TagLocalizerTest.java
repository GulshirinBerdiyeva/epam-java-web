package com.epam.task.web.project.locale;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class TagLocalizerTest {

    private static final String EN = "en";
    private static final String FR = "fr";
    private static final String RU = "ru";
    private static final String KEY = "locale.title.order.music";
    private static final String EXPECTED_EN_VALUE = "Order Music";
    private static final String EXPECTED_FR_VALUE = "Commander de la musique";
    private static final String EXPECTED_RU_VALUE = "Заказ Музыки";

    private final Localizer tagLocalizer = new TagLocalizer();

    @Test
    public void localizeShouldReturnEnStringValueWhenEnLocaleApplied() {
        Optional<String> actual = tagLocalizer.localize(KEY, EN);

        Assert.assertEquals(EXPECTED_EN_VALUE, actual.get());
    }

    @Test
    public void localizeShouldReturnFrStringValueWhenFrLocaleApplied() {
        Optional<String> actual = tagLocalizer.localize(KEY, FR);

        Assert.assertEquals(EXPECTED_FR_VALUE, actual.get());
    }

    @Test
    public void localizeShouldReturnRuStringValueWhenRuLocaleApplied() {
        Optional<String> actual = tagLocalizer.localize(KEY, RU);

        Assert.assertEquals(EXPECTED_RU_VALUE, actual.get());
    }

    @Test
    public void localizeShouldReturnOptionalEmptyWhenValueNull() {
        Optional<String> actual = tagLocalizer.localize(null, EN);

        Assert.assertFalse(actual.isPresent());
    }

    @Test
    public void localizeShouldReturnEnStringValueWhenLocaleNull() {
        Optional<String> actual = tagLocalizer.localize(KEY, null);

        Assert.assertEquals(EXPECTED_EN_VALUE, actual.get());
    }

}