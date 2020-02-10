package com.mitsko.mrdb.dao.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private final static Logger logger = LogManager.getLogger(PropertiesManager.class);
    private Properties properties;

    public PropertiesManager(String path) {
        try {
            FileInputStream fis = new FileInputStream("F:\\IntelliJ IDEA Ultimate\\Projects\\Java-Web_FINAL\\src\\main\\resources\\db.properties");
            properties = new Properties();
            properties.load(fis);
            logger.info("Load db parameters");
        } catch (FileNotFoundException ex) {
            logger.error(ex);
            //System.exit(-1);
            //throw new DAOException(ex);
        } catch (IOException ex) {
            logger.error(ex);
            //System.exit(-1);
            //throw new DAOException(ex);
        }
    }

    public String getValue(String parameter) {
        logger.debug("Get db parameter " + parameter);
        return properties.getProperty(parameter);
    }
}
