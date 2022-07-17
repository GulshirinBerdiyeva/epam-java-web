package com.epam.task.web.project.locale;

public enum Locale {
    EN, FR, RU;

    public static boolean isValid(String localeValue) {

        if (localeValue != null) {

            for (Locale locale : Locale.values()) {
                if (locale.name().equals(localeValue.toUpperCase())) {
                    return true;
                }
            }
        }

        return false;
    }

}
