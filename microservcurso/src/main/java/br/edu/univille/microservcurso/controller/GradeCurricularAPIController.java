package br.edu.univille.microservcurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univille.microservcurso.entity.GradeCurricular;
import br.edu.univille.microservcurso.service.GradeCurricularService;
import io.dapr.Topic;
import io.dapr.client.domain.CloudEvent;

@RestController
@RequestMapping("/api/v1/gradescurriculares")
public class GradeCurricularAPIController {
    @Autowired
    private GradeCurricularService service;

    @GetMapping
    public ResponseEntity<List<GradeCurricular>> listaGradeCurricular(){
        var listaGradeCurricular = service.getAll();
        return 
            new ResponseEntity<List<GradeCurricular>>
            (listaGradeCurricular, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GradeCurricular> buscarGradeCurricular(@PathVariable("id")  String id){
        var gradecurricular = service.getById(id);
        if(gradecurricular == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<GradeCurricular>
            (gradecurricular, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<GradeCurricular> inserirCliente(@RequestBody GradeCurricular gradecurricular){
        if(gradecurricular == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        gradecurricular = service.saveNew(gradecurricular);
        return 
            new ResponseEntity<GradeCurricular>
            (gradecurricular, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<GradeCurricular> atualizarGradeCurricular(@PathVariable("id")  String id, @RequestBody GradeCurricular gradecurricular){
        if(gradecurricular == null || id == ""  || id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        gradecurricular = service.update(id, gradecurricular);
        if(gradecurricular == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<GradeCurricular>
            (gradecurricular, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GradeCurricular> removerGradeCurricular(@PathVariable("id")  String id){
        if(id == ""  || id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var gradecurricular = service.delete(id);
        if(gradecurricular == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<GradeCurricular>
            (gradecurricular, HttpStatus.OK);
    }

    @Topic(name = "${app.component.topic.gradecurricular}", pubsubName = "${app.component.service}")
    @PostMapping(path = "/event", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<GradeCurricular> atualizarGradeCurricular(@RequestBody(required = false) CloudEvent<GradeCurricular> cloudEvent){
        var gradecurricular = service.update(cloudEvent.getData());
        System.out.println("EVENT" + gradecurricular.getCursoId());
        return 
            new ResponseEntity<GradeCurricular>
            (gradecurricular, HttpStatus.OK);
    }
    
}