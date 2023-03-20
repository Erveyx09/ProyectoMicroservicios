package com.formacionbdi.microservicios.app.respuestas.models.repository;

import com.formacionbdi.microservicios.app.respuestas.models.entity.Respuesta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RespuestaRepository extends CrudRepository<Respuesta, Long> {

    @Query("select r from Respuesta r join fetch r.alumno a join fetch r.pregunta p join fetch p.examen e where a.id=?1 and e.id=?2")
    public Iterable<Respuesta> findRespuestasByAlumnoAndExamen (Long alumnoId, Long examenId);

    @Query("select e.id from Respuesta r join r.alumno a join r.pregunta p join p.examen e where a.id=?1 group by e.id")
    public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);


}
