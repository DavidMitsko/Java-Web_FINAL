package com.mitsko.mrdb.service.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Crypto {

    public String encode(String str) {
        return DigestUtils.md5Hex(str);
    }
}
