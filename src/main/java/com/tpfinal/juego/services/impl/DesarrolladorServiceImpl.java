package com.tpfinal.juego.services.impl;

import com.tpfinal.juego.dto.DesarrolladorDTO;
import com.tpfinal.juego.entities.Desarrollador;
import com.tpfinal.juego.repository.DesarrolladorRepository;
import com.tpfinal.juego.services.DesarrolladorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DesarrolladorServiceImpl implements DesarrolladorService {

    private DesarrolladorRepository desarrolladorRepository;

    @Override
    public DesarrolladorDTO registroDeDesarrolladores(String nombre, String correoElectronico, String rol) {
        Desarrollador desarrollador = new Desarrollador();
        desarrollador.setNombre(nombre);
        desarrollador.setCorreoElectronico(correoElectronico);
        desarrollador.setRol(rol);

        return DesarrolladorDTO.fromEntity(java.util.Optional.of(desarrolladorRepository.save(desarrollador)));
    }
    @Override
    public List<Desarrollador> obtenerDevsDeJuego(Long juegoId) {

        //return desarrolladorRepository.findByJuegoId(juegoId);
        return null;
    }

    @Override
    public DesarrolladorDTO obtenerDesarrolladorPorId(Long desarrolladorId) {

        return DesarrolladorDTO.fromEntity(desarrolladorRepository.findById(desarrolladorId));

    }


}
