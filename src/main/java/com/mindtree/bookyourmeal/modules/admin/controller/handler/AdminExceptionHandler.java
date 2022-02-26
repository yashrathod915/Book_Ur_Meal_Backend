package com.mindtree.bookyourmeal.modules.admin.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.bookyourmeal.modules.admin.exception.AdminException;
import com.mindtree.bookyourmeal.modules.core.dto.ExceptionDto;
@RestControllerAdvice
public class AdminExceptionHandler {	
		@ExceptionHandler(AdminException.class)
		public ResponseEntity<ExceptionDto> adminExceptionHandler(AdminException e, Throwable cause){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto(e.getMessage()));
		}
	}
