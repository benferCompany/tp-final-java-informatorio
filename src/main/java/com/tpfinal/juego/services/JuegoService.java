package com.tpfinal.juego.services;

import com.tpfinal.juego.dto.DesarrolladorDTO;
import com.tpfinal.juego.dto.JuegoDTO;
import com.tpfinal.juego.entities.Desarrollador;
import com.tpfinal.juego.entities.Juego;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

public interface JuegoService {
    public JuegoDTO registroDeJuego(String titulo,String descripcion, Date fechaDeLanzamiento);
    public JuegoDTO agregarJuego(JuegoDTO juegoDTO);
    public List<JuegoDTO> visualizacionDeJuegosEnProgreso();
    public List<JuegoDTO> visualizacionDeJuegosFinalizados();
    public List<DesarrolladorDTO> visualizaciónDeLosDevsDeUnJuego(Long idJuego);
    public JuegoDTO asignacionDeUnDesarrolladorAUnJuego(Long idJuego, DesarrolladorDTO desarrolladorDTO);
    public List<JuegoDTO> visualizacionDeJuegos();
    public boolean eliminar(@PathVariable Long id);
}
