package com.teg.message.assignment.exception;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
@ExceptionHandler(ConstraintViolationException.class)
 protected ResponseEntity<Object> handleMethodArgumentNotValid(ConstraintViolationException ex) {
	 final Map<String, Object> response = new HashMap<>();
     response.put("message", "Your request contains errors");
     response.put("errors", ex.getConstraintViolations()
             .stream()
             .map(it -> new HashMap<String, String>() {{
                 put(it.getPropertyPath().toString(), it.getMessage());
             }})
             .collect(Collectors.toList())
     );
	    
      return ResponseEntity.badRequest().body(response);
	  }
	 
	
}
