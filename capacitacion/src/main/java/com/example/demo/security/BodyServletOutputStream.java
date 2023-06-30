package com.example.demo.security;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;

public class BodyServletOutputStream  extends ServletOutputStream {
	private DataOutputStream stream; 

    public BodyServletOutputStream(OutputStream output) { 
      stream = new DataOutputStream(output); 
    }

    @Override
    public void write(int b) throws IOException  {
        stream.write(b); 
    }
    
    @Override
    public void write(byte[] b) throws IOException  { 
        stream.write(b); 
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException  {
        stream.write(b,off,len); 
    }

    @Override
    public boolean isReady() {
        // TODO Auto-generated method stub
        return true;
    }
    

	@Override
	public void setWriteListener(WriteListener listener) {
		// TODO Auto-generated method stub
		
	}
}
