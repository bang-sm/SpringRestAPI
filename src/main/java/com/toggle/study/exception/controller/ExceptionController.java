package com.toggle.study.exception.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.toggle.study.exception.E400_101DataCreateFailedException;
import com.toggle.study.exception.E400_102NotFoundException;
import com.toggle.study.exception.type.ErrorMessageType;
import com.toggle.study.model.reponse.ErrorResponse;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionController {

	static Logger logger = LoggerFactory.getLogger(ExceptionController.class);

	@ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<ErrorResponse> handleException(HttpServletResponse response, Exception e) {
		logger.error("Interal Error : {}", e);
		return new ResponseEntity<>(new ErrorResponse(ErrorMessageType.E500_101.getCode(), ErrorMessageType.E500_101.getLabel()),
        HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(E400_101DataCreateFailedException.class)
    public ResponseEntity<ErrorResponse> e400_101DataCreateFailedException(HttpServletResponse response, E400_101DataCreateFailedException e) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ErrorMessageType.E400_101.getCode(), String.format(ErrorMessageType.E400_101.getLabel(), e.getMessage())),
        HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(E400_102NotFoundException.class)
    public ResponseEntity<ErrorResponse> e400_102NotFoundException(HttpServletResponse response, E400_102NotFoundException e) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ErrorMessageType.E400_102.getCode(), ErrorMessageType.E400_102.getLabel()),
        HttpStatus.BAD_REQUEST);
	}
	

}
