package br.edu.univille.microservcurso.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.edu.univille.microservcurso.entity.Professor;
import br.edu.univille.microservcurso.repository.ProfessorRepository;
import br.edu.univille.microservcurso.service.ProfessorService;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;

@Service
public class ProfessorServiceImpl implements ProfessorService{

    @Autowired
    private ProfessorRepository repository;
    private DaprClient client = new DaprClientBuilder().build();
    @Value("${app.component.topic.professor}")
    private String TOPIC_NAME;
    @Value("${app.component.service}")
	private String PUBSUB_NAME;

    @Override
    public List<Professor> getAll() {
        var iterador = repository.findAll();
        List<Professor> listaProfessores = new ArrayList<>();

        iterador.forEach(listaProfessores::add);

        return listaProfessores;
    }

    @Override
    public Professor getById(String id) {
        var professor = repository.findById(id);
        if(professor.isPresent())
            return professor.get();
        return null;
    }

    @Override
    public Professor saveNew(Professor professor) {
	    professor.setId(null);
        professor = repository.save(professor);
        publicarAtualizacao(professor);
	    return professor;
    }

    @Override
    public Professor update(String id, Professor professor) {
	var buscaProfessorAntigo = repository.findById(id);
	if (buscaProfessorAntigo.isPresent()){
		var professorAntigo = buscaProfessorAntigo.get();

		//Atualizar cada atributo do objeto antigo 
        professorAntigo.setNome(professor.getNome());
        professorAntigo.setCurso(professor.getCurso());
        professorAntigo.setSexo(professor.getSexo());
        professorAntigo.setCpf(professor.getCpf());
        professorAntigo.setCursoId(professor.getCursoId());
        professorAntigo.setTelefone(professor.getTelefone());
        professorAntigo.setIdade(professor.getIdade());

        professorAntigo = repository.save(professorAntigo);
        publicarAtualizacao(professorAntigo);
		return professorAntigo;
	}
	return null;
    }

    @Override
    public Professor delete(String id) {
        var buscaProfessor = repository.findById(id);
        if (buscaProfessor.isPresent()){
            var professor = buscaProfessor.get();

            repository.delete(professor);

            return professor;
        }
        return null;
    }
     //método privado para publicar a atualização
     private void publicarAtualizacao(Professor professor){
        client.publishEvent(
					PUBSUB_NAME,
					TOPIC_NAME,
					professor).block();
    }
}

    

