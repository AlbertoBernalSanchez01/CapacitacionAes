package com.example.demo.security.jwt;

import static com.example.demo.security.Constants.HEADER_AUTHORIZACION_KEY;
import static com.example.demo.security.Constants.SUPER_SECRET_KEY;
import static com.example.demo.security.Constants.TOKEN_BEARER_PREFIX;

import java.io.IOException;
import java.util.ArrayList;

import javax.crypto.SecretKey;

import com.example.demo.domain.UserApplication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * @author Zathura Code Generator Version 23.05 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2023-06-16T14:55:15.569972700
 *
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String header = req.getHeader(HEADER_AUTHORIZACION_KEY);

		if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

		String token = request.getHeader(HEADER_AUTHORIZACION_KEY);
		if (token != null) {
			// Se procesa el token y se recupera el usuario.
			SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SUPER_SECRET_KEY));
			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build()
					.parseClaimsJws(token.replace(TOKEN_BEARER_PREFIX, ""));

			UserApplication authDTO = new UserApplication();
			

			return new UsernamePasswordAuthenticationToken(authDTO, null, new ArrayList<>());
		}
		return null;
	}
}
