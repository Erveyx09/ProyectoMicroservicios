package com.formacionbdi.microservicios.app.respuestas.services;

import com.formacionbdi.microservicios.app.respuestas.client.ExamenFeignClient;
import com.formacionbdi.microservicios.app.respuestas.models.entity.Respuesta;
import com.formacionbdi.microservicios.app.respuestas.models.repository.RespuestaRepository;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Pregunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaServiceImpl implements RespuestaService{

    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private ExamenFeignClient examenClient;

    @Override
    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
        return repository.saveAll(respuestas);
    }

    @Override
    public Iterable<Respuesta> findRespuestasByAlumnoAndExamen(Long alumnoId, Long examenId) {
        Examen examen = examenClient.obtenerExamenPorId(examenId);
        List<Pregunta> preguntas = examen.getPreguntas();
        List<Long> preguntaIds = preguntas.stream().map(Pregunta::getId).collect(Collectors.toList());
        List<Respuesta> respuestas = (List<Respuesta>) repository.findRespuestasByAlumnoByPreguntaIds(alumnoId, preguntaIds);
        respuestas.stream().map(respuesta -> {
            preguntas.forEach(pregunta -> {
                if(pregunta.getId().equals(respuesta.getPreguntaId())){
                    respuesta.setPregunta(pregunta);
                }
            });
            return respuesta;
        }).collect(Collectors.toList());
        return respuestas;
    }
    @Override
    public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
        return null;
    }
}
