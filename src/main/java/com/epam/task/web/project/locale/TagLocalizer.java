package com.epam.task.web.project.locale;

import java.util.Optional;
import java.util.ResourceBundle;

public class TagLocalizer implements Localizer {

    private static final String LOCALE = "locale";

    @Override
    public Optional<String> localize(Object value, String locale) {
        if (value == null) {
            return Optional.empty();
        }

        String baseName = locale != null ? LOCALE + "_" + locale : LOCALE;

        ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName);

        String localizedValue = resourceBundle.getString((String) value);

        return Optional.of(localizedValue);
    }

}
