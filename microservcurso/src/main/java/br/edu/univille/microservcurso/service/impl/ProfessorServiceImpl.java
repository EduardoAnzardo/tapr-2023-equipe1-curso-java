package br.edu.univille.microservcurso.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univille.microservcurso.entity.Professor;
import br.edu.univille.microservcurso.repository.ProfessorRepository;
import br.edu.univille.microservcurso.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService{

    @Autowired
    private ProfessorRepository repository;

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
	return repository.save(professor);
    }

    @Override
    public Professor update(String id, Professor professor) {
	var buscaProfessorAntigo = repository.findById(id);
	if (buscaProfessorAntigo.isPresent()){
		var professorAntigo = buscaProfessorAntigo.get();

		//Atualizar cada atributo do objeto antigo 
		professorAntigo.setId(professor.getId());
        professorAntigo.setNome(professor.getNome());
        professorAntigo.setCurso(professor.getCurso());
        professorAntigo.setTurma(professor.getTurma());

		return repository.save(professorAntigo);
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
}

    

