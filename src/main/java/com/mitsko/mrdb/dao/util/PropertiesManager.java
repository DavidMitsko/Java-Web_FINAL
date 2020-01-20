package com.mitsko.mrdb.dao.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    public String getValue(String parameter) {
        Properties properties = new Properties();

        try(FileInputStream fis = new FileInputStream("src/main/resources/db.properties")){
            properties.load(fis);

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        return properties.getProperty(parameter);
    }
}
