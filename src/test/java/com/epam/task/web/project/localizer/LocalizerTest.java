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

    private final HttpSession session = Mockito.mock(HttpSession.class);
    
    @Test
    public void localizeShouldReturnUsStringValueWhenUsLocaleApplied() {
        //given
        when(session.getAttribute(LOCAL)).thenReturn(US);
        Localizer localizer = new Localizer(session);

        //when
        String actual = localizer.localize(KEY);

        //then
        Assert.assertEquals(EXPECTED_US_VALUE, actual);
    }

    @Test
    public void localizeShouldReturnFrStringValueWhenFrLocaleApplied() {
        //given
        when(session.getAttribute(LOCAL)).thenReturn(FR);
        Localizer localizer = new Localizer(session);

        //when
        String actual = localizer.localize(KEY);

        //then
        Assert.assertEquals(EXPECTED_FR_VALUE, actual);
    }

    @Test
    public void localizeShouldReturnRuStringValueWhenRuLocaleApplied() {
        //given
        when(session.getAttribute(LOCAL)).thenReturn(RU);
        Localizer localizer = new Localizer(session);

        //when
        String actual = localizer.localize(KEY);

        //then
        Assert.assertEquals(EXPECTED_RU_VALUE, actual);
    }

    @Test
    public void localizeShouldReturnUsStringValueWhenNullApplied() {
        //given
        when(session.getAttribute(LOCAL)).thenReturn(null);
        Localizer localizer = new Localizer(session);

        //when
        String actual = localizer.localize(KEY);

        //then
        Assert.assertEquals(EXPECTED_US_VALUE, actual);
    }

}