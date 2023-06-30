package com.example.demo.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper{

	private boolean isString;
	
	public RequestWrapper(HttpServletRequest request) {
		super(request);
		isString = request.getHeader("Content-Type").equals("application/json");
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		
		final ServletInputStream inputStream= super.getInputStream();
		
		String string = new String(inputStream.readAllBytes());
		
		String decrypBody="";
		try {
			if(isString && string.startsWith("\"")) {
				string = string.substring(1, string.length()-1);
			}
			//decrypBody = AesUtil.decrypt(string);
		} catch (Exception e) {
			decrypBody=string;
		}
		
		final InputStream innerStream=new ByteArrayInputStream(decrypBody.getBytes());
		
		return new ServletInputStream() {
			
			@Override
			public int read() throws IOException {
				return innerStream.read();
			}
			
			@Override
			public void setReadListener(ReadListener listener) {
				inputStream.setReadListener(listener);
				
			}
			
			@Override
			public boolean isReady() {
				
				return inputStream.isReady();
			}
			
			@Override
			public boolean isFinished() {
				
				return inputStream.isFinished();
			}
		};
	}

}
