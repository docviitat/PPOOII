package com.ppooii.demo.Config.Model;

public class JwtConstants {

    // Spring Security
    public static final String LOGIN_URL = "/api/auth/authenticate";
    public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";

    // JWT
    // Reused from the existing JWTAuthorizationFilter so both sides sign/verify with the same key.
    public static final String SUPER_SECRET_KEY = "SecretKeyForJWTTokenGenerationSuperSecretPPOOIIProject123456";
    public static final long TOKEN_EXPIRATION_TIME = 864_000_000L; // 10 days
}
