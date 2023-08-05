package com.tpfinal.juego.services.impl;

import com.tpfinal.juego.dto.TareaDTO;
import com.tpfinal.juego.entities.Tarea;
import com.tpfinal.juego.repository.DesarrolladorRepository;
import com.tpfinal.juego.repository.JuegoRepository;
import com.tpfinal.juego.repository.TareaRepository;
import com.tpfinal.juego.services.TareaService;
import com.tpfinal.juego.tools.FechasTools;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TareaServiceImpl implements TareaService {

    private TareaRepository tareaRepository;
    private JuegoRepository juegoRepository;
    private DesarrolladorRepository desarrolladorRepository;
    @Override
    public TareaDTO asignacionDeTareas(String descripcion, Long juegoId, Long desarrolladorId, Date fechaLimite) {
        Tarea tarea = new Tarea();
        tarea.setDescripcion(descripcion);
        tarea.setJuego(juegoRepository.findById(juegoId).orElse(null));
        tarea.setDesarrollador(desarrolladorRepository.findById(desarrolladorId).orElse(null));
        tarea.setFechaLimite(fechaLimite);
        tarea.setEstado("Pendiente");
        return TareaDTO.fromEntity(tareaRepository.save(tarea));
    }
    @Override
    public List<Tarea> obtenerTareasPorDesarrollador(Long desarrolladorId) {

        return tareaRepository.findByDesarrolladorId(desarrolladorId);
    }

    @Override
    public List<Tarea> buscarTareasPorEstado(String estado) {
        return tareaRepository.findByEstado(estado);
    }


    @Override
    public List<TareaDTO> visualizacionDeTareasPorDesarrollador(Long idDesarrollador) {

        return tareaRepository.findByDesarrolladorId(idDesarrollador).stream().map(tarea->TareaDTO.fromEntity(tarea)).collect(Collectors.toList());
    }

    @Override
    public List<TareaDTO> visualizacionDeTareas() {
        return tareaRepository.findAll().stream().map(tarea->TareaDTO.fromEntity(tarea)).collect(Collectors.toList());
    }

    @Override
    public TareaDTO actualizacionDeEstadoTareas(Long idTarea, String estado) {
        Tarea tarea = tareaRepository.findById(idTarea).get();
        tarea.setEstado(estado);
        return TareaDTO.fromEntity(tarea);
    }



    @Override
    public List<TareaDTO> busquedaDeTareasPorUnJuego(Long idJuego) {

        return tareaRepository.findByJuegoId(idJuego).stream()
                .map(tarea-> TareaDTO.fromEntity(tarea))
                .collect(Collectors.toList());
    }

    @Override
    public List<TareaDTO> busquedaDeTareasFueraDePlazo() {

        return tareaRepository.findByFechaLimiteBefore(new Date()).stream()
                .map(tarea->TareaDTO.fromEntity(tarea)).collect(Collectors.toList());
    }

    @Override
    public List<TareaDTO> busquedaDeTareas(String estado, String fechaLimite) throws ParseException {
        List<Tarea> returnDeTarea;

        if(!fechaLimite.isEmpty() && estado.isEmpty() ){
            FechasTools fechasTools = new FechasTools();
            Date fechaDate = fechasTools.convertirStringADate(fechaLimite);
            Date startOfDay = fechasTools.starOfDay(fechaDate);
            Date endOfDay = fechasTools.starOfDay(fechaDate);

            returnDeTarea = tareaRepository.findByFechaLimiteBetween(startOfDay,endOfDay);
        }
        if(!estado.isEmpty() && fechaLimite.isEmpty()){
            returnDeTarea = tareaRepository.findByEstado(estado);
        }
        if(!estado.isEmpty() && !fechaLimite.isEmpty()){
            FechasTools fechasTools = new FechasTools();
            Date fechaDate = fechasTools.convertirStringADate(fechaLimite);
            Date startOfDay = fechasTools.starOfDay(fechaDate);
            Date endOfDay = fechasTools.starOfDay(fechaDate);

            returnDeTarea = tareaRepository.findByEstadoAndFechaLimiteBetween(estado, startOfDay,endOfDay);
        }
        else{
            returnDeTarea = tareaRepository.findAll();
        }
    return returnDeTarea.stream().map(tarea -> TareaDTO.fromEntity(tarea)).collect(Collectors.toList());
    }
}
