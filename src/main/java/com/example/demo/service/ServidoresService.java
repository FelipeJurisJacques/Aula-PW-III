package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.example.demo.dao.ServidoresDao;
import com.example.demo.entity.ServidoresEntity;
import com.example.exceptions.ObjNotFoundException;

@Service
public class ServidoresService {
	
	@Autowired
	private ServidoresDao dao;
	
	public List<ServidoresEntity> buscar() {
		return dao.findAll();
	}
	
	public ServidoresEntity buscar(Integer id) {
		Optional<ServidoresEntity> ServidoresDao = dao.findById(id);
		//return ServidoresDao.orElse(null);
		return ServidoresDao.orElseThrow(()-> new ObjNotFoundException("Servidor não encontrado"));
	}
	
	public ServidoresEntity salvar(ServidoresEntity obj) {
		obj.setId(null);
		return dao.save(obj);
	}
	
	public ServidoresEntity atualizar(ServidoresEntity obj) {
		ServidoresEntity servidores_banco = buscar(obj.getId());
		if(obj.getNome() == null) {
			obj.setNome(servidores_banco.getNome());
		}
		if(obj.getEmail() == null) {
			obj.setEmail(servidores_banco.getEmail());
		}
		if(obj.getSenha() == null) {
			obj.setSenha(servidores_banco.getSenha());
		}
		return dao.save(obj);
	}
	
	public Page<ServidoresEntity> buscarPorPagina(
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
