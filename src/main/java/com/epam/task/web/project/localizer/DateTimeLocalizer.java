package com.epam.task.web.project.localizer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateTimeLocalizer {

    private static final String ENGLISH_LOCAL = "us";
    private final String locale;

    public DateTimeLocalizer(String locale) {
        this.locale = locale != null ? locale : ENGLISH_LOCAL;
    }

    public String localizeDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(new Locale(locale));
        return dateTime.format(dateTimeFormatter);
    }

}