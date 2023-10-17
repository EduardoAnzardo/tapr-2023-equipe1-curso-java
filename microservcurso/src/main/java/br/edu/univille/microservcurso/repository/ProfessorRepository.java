package br.edu.univille.microservcurso.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.univille.microservcurso.entity.Professor;

@Repository
public interface ProfessorRepository
    extends CrudRepository<Professor,String>{

    }
    
