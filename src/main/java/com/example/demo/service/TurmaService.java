package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TurmaDao;
import com.example.demo.entity.TurmaEntity;

@Service
public class TurmaService {
	
	@Autowired
	private TurmaDao dao;
	
	public List<TurmaEntity> buscar() {
		return dao.findAll();
	}
	
	public TurmaEntity buscar(Integer id) {
		Optional<TurmaEntity> TurmaDao = dao.findById(id);
		return TurmaDao.orElse(null);
	}
}
