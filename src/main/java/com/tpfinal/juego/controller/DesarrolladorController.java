package com.tpfinal.juego.controller;

import com.tpfinal.juego.dto.DesarrolladorDTO;
import com.tpfinal.juego.dto.TareaDTO;
import com.tpfinal.juego.entities.Desarrollador;
import com.tpfinal.juego.services.DesarrolladorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/desarrolladores")
public class DesarrolladorController {

    private DesarrolladorService desarrolladorService;
    @Autowired
    public DesarrolladorController(DesarrolladorService desarrolladorService) {
        this.desarrolladorService = desarrolladorService;
    }


    @PostMapping("/registroDeDesarrolladores")
    public ResponseEntity<DesarrolladorDTO> registroDeDesarrolladores(@RequestParam String nombre,@RequestParam String correoElectronico,@RequestParam String rol){
        return new ResponseEntity<>(desarrolladorService.registroDeDesarrolladores(nombre,correoElectronico,rol),HttpStatus.OK);
    }



    @GetMapping("/{desarrolladorId}")
    public ResponseEntity<DesarrolladorDTO> obtenerDesarrolladorPorId(@PathVariable Long desarrolladorId) {
        DesarrolladorDTO desarrollador = desarrolladorService.obtenerDesarrolladorPorId(desarrolladorId);
        return new ResponseEntity<>(desarrollador, HttpStatus.OK);
    }

    @GetMapping("/{juegoId}/desarrolladores")
    public ResponseEntity<List<Desarrollador>> obtenerDevsDeJuego(@PathVariable Long juegoId) {
        List<Desarrollador> desarrolladores = desarrolladorService.obtenerDevsDeJuego(juegoId);
        return new ResponseEntity<>(desarrolladores, HttpStatus.OK);
    }




}