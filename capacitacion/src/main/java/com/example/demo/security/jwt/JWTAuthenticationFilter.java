package com.example.demo.security.jwt;

import static  com.example.demo.security.Constants.HEADER_AUTHORIZACION_KEY;
import static  com.example.demo.security.Constants.ISSUER_INFO;
import static  com.example.demo.security.Constants.SUPER_SECRET_KEY;
import static  com.example.demo.security.Constants.TOKEN_BEARER_PREFIX;
import static  com.example.demo.security.Constants.TOKEN_EXPIRATION_TIME;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.SecretKey;

import com.example.demo.domain.UserApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{


	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
		try {
			UserApplication credenciales = new ObjectMapper().readValue(request.getInputStream(), UserApplication.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credenciales.getUsername(), credenciales.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,Authentication auth) throws IOException, ServletException {

		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SUPER_SECRET_KEY));

		String token = Jwts.builder()
				.setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
				.setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
				.signWith(key)
				.compact();

		//Se registra el token en el body
		response.setContentType("application/json");
		response.getWriter().write("{\"token\":"+"\""+TOKEN_BEARER_PREFIX + token+"\"}");
		//Se registra el token en el Header
		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + token);
		response.getWriter().flush();

	}
}
