package com.epam.task.web.project.localizer;

import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class Localizer {

    private static final String LOCAL = "local";
    private final ResourceBundle resourceBundle;

    public Localizer(HttpSession session) {
        String localValue = (String) session.getAttribute(LOCAL);
        String local = localValue != null ? LOCAL + "_" + localValue : LOCAL;

        this.resourceBundle = ResourceBundle.getBundle(local);
    }

    public String localize(String key) {
        return resourceBundle.getString(key);
    }

}