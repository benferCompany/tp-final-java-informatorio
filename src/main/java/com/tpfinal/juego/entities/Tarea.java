package com.tpfinal.juego.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "juego_id")
    @JsonBackReference
    private Juego juego;
    @ManyToOne
    @JoinColumn(name = "desarrollador_id")
    @JsonBackReference
    private Desarrollador desarrollador;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date fechaLimite;
    private String estado;
}


