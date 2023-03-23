package com.formacionbdi.microservicios.app.respuestas.client;

import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-examenes")
public interface ExamenFeignClient {

    @GetMapping("/{id}")
    public Examen obtenerExamenPorId(@PathVariable Long id);
}
