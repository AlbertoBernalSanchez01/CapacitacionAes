package com.example.demo.security;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FiltroAes implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest rqs = (HttpServletRequest) request;
		
		
		if(rqs.getMethod().equalsIgnoreCase("POST")) {
			RequestWrapper rw= new RequestWrapper(rqs);
			
			chain.doFilter(rw, response);
			
		}
		chain.doFilter(request, response);
		
	}

}
