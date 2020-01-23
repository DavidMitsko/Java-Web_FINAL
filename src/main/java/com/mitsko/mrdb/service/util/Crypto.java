package com.mitsko.mrdb.service.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Crypto {

    public String encode(String str) {
        String md5Hex = DigestUtils.md5Hex(str);
        return str;
    }
}
