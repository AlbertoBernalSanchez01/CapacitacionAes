package com.example.demo.security;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FiltroAesResponse implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		ResponseWrapper responseWrapper= new ResponseWrapper(res);
		
		chain.doFilter(request, responseWrapper);
		
		OutputStream out = res.getOutputStream();
        out.write(responseWrapper.getData());
        out.close();
		
	}

}
