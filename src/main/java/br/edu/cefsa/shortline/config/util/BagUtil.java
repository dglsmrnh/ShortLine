package br.edu.cefsa.shortline.config.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BagUtil {

    private BagUtil(){}

    public static final String ACCEPT = "A";
    public static final String PENDING = "P";
    public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
}
