package com.instaton.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.instaton.response.InstatonResponse;
import com.instaton.response.InstatonResponseBuilder;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

	private final Log log = LogFactory.getLog(ExceptionHandlingController.class);

	private final String errorMessage = "Operation Failed";

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(InstatonException.class)
	public ResponseEntity<InstatonResponse> handleException(InstatonException exception) {
		log.error(errorMessage, exception);
		InstatonResponse response = InstatonResponseBuilder.begin().code(exception.getReturnCode()).message(exception.getResponseMessage()).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@ExceptionHandler(Exception.class)
	public ResponseEntity<InstatonResponse<Object>> handleException(Exception exception) {
		log.error(errorMessage, exception);
		InstatonResponse<Object> response = InstatonResponseBuilder.begin().code(InstatonReturnCode.GENERIC_ERROR).message(exception.getMessage()).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<InstatonResponse<Object>> handleException(AccessDeniedException exception) {
		log.error(errorMessage, exception);
		InstatonResponse<Object> response = InstatonResponseBuilder.begin().code(InstatonReturnCode.ACCESS_DENIED_EXCEPTION).message(exception.getMessage()).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
