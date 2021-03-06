package com.example.demo.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.DTO.ServidoresDTO;
import com.example.demo.entity.ServidoresEntity;
import com.example.demo.service.ServidoresService;

@RestController
@RequestMapping(value="/servidores")
public class ServidoresResource {

	@Autowired
	ServidoresService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<ServidoresDTO> listar() {
		List<ServidoresEntity> servidoresEntity = service.buscar();
		List<ServidoresDTO> servidoresDTO = servidoresEntity.stream().map(
				obj -> new ServidoresDTO(obj)).collect(Collectors.toList()
		);				
		return servidoresDTO;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<ServidoresEntity> buscar(@PathVariable Integer id){
		ServidoresEntity objeto = service.buscar(id);
		/*if(objeto==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}*/
		return ResponseEntity.ok(objeto);
	}

	/**
	 * Do cliente vem um json que é transformado em DTO
	 * depois transformado em entity e ai gravano no banco
	 * */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(
			@RequestBody ServidoresDTO objDTO
		){
		ServidoresEntity obj = new ServidoresEntity(
				null, 
				objDTO.getNome(), 
				objDTO.getEmail(), 
				objDTO.getSenha()
		);		
		obj = service.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(
			@RequestBody ServidoresEntity obj,
			@PathVariable Integer id
	){
		obj.setId(id);
		obj = service.atualizar(obj);
		return ResponseEntity.noContent().build(); //retorno codigo 204 (quando executou mas nao deu retorno)
	}
	
	@RequestMapping(value="/paginacao", method=RequestMethod.GET)
	public ResponseEntity<Page<ServidoresDTO>> listarPaginas(
			@RequestParam (value="pagina", defaultValue="0") Integer pagina,
			@RequestParam (value="qtd", defaultValue="15") Integer qtdLinhas,
			@RequestParam (value="ordem", defaultValue="nome") String dir,
			@RequestParam (value="dir", defaultValue="DESC") String orderBy
	){
		Page<ServidoresEntity> listaServidores = service.buscarPorPagina(pagina, qtdLinhas, orderBy, dir);
		Page<ServidoresDTO> listaDTO = listaServidores.map(obj -> new ServidoresDTO(obj));
		return ResponseEntity.ok().body(listaDTO);
	}
}
