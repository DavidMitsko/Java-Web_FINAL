package com.mitsko.mrdb.dao.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private Properties properties = new Properties();

    public String getValue(String parameter) {
        try(FileInputStream fis = new FileInputStream(Parameter.PROPERTIES_PATH)){
            properties.load(fis);

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        return properties.getProperty(parameter);
    }
}
