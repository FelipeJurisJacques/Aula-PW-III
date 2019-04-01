package com.example.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroRessource> validacao(MethodArgumentNotValidException e, HttpServletRequest request){
		ErroValidacao erros = new ErroValidacao(HttpStatus.BAD_REQUEST.value(), "Erro de validação");
		//Recuperando todos os campos que "deram" execao e adicionandona lista personalizada
		for(FieldError item: e.getBindingResult().getFieldErrors()) {
			erros.addErro(item.getField(), item.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
	}
}
