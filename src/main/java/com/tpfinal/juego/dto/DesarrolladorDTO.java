package com.tpfinal.juego.dto;

import com.tpfinal.juego.entities.Desarrollador;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter @Setter
public class DesarrolladorDTO {
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String rol;

    public static DesarrolladorDTO fromEntity(Optional<Desarrollador> desarrollador) {
        DesarrolladorDTO dto = new DesarrolladorDTO();
        dto.setId(desarrollador.get().getId());
        dto.setNombre(desarrollador.get().getNombre());;
        dto.setCorreoElectronico(desarrollador.get().getCorreoElectronico());
        dto.setRol(desarrollador.get().getRol());
        return dto;
    }
}
