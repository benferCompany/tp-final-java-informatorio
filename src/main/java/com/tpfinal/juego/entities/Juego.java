package com.tpfinal.juego.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter @Getter

public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL)
    private List<Desarrollador> desarrolladores;
    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL)
    private List<Tarea> tareas;
    private Date fechaLanzamiento;


}

