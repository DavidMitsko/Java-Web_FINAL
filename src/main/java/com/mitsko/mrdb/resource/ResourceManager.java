package com.mitsko.mrdb.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {
    private final static ResourceManager instance = new ResourceManager();
    private final String resourcePath = "text";
    private ResourceBundle resourceBundle;

    private ResourceManager() {
        resourceBundle = ResourceBundle.getBundle(resourcePath, Locale.getDefault());
    }

    public static ResourceManager getInstance() {
        return instance;
    }

    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourcePath, locale);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }

    public Locale getCurrentLocale() {
        return resourceBundle.getLocale();
    }
}
