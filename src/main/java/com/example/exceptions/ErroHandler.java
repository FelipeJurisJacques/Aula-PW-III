package com.example.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javassist.tools.rmi.ObjectNotFoundException;

@ControllerAdvice
public class ErroHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErroRessource> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		ErroRessource erro = new ErroRessource(
				HttpStatus.NOT_FOUND.value(), 
				e.getMessage()
		);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

}
