package com.tpfinal.juego.services;

import com.tpfinal.juego.dto.TareaDTO;
import com.tpfinal.juego.entities.Tarea;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface TareaService {
    public TareaDTO asignacionDeTareas(String descripcion, Long juegoId, Long desarrolladorId, Date fechaLimite);
    public List<Tarea> obtenerTareasPorDesarrollador(Long desarrolladorId);
    public List<Tarea> buscarTareasPorEstado(String estado);

    public List<TareaDTO> visualizacionDeTareasPorDesarrollador(Long idDesarrollador);
    public List<TareaDTO> visualizacionDeTareas();
    public TareaDTO actualizacionDeEstadoTareas(Long idTarea, String estado);
    public List<TareaDTO> busquedaDeTareas(String estado, String date) throws ParseException;
    public List<TareaDTO> busquedaDeTareasPorUnJuego(Long idJuego);
    public List<TareaDTO> busquedaDeTareasFueraDePlazo();


}
