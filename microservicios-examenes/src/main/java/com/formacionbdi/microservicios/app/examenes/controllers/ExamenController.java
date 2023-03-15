package com.formacionbdi.microservicios.app.examenes.controllers;

import com.formacionbdi.microservicios.app.examenes.models.entity.Examen;
import com.formacionbdi.microservicios.app.examenes.models.entity.Pregunta;
import com.formacionbdi.microservicios.app.examenes.services.ExamenService;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Examen examen, @PathVariable Long id){
        Optional<Examen> o = service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Examen examenDb = o.get();
        examenDb.setNombre(examen.getNombre());

        List<Pregunta> eliminadas = new ArrayList<>();

        examenDb.getPreguntas().forEach(pdb ->{
            if (!examen.getPreguntas().contains(pdb)){
                eliminadas.add(pdb);
            }
                });
        eliminadas.forEach(examenDb::removePregunta);
        examenDb.setPreguntas(examen.getPreguntas());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
    }
}