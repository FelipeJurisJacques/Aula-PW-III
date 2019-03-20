package com.example.demo;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.dao.CursoDao;
import com.example.demo.dao.TurmaDao;
import com.example.demo.entity.CursoEntity;
import com.example.demo.entity.TurmaEntity;

@SpringBootApplication
public class DemoApplication  implements CommandLineRunner{

	@Autowired
	private CursoDao cursoDAO;

	@Autowired
	private TurmaDao turmaDao;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		CursoEntity curso1 = new CursoEntity(null, "Sistemas Para Internet", "Superior", "Noturno");
		CursoEntity curso2 = new CursoEntity(null, "Processos Químicos", "Superior", "Noturno");
		
		TurmaEntity turma1 = new TurmaEntity(null, "Turma formandos", 5, curso1);
		TurmaEntity turma2 = new TurmaEntity(null, "Turma", 1, curso2);
		TurmaEntity turma3 = new TurmaEntity(null, "Turma", 1, curso2);
		
		curso1.getTurmas().addAll(Arrays.asList(turma1, turma2));
		curso1.getTurmas().addAll(Arrays.asList(turma3));
		
		cursoDAO.saveAll(Arrays.asList(curso1, curso2));
		
		turmaDao.saveAll(Arrays.asList(turma1, turma2, turma3));
	}
}
