package br.edu.univille.microservcurso.service;

import java.util.List;
import br.edu.univille.microservcurso.entity.GradeCurricular;

public interface GradeCurricularService {
    public List<GradeCurricular> getAll();
    public GradeCurricular getById(String id);
    public GradeCurricular saveNew(GradeCurricular gradecurricular);
    public GradeCurricular update(String id, GradeCurricular gradecurricular);
    public GradeCurricular update(GradeCurricular gradecurricular);
    public GradeCurricular delete(String id);
}