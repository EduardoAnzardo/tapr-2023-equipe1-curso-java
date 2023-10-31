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
    
}
