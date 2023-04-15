package com.tunewalker.rest.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Decode {

    public static String decodeString(String encoded) throws UnsupportedEncodingException {
        byte[] decoder = Base64.getDecoder().decode(encoded.substring(6));
        return new String(decoder, StandardCharsets.UTF_8);
    }
}
