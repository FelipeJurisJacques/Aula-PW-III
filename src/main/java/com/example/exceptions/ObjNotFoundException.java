package com.example.exceptions;

public class ObjNotFoundException extends RuntimeException{
	private static final long serivalVersionUid = 1L;
	
	public ObjNotFoundException(String msg) {
		super(msg);
	}
}
