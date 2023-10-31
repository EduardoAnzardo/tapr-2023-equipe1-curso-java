package br.edu.univille.microservcurso.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.univille.microservcurso.entity.Professor;
import br.edu.univille.microservcurso.service.ProfessorService;

@RestController
@RequestMapping("/api/v1/professores")
public class ProfessorAPIController {

    @Autowired
    private ProfessorService service;

    @GetMapping
    public ResponseEntity<List<Professor>> listaProfessores(){
        //var listaProfessores = new ArrayList<Professor>();
        var listaProfessores = service.getAll();
        return 
            new ResponseEntity<List<Professor>>
            (listaProfessores, HttpStatus.OK);
    }
}
