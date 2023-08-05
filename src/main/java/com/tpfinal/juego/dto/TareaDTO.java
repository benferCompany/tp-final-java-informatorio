package com.tpfinal.juego.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tpfinal.juego.entities.Desarrollador;
import com.tpfinal.juego.entities.Tarea;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;

import static java.util.Optional.*;

@Getter @Setter
public class TareaDTO {
    private Long id;
    private String descripcion;

    // Use @JsonIgnoreProperties para ignorar la referencia circular en juego y desarrollador

    private JuegoDTO juego;
    private DesarrolladorDTO desarrollador;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date fechaLimite;
    private String estado;

    // Constructor, getters y setters

    // Método para convertir entidad a DTO
    public static TareaDTO fromEntity(Tarea tarea) {
        TareaDTO dto = new TareaDTO();
        dto.setId(tarea.getId());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setFechaLimite(tarea.getFechaLimite());
        dto.setEstado(tarea.getEstado());
        dto.setJuego(JuegoDTO.fromEntity(tarea.getJuego()));
        dto.setDesarrollador(DesarrolladorDTO.fromEntity(ofNullable(tarea.getDesarrollador())));

        return dto;
    }
}
