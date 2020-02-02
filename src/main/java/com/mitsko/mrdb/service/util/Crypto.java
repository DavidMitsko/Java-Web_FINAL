package com.mitsko.mrdb.service.util;


import org.mindrot.jbcrypt.BCrypt;

public class Crypto {
    private final String gensalt;

    public Crypto() {
        gensalt = BCrypt.gensalt();
    }

    public String encode(String password) {
        return BCrypt.hashpw(password, gensalt);
    }

    public boolean checkPassword(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
