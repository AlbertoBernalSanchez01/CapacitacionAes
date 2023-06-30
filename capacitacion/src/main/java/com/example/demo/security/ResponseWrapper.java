package com.example.demo.security;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper{
	private ByteArrayOutputStream output;
	private int contentLength;
	private String contentType;

	private PrintWriter pwriter = null;
	private BodyServletOutputStream outpstrm = null;

	public ResponseWrapper(HttpServletResponse response) {
		super(response);
		output = new ByteArrayOutputStream();
	}

	public byte[] getData() {

		try {

			String decrypted = new String(output.toByteArray());
			//String crypted = AesUtil.encrypt(decrypted);
			String crypted = " ";
			return crypted.getBytes();

		} catch (Exception e) {

			return output.toByteArray();
		}
	}

	@Override
	public ServletOutputStream getOutputStream() {

		if (outpstrm == null)
			outpstrm = new BodyServletOutputStream(output);

		return outpstrm;

	}

	@Override
	public PrintWriter getWriter() {
		if (pwriter == null)
			pwriter = new PrintWriter(getOutputStream(), true);
		return pwriter;
	}

	@Override
	public void setContentLength(int length) {
		this.contentLength = length;
		super.setContentLength(length);
	}

	public int getContentLength() {
		return contentLength;
	}

	@Override
	public void setContentType(String type) {
		this.contentType = type;
		super.setContentType(type);
	}

	@Override
	public String getContentType() {
		return contentType;
	}
}
