package com.mindtree.bookyourmeal.modules.core.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.bookyourmeal.exception.ServiceException;
import com.mindtree.bookyourmeal.modules.core.dto.ExceptionDto;
import com.mindtree.bookyourmeal.modules.core.exception.CoreException;

@RestControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ExceptionDto> coreExceptionHandler(ServiceException e, Throwable cause){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto(e.getMessage()));
	}
}
