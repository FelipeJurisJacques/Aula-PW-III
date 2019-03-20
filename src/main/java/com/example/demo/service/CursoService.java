package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.CursoDao;
import com.example.demo.entity.CursoEntity;
import com.example.exceptions.ObjNotFoundException;

@Service
public class CursoService {
	
	@Autowired
	private CursoDao dao;
	
	public List<CursoEntity> buscar() {
		return dao.findAll();
	}
	
	public CursoEntity buscar(Integer id) {
		Optional<CursoEntity> curso = dao.findById(id);
		//return curso.orElse(null);
		return curso.orElseThrow(()-> new ObjNotFoundException("Curso não encontrado"));
	}
	
	public CursoEntity salvar(CursoEntity obj) {
		obj.setId(null);
		return dao.save(obj);
	}
}
