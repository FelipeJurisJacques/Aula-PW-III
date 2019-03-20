package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.AlunoDao;
import com.example.demo.entity.AlunoEntity;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoDao dao;
	
	public List<AlunoEntity> buscar() {
		return dao.findAll();
	}
	
	public AlunoEntity buscar(Integer id) {
		Optional<AlunoEntity> AlunoDao = dao.findById(id);
		return AlunoDao.orElse(null);
	}
}
