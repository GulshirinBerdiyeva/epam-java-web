package com.epam.task.web.project.locale;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Optional;

public class DateTimeLocalizer implements Localizer {

    private static final String EN = "en";

    @Override
    public Optional<String> localize(Object value, String locale) {
        if (value == null) {
            return Optional.empty();
        }

        LocalDateTime dateTime = (LocalDateTime) value;

        String localeValue = locale == null ? EN : locale;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                                                               .withLocale(new Locale(localeValue));

        String localizedDateTime = dateTime.format(dateTimeFormatter);

        return Optional.of(localizedDateTime);
    }

}
