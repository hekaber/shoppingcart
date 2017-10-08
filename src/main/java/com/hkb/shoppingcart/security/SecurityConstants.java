package com.hkb.shoppingcart.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 3600_000; // 1hour
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String HEADER_EXPOSER = "Access-Control-Expose-Headers";
    public static final String SIGN_UP_URL = "/users/signup";
}
