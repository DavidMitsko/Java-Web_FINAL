package com.mitsko.mrdb.dao.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private Properties properties;

    public PropertiesManager(String path) {
        try {
            FileInputStream fis = new FileInputStream("F:\\IntelliJ IDEA Ultimate\\Projects\\Java-Web_FINAL\\src\\main\\resources\\db.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getValue(String parameter) {
        return properties.getProperty(parameter);
    }
}
