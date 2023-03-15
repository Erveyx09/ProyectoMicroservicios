package com.formacionbdi.microservicios.app.examenes.models.repository;

import com.formacionbdi.microservicios.app.examenes.models.entity.Examen;
import org.springframework.data.repository.CrudRepository;

public interface ExamenRepository extends CrudRepository<Examen, Long> {

}
