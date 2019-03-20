package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.ServidoresDao;
import com.example.demo.entity.ServidoresEntity;

@Service
public class ServidoresService {
	
	@Autowired
	private ServidoresDao dao;
	
	public List<ServidoresEntity> buscar() {
		return dao.findAll();
	}
	
	public ServidoresEntity buscar(Integer id) {
		Optional<ServidoresEntity> ServidoresDao = dao.findById(id);
		return ServidoresDao.orElse(null);
	}
}
