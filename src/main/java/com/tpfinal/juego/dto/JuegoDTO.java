package com.tpfinal.juego.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tpfinal.juego.entities.Desarrollador;
import com.tpfinal.juego.entities.Juego;
import com.tpfinal.juego.entities.Tarea;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class JuegoDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private List<Long> desarrolladoresIds; // Lista de IDs de los desarrolladores
    private List<Long> tareasIds;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date fechaLanzamiento;
    private String estado;

    public static JuegoDTO fromEntity(Juego juego) {
        JuegoDTO dto = new JuegoDTO();
        if(juego.getId() !=null){
            dto.setId(juego.getId());
        }

        dto.setTitulo(juego.getTitulo());
        dto.setDescripcion(juego.getDescripcion());
        if (juego.getDesarrolladores() != null) {
            dto.setDesarrolladoresIds(juego.getDesarrolladores().stream()
                    .map(Desarrollador::getId)
                    .collect(Collectors.toList()));
        }
        if (juego.getTareas() != null) {
            dto.setTareasIds(juego.getTareas().stream()
                    .map(Tarea::getId)
                    .collect(Collectors.toList()));

        }
        dto.setFechaLanzamiento(juego.getFechaLanzamiento());
        dto.setEstado(juego.getEstado());
        return dto;
    }
}
