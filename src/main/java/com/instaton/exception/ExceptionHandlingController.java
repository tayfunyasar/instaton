package com.instaton.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.instaton.response.LoyaltyResponse;
import com.instaton.response.LoyaltyResponseBuilder;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

	private final Log log = LogFactory.getLog(ExceptionHandlingController.class);

	private final String errorMessage = "Operation Failed";

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(LoyaltyException.class)
	public ResponseEntity<LoyaltyResponse> handleException(LoyaltyException exception) {
		log.error(errorMessage, exception);
		LoyaltyResponse response = LoyaltyResponseBuilder.begin().code(exception.getReturnCode()).message(exception.getResponseMessage()).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@ExceptionHandler(Exception.class)
	public ResponseEntity<LoyaltyResponse<Object>> handleException(Exception exception) {
		log.error(errorMessage, exception);
		LoyaltyResponse<Object> response = LoyaltyResponseBuilder.begin().code(LoyaltyReturnCode.GENERIC_ERROR).message(exception.getMessage()).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<LoyaltyResponse<Object>> handleException(AccessDeniedException exception) {
		log.error(errorMessage, exception);
		LoyaltyResponse<Object> response = LoyaltyResponseBuilder.begin().code(LoyaltyReturnCode.ACCESS_DENIED_EXCEPTION).message(exception.getMessage()).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
