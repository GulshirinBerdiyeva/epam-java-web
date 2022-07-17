package com.epam.task.web.project.locale;

import java.util.Optional;

/**
 * Implements by definite Localizer classes
 * */
public interface Localizer {
    /**
     * Localize value
     *
     * @param value value for localization
     * @param locale current locale
     * @return String
     * */
    Optional<String> localize(Object value, String locale);

}
