package com.tpfinal.juego.entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "juego_id")
    private Juego juego;
    @ManyToOne
    @JoinColumn(name = "desarrollador_id")
    private Desarrollador desarrollador;
    private Date fechaLimite;
    private String estado;
}

