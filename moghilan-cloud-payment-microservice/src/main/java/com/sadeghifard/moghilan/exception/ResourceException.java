package com.sadeghifard.moghilan.exception;

public class ResourceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldname;
	private Object fieldValue;
	
	public ResourceException(String resourceName, String fieldName, Object fieldValu) {
		super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldName));
		this.resourceName = resourceName;
		this.fieldname = fieldName;
		this.fieldValue = fieldValue;
	}
}
