package com.aadev.aecomics.helpers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ApiMarvelConfig {

    public static final String BASE_URL = "http://gateway.marvel.com/v1/public/";
    private static final String API_PRIVATE_KEY = "";
    public static final String API_PUBLIC_KEY = "bfa5ce75aaf76c1badbcb88796dfc0e7";

    public static String getHashMd5(String timestamp) {

        String value = timestamp + API_PRIVATE_KEY + API_PUBLIC_KEY;

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
        return hash.toString(16);
    }

}
