package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
	
	public CursoEntity atualizar(CursoEntity obj) {
		CursoEntity curso_banco = buscar(obj.getId());
		if(obj.getNome() == null) {
			obj.setNome(curso_banco.getNome());
		}
		if(obj.getNivel() == null) {
			obj.setNivel(curso_banco.getNivel());
		}
		if(obj.getTurno() == null) {
			obj.setTurno(curso_banco.getTurno());
		}
		return dao.save(obj);
	}
	
	public Page<CursoEntity> buscarPorPagina(
			Integer pagina,
			Integer qtdLinhas,
			String dir,
			String orderBy
	){
		PageRequest pageRequest = PageRequest.of(
				pagina, 
				qtdLinhas, 
				Direction.valueOf(dir), 
				orderBy
		);
		return dao.findAll(pageRequest);
	}
}
