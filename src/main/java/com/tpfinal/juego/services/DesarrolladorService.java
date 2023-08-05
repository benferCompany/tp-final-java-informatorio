package com.tpfinal.juego.services;

import com.tpfinal.juego.dto.DesarrolladorDTO;
import com.tpfinal.juego.entities.Desarrollador;

import javax.xml.crypto.Data;
import java.util.List;

public interface DesarrolladorService {
    public DesarrolladorDTO registroDeDesarrolladores(String nombre, String correoElectronico, String rol);
    public List<Desarrollador> obtenerDevsDeJuego(Long juegoId);
    public DesarrolladorDTO obtenerDesarrolladorPorId(Long desarrolladorId);


}
