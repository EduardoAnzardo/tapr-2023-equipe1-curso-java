package br.edu.univille.microservcurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarProfessor(@PathVariable("id") String id){
        var professor = service.getById(id);
        if(professor == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<Professor>
            (professor, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Professor> inserirCarro(@RequestBody Professor professor){
        if(professor == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        professor = service.saveNew(professor);
        return 
            new ResponseEntity<Professor>
            (professor, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable("id")  String id, @RequestBody Professor professor){
	if(professor == null || id == ""  || id == null){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	professor = service.update(id, professor);
	if(professor == null){
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	return 
		new ResponseEntity<Professor>
		(professor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Professor> removerProfessor(@PathVariable("id")  String id){
	if(id == ""  || id == null){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	var professor = service.delete(id);
	if(professor == null){
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	return 
		new ResponseEntity<Professor>
		(professor, HttpStatus.OK);
    }
}
