package com.sadeghifard.moghilan.controller;

import org.hibernate.ResourceClosedException;
import org.springframework.web.context.request.WebRequest;

import com.sadeghifard.moghilan.exception.ErrorMessage;
import com.sadeghifard.moghilan.exception.ResourceNotFoundException;
import com.sadeghifard.moghilan.exception.ResourceAlreadyReportedException;
import com.sadeghifard.moghilan.exception.ResourceBadRequestException;
import com.sadeghifard.moghilan.exception.ResourceNotAcceptableException;



public interface IExceptionHandlerController  {
	
	ErrorMessage globalExceptionHandler(Exception ex, WebRequest request);
	ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request);
	ErrorMessage resourseAlreadyReportedException(ResourceAlreadyReportedException ex, WebRequest request);
	ErrorMessage resourseNotAcceptableException(ResourceNotAcceptableException ex, WebRequest request);
	ErrorMessage resourseBadRequestException(ResourceBadRequestException ex, WebRequest request);
	ErrorMessage resourseClosedException(ResourceClosedException ex, WebRequest request);
}
