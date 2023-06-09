package com.formacionbdi.microservicios.app.respuestas.services;

import com.formacionbdi.microservicios.app.respuestas.client.ExamenFeignClient;
import com.formacionbdi.microservicios.app.respuestas.models.entity.Respuesta;
import com.formacionbdi.microservicios.app.respuestas.models.repository.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaServiceImpl implements RespuestaService{

    @Autowired
    private RespuestaRepository repository;

    //@Autowired
    //private ExamenFeignClient examenClient;

    @Override
    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
        return repository.saveAll(respuestas);
    }

    @Override
    public Iterable<Respuesta> findRespuestasByAlumnoAndExamen(Long alumnoId, Long examenId) {
        /*Examen examen = examenClient.obtenerExamenPorId(examenId);
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
        return respuestas;*/
        return repository.findRespuestasByAlumnoAndExamen(alumnoId,examenId);
    }

    @Override
    public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
        /*List<Respuesta> respuestasAlumno = (List<Respuesta>) repository.findByAlumnoId(alumnoId);
        List<Long> examenIds = Collections.emptyList();
        if(respuestasAlumno.size() > 0){
            List<Long> preguntaIds = respuestasAlumno.stream().map(Respuesta::getPreguntaId).collect(Collectors.toList());
            examenIds = examenClient.obtenerExamenesIdsPorPreguntasIdsRespondidas(preguntaIds);
        }*/
        List<Respuesta> respuestasAlumno = (List<Respuesta>) repository.findExamenesIdsConRespuestaByAlumno(alumnoId);
        List<Long> examenIds = respuestasAlumno.stream()
                .map(respuesta -> respuesta.getPregunta().getExamen().getId())
                .distinct().collect(Collectors.toList());
        return examenIds;
    }

    @Override
    public Iterable<Respuesta> findByAlumnoId(Long alumnoId) {
        return repository.findByAlumnoId(alumnoId);
    }
}
