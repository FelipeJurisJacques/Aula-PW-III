package com.example.demo.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.AlunoEntity;
import com.example.demo.service.AlunoService;

@RestController
@RequestMapping(value="/alunos")
public class AlunoResurce {

	@Autowired
	AlunoService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<AlunoEntity> listar() {
		List<AlunoEntity> lista = service.buscar();
		return lista;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<AlunoEntity> buscar(@PathVariable Integer id){
		AlunoEntity objeto = service.buscar(id);
		if(objeto==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(objeto);
	}

}
