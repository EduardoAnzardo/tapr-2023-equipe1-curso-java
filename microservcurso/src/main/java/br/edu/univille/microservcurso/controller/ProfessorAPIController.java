package br.edu.univille.microservcurso.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.univille.microservcurso.entity.Professor;

@RestController
@RequestMapping("/api/v1/professores")
public class ProfessorAPIController {
    @GetMapping
    public ResponseEntity<List<Professor>> listaProfessores(){
        var listaProfessores = new ArrayList<Professor>();

        return 
            new ResponseEntity<List<Professor>>
            (listaProfessores, HttpStatus.OK);
    }
}
