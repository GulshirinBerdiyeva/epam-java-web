package com.epam.task.web.project.localizer;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.when;

public class LocalizerTest {

    private static final String LOCAL = "local";
    private static final String US = "us";
    private static final String FR = "fr";
    private static final String RU = "ru";
    private static final String KEY = "local.title.order.music";
    private static final String EXPECTED_US_VALUE = "Order Music";
    private static final String EXPECTED_FR_VALUE = "Commander de la musique";
    private static final String EXPECTED_RU_VALUE = "Заказ Музыки";

    private HttpSession session = Mockito.mock(HttpSession.class);
    
    @Test
    public void localizeShouldReturnUSStringValueWhenUSLocaleApplied() {
        when(session.getAttribute(LOCAL)).thenReturn(US);
        Localizer localizer = new Localizer(session);

        String actual = localizer.localize(KEY);

        Assert.assertEquals(EXPECTED_US_VALUE, actual);
    }

    @Test
    public void localizeShouldReturnFRStringValueWhenFRLocaleApplied() {
        when(session.getAttribute(LOCAL)).thenReturn(FR);
        Localizer localizer = new Localizer(session);

        String actual = localizer.localize(KEY);

        Assert.assertEquals(EXPECTED_FR_VALUE, actual);
    }

    @Test
    public void localizeShouldReturnRUStringValueWhenRULocaleApplied() {
        when(session.getAttribute(LOCAL)).thenReturn(RU);
        Localizer localizer = new Localizer(session);

        String actual = localizer.localize(KEY);

        Assert.assertEquals(EXPECTED_RU_VALUE, actual);
    }

    @Test
    public void localizeShouldReturnUSStringValueWhenNullApplied() {
        when(session.getAttribute(LOCAL)).thenReturn(null);
        Localizer localizer = new Localizer(session);

        String actual = localizer.localize(KEY);

        Assert.assertEquals(EXPECTED_US_VALUE, actual);
    }

}
