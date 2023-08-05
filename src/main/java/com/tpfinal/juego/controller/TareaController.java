package com.tpfinal.juego.controller;

import com.tpfinal.juego.dto.TareaDTO;
import com.tpfinal.juego.entities.Tarea;
import com.tpfinal.juego.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/v1/tareas")
public class TareaController {
    private TareaService tareaService;

    @Autowired
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @PostMapping("/asignacionDeTareas")
    public ResponseEntity<TareaDTO> asignacionDeTareas(@RequestBody TareaDTO tarea) {
        TareaDTO nuevaTarea = tareaService.asignacionDeTareas(tarea.getDescripcion(),
                tarea.getJuego().getId(),
                tarea.getDesarrollador().getId(),
                tarea.getFechaLimite());

        return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);
    }



    @GetMapping("/por-estado/{estado}")
    public ResponseEntity<List<Tarea>> buscarTareasPorEstado(@PathVariable String estado) {
        List<Tarea> tareasPorEstado = tareaService.buscarTareasPorEstado(estado);
        return new ResponseEntity<>(tareasPorEstado, HttpStatus.OK);
    }
    @GetMapping("/visualizacionDeTareasPorDesarrollador/{idDesarrollador}")
    public ResponseEntity<List<TareaDTO>> visualizacionDeTareasPorDesarrollador(@PathVariable Long idDesarrollador){

        return new ResponseEntity<>(tareaService.visualizacionDeTareasPorDesarrollador(idDesarrollador), HttpStatus.OK);
    }

    @GetMapping("/visualizacionDeTareas")
    public ResponseEntity<List<TareaDTO>> visualizacionDeTareas(){
        return new ResponseEntity<>(tareaService.visualizacionDeTareas(),HttpStatus.OK);
    }
    @PutMapping("/actualizacionDeEstadoTareas/{idTarea}")
    public ResponseEntity<TareaDTO> actualizacionDeEstadoTareas(@PathVariable Long idTarea,@RequestParam String estado){

        return new ResponseEntity<>(tareaService.actualizacionDeEstadoTareas(idTarea, estado),HttpStatus.OK);
    }
    @PostMapping("/busquedaDeTareas")
    public ResponseEntity<List<TareaDTO>> busquedaDeTareas(@RequestParam String estado, @RequestParam String fechaLimite) throws ParseException {
        return new ResponseEntity<>(tareaService.busquedaDeTareas(estado,fechaLimite),HttpStatus.OK);
    }
    @GetMapping("/busquedaDeTareasPorUnJuego/{idJuego}")
    public ResponseEntity<List<TareaDTO>> busquedaDeTareasPorUnJuego(@PathVariable Long idJuego){
        return new ResponseEntity<>(tareaService.busquedaDeTareasPorUnJuego(idJuego),HttpStatus.OK);
    }

    @GetMapping("/busquedaDeTareasFueraDePlazo")
    public ResponseEntity<List<TareaDTO>> busquedaDeTareasFueraDePlazo() {
        return new ResponseEntity<>(tareaService.busquedaDeTareasFueraDePlazo(),HttpStatus.OK);

    }
}
