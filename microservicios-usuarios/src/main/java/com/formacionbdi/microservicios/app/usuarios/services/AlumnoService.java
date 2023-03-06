package com.formacionbdi.microservicios.app.usuarios.services;

import com.formacionbdi.microservicios.app.usuarios.models.entity.Alumno;

import java.util.Optional;

public interface AlumnoService {

    public Iterable<Alumno> findAll();

    public Optional<Alumno> findById(Long id);

    public Alumno save(Alumno alumno);

    public void deleteById(Long id);


}
