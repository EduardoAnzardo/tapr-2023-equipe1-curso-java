package br.edu.univille.microservcurso.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.edu.univille.microservcurso.entity.GradeCurricular;

@Repository
public interface GradeCurricularRepository 
    extends CrudRepository<GradeCurricular,String>{
    
}
