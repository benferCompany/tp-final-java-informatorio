package com.tpfinal.juego.controller;


import com.tpfinal.juego.dto.DesarrolladorDTO;
import com.tpfinal.juego.dto.JuegoDTO;
import com.tpfinal.juego.entities.Desarrollador;
import com.tpfinal.juego.entities.Juego;
import com.tpfinal.juego.services.JuegoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/juegos")
@AllArgsConstructor
public class JuegoController {
    private JuegoService juegoService;

    ///Peticiones get
    @GetMapping("/{id}")
    public ResponseEntity<List<JuegoDTO>> visualizacionDeJuegos() {

        return new ResponseEntity<>(juegoService.visualizacionDeJuegos(),HttpStatus.OK);
    }
    @GetMapping("/visualizacionDeJuegosEnProgreso")

    public ResponseEntity<List<JuegoDTO>> visualizacionDeJuegosEnProgreso() {

        return new ResponseEntity<>(juegoService.visualizacionDeJuegosEnProgreso(),HttpStatus.OK);
    }

    @GetMapping("/visualizaciónDeLosDevsDeUnJuego/{idJuego}")

    public ResponseEntity<List<DesarrolladorDTO>> visualizaciónDeLosDevsDeUnJuego(@PathVariable Long idJuego){

        return new ResponseEntity<>(juegoService.visualizaciónDeLosDevsDeUnJuego(idJuego),HttpStatus.OK);
    }

    @GetMapping("/visualizacionDeJuegosFinalizados")
    public ResponseEntity<List<JuegoDTO>> visualizacionDeJuegosFinalizados(){
        return new ResponseEntity<>(juegoService.visualizacionDeJuegosFinalizados(),HttpStatus.OK);
    }

    //Peticiones Post

    @PostMapping
    public ResponseEntity<JuegoDTO> agregarJuego(@RequestBody JuegoDTO juegoDTO){

        return new ResponseEntity<>(juegoService.agregarJuego(juegoDTO), HttpStatus.CREATED);
    }

    @PostMapping("/registroDeJuego")
    public ResponseEntity<JuegoDTO> registroDeJuego(@RequestBody JuegoDTO juegoDTO){
        return new ResponseEntity<>(juegoService.registroDeJuego(juegoDTO.getTitulo(), juegoDTO.getDescripcion(), juegoDTO.getFechaLanzamiento()),HttpStatus.CREATED);
    }


    ///Peticiones Delete

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id){
        if(juegoService.eliminar(id)){
            return "El juego se elimino con éxito";
        }
        return "El juego no exite";
    }

//Peticiones Put
    @PutMapping("/asignacionDeUnDesarrolladorAUnJuego/{idJuego}")
    public ResponseEntity<JuegoDTO> asignacionDeUnDesarrolladorAUnJuego(@PathVariable Long idJuego, @RequestBody DesarrolladorDTO desarrolladorDTO){

        return new ResponseEntity<>(juegoService.asignacionDeUnDesarrolladorAUnJuego(idJuego,desarrolladorDTO), HttpStatus.OK);
    }



}
