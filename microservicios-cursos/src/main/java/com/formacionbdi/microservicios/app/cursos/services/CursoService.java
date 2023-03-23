package com.formacionbdi.microservicios.app.cursos.services;

import com.formacionbdi.microservicios.app.cursos.models.entity.Curso;
import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.services.CommonService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CursoService extends CommonService<Curso> {

    public Curso findCursoByAlumnoId(Long id);

    public Iterable<Long> obternerExamenesIdsconRespuestasPorAlumno(Long alumnoId);

    public Iterable<Alumno> obtenerAlumnosPorCurso(List<Long> ids);

    public void eliminarCursoAlumnoPorId(Long id);
}
