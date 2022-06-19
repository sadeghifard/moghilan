package com.sadeghifard.moghilan.controller;

import org.springframework.web.context.request.WebRequest;

import com.sadeghifard.moghilan.exception.ErrorMessage;
import com.sadeghifard.moghilan.exception.ResourceException;

public interface IExceptionHandlerController  {
	
	public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request);
	public ErrorMessage resourceNotFoundException(ResourceException ex, WebRequest request);
	public ErrorMessage resourseAlreadyReportedException(ResourceException ex, WebRequest request);
	public ErrorMessage resourseNotAcceptableException(ResourceException ex, WebRequest request);
}
