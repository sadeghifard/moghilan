package com.sadeghifard.moghilan.controller.impl;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.sadeghifard.moghilan.controller.IExceptionHandlerController;
import com.sadeghifard.moghilan.exception.ErrorMessage;
import com.sadeghifard.moghilan.exception.ResourceException;

@RestControllerAdvice
public class ExceptionHandlerController implements IExceptionHandlerController {

	@Override
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
										HttpStatus.INTERNAL_SERVER_ERROR.value(),
										LocalDateTime.now(),
										ex.getMessage(),
										request.getDescription(false)
										);
		return message;
	}

	@Override
	@ExceptionHandler(ResourceException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage resourceNotFoundException(ResourceException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
										HttpStatus.NOT_FOUND.value(),
										LocalDateTime.now(),
										ex.getMessage(),
										request.getDescription(false)
										);
		return message;
	}

	@Override
	@ExceptionHandler(ResourceException.class)
	@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
	public ErrorMessage resourseAlreadyReportedException(ResourceException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
										HttpStatus.ALREADY_REPORTED.value(),
										LocalDateTime.now(),
										ex.getMessage(),
										request.getDescription(false)
										);
		return message;
	}

	@Override
	@ExceptionHandler(ResourceException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public ErrorMessage resourseNotAcceptableException(ResourceException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
										HttpStatus.ALREADY_REPORTED.value(),
										LocalDateTime.now(),
										ex.getMessage(),
										request.getDescription(false)
										);
		return message;
	}

}
