package com.mitsko.mrdb.service.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class Crypto {
    private final static Logger logger = LogManager.getLogger(Crypto.class);

    public String encode(String password) {
        String code = BCrypt.hashpw(password, BCrypt.gensalt());
        logger.debug("Encode password");
        return code;
    }

    public boolean checkPassword(String password, String hashPassword) {
        boolean flag = BCrypt.checkpw(password, hashPassword);
        logger.debug("Check password");
        return flag;
    }
}
