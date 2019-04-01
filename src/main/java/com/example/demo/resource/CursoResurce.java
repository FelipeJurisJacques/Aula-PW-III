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
import com.example.DTO.CursoDTO;
import com.example.demo.entity.CursoEntity;
import com.example.demo.service.CursoService;

@RestController
@RequestMapping(value="/cursos")
public class CursoResurce {

	@Autowired
	CursoService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<CursoDTO> listar() {
		List<CursoEntity> listaEntity = service.buscar();
		List<CursoDTO> listaDTO = listaEntity.stream().map(
				obj -> new CursoDTO(obj)).collect(Collectors.toList()
		);				
		return listaDTO;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<CursoEntity> buscar(@PathVariable Integer id){
		CursoEntity objeto = service.buscar(id);
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
			@RequestBody CursoDTO objDTO
		){
		CursoEntity obj = new CursoEntity(
				null, 
				objDTO.getNome(), 
				objDTO.getNivel(), 
				objDTO.getTurno()
		);		
		obj = service.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(
			@RequestBody CursoEntity obj,
			@PathVariable Integer id
	){
		obj.setId(id);
		obj = service.atualizar(obj);
		return ResponseEntity.noContent().build(); //retorno codigo 204 (quando executou mas nao deu retorno)
	}
	
	@RequestMapping(value="/paginacao", method=RequestMethod.GET)
	public ResponseEntity<Page<CursoDTO>> listarPaginas(
			@RequestParam (value="pagina", defaultValue="0") Integer pagina,
			@RequestParam (value="qtd", defaultValue="15") Integer qtdLinhas,
			@RequestParam (value="ordem", defaultValue="nome") String dir,
			@RequestParam (value="dir", defaultValue="DESC") String orderBy
	){
		Page<CursoEntity> listaCursos = service.buscarPorPagina(pagina, qtdLinhas, orderBy, dir);
		Page<CursoDTO> listaDTO = listaCursos.map(obj -> new CursoDTO(obj));
		return ResponseEntity.ok().body(listaDTO);
	}	
}
