package com.formacionbdi.microservicios.app.cursos.controllers;

import com.formacionbdi.microservicios.app.cursos.models.entity.Curso;
import com.formacionbdi.microservicios.app.cursos.services.CursoService;
import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {

    Logger log = LoggerFactory.getLogger(CursoController.class);

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return this.validar(result);
        }
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();
        dbCurso.setNombre(curso.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @PutMapping("/{id}/asignar-alumnos")
    public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id){
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();
        alumnos.forEach(dbCurso::addAlumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
        log.error("eliminarAlumno " + alumno.toString() + " " + id);
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();
        dbCurso.removeAlumno(alumno);
        log.error("Curso removido " + dbCurso.getAlumnos().toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id){
        Curso curso = service.findCursoByAlumnoId(id);
        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}/asignar-examenes")
    public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id){
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();
        examenes.forEach(dbCurso::addExamen);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @PutMapping("/{id}/eliminar-examen")
    public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){
        log.error("eliminarExamen " + examen.toString() + " " + id);
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();
        dbCurso.removeExamen(examen);
        log.error("Curso removido " + dbCurso.getExamenes().toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }
}
