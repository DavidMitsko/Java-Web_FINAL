package com.mitsko.mrdb.dao.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class PropertiesManager {
    private final static Logger logger = LogManager.getLogger(PropertiesManager.class);
    private final static PropertiesManager instance = new PropertiesManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    public static PropertiesManager getInstance() {
        return instance;
    }

    public String getValue(String parameter) {
        logger.debug("Get db parameter " + parameter);
        return bundle.getString(parameter);
    }
}
