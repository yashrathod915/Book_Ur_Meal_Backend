package com.mindtree.bookyourmeal.modules.restaurant.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.bookyourmeal.exception.ServiceException;
import com.mindtree.bookyourmeal.modules.core.dto.ExceptionDto;
import com.mindtree.bookyourmeal.modules.restaurant.controller.RestaurantController;
import com.mindtree.bookyourmeal.modules.restaurant.exception.RestaurantException;

@RestControllerAdvice(assignableTypes = {RestaurantController.class})
public class RestaurantControllerExceptionHandler 
{
		@ExceptionHandler(RestaurantException.class)
		public ResponseEntity<ExceptionDto> userControllerExceptionHandler(ServiceException e, Throwable cause){
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto(e.getMessage()));
		}

	}


