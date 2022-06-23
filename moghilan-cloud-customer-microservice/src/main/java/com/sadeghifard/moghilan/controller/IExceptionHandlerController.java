package com.sadeghifard.moghilan.controller;

import org.springframework.web.context.request.WebRequest;

import com.sadeghifard.moghilan.exception.ErrorMessage;
import com.sadeghifard.moghilan.exception.ResourceNotFoundException;
import com.sadeghifard.moghilan.exception.ResourceAlreadyReportedException;
import com.sadeghifard.moghilan.exception.ResourceBadRequestException;
import com.sadeghifard.moghilan.exception.ResourceNotAcceptableException;



public interface IExceptionHandlerController  {
	
	public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request);
	public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request);
	public ErrorMessage resourseAlreadyReportedException(ResourceAlreadyReportedException ex, WebRequest request);
	public ErrorMessage resourseNotAcceptableException(ResourceNotAcceptableException ex, WebRequest request);
	public ErrorMessage resourseBadRequestException(ResourceBadRequestException ex, WebRequest request);
}
