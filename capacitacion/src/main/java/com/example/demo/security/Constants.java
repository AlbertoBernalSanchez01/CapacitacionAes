package com.example.demo.security;

public class Constants {

		//AES
		public static final String SUPER_SECRET_KEY_AES ="9EBBDCD1311B437B6C95EC3764FB4ABC";
		public static final String SALT_AES = "VORTEXBIRDSALTTS";
		public static final String AES_ALGORITHM="AES/CBC/PKCS5Padding";
		public static final String PBKDF2_ALGORITHM="PBKDF2withhmacSHA256";
		
		
		
		private Constants() {
			throw new IllegalStateException("Constants class");
		}

		// Spring Security

		public static final String LOGIN_URL = "/api/v1/auth/loginInit";
		public static final String TEST_URL = "/api/v1/test/**";
		//Path http://localhost:9090/actuator
		public static final String ACTUATOR_URL = "/actuator/**";
		
		//Path http://localhost:9090/swagger-ui/index.html
		public static final String API_DOCS="/v3/api-docs/**";
		public static final String SWAGGER_UI="/swagger-ui/**";
		
		
		public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
		public static final String TOKEN_BEARER_PREFIX = "Bearer ";

		// JWT

		public static final String ISSUER_INFO = "https://zathuracode.org";
		public static final String SUPER_SECRET_KEY = "z4tur4c0dv102023isth3b3stcodeg3n3ratorintheworldofj4v4v0rtex2023";
		public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day
}
