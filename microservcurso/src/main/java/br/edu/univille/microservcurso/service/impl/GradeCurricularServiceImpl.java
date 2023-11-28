package br.edu.univille.microservcurso.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univille.microservcurso.entity.GradeCurricular;
import br.edu.univille.microservcurso.repository.GradeCurricularRepository;
import br.edu.univille.microservcurso.service.GradeCurricularService;

@Service
public class GradeCurricularServiceImpl 
    implements GradeCurricularService{
    
    @Autowired
    private GradeCurricularRepository repository;

    @Override
    public List<GradeCurricular> getAll() {
        var iterador = repository.findAll();
        List<GradeCurricular> listaGradeCurriculares = new ArrayList<>();

        iterador.forEach(listaGradeCurriculares::add);

        return listaGradeCurriculares;
    }

    @Override
    public GradeCurricular getById(String id) {
        var gradecurricular = repository.findById(id);
        if(gradecurricular.isPresent())
            return gradecurricular.get();
        return null;
    }

    @Override
    public GradeCurricular saveNew(GradeCurricular gradecurricular) {
        gradecurricular.setId(null);
        return repository.save(gradecurricular);
    }

    @Override
    public GradeCurricular update(String id, GradeCurricular gradecurricular) {
        var buscaGradeCurricularAntigo = repository.findById(id);
        if (buscaGradeCurricularAntigo.isPresent()){
            var gradecurricularAntigo = buscaGradeCurricularAntigo.get();

            //Atualizar cada atributo do objeto antigo 
            gradecurricularAntigo.setCursoId(gradecurricular.getCursoId());
            gradecurricularAntigo.setCurso(gradecurricular.getCurso());
            
            return repository.save(gradecurricularAntigo);
        }
        return null;
    }

    @Override
    public GradeCurricular delete(String id) {
        var buscaGradeCurricular = repository.findById(id);
        if (buscaGradeCurricular.isPresent()){
            var gradecurricular = buscaGradeCurricular.get();

            repository.delete(gradecurricular);

            return gradecurricular;
        }
        return null;
    }

    @Override
    public GradeCurricular update(GradeCurricular gradecurricular) {
        return repository.save(gradecurricular);
    }
    
}
