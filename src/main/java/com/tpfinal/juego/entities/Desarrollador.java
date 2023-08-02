package com.tpfinal.juego.entities;


import jakarta.persistence.*;

@Entity
public class Desarrollador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String rol;
    @ManyToOne
    @JoinColumn(name = "juego_id")
    private Juego juego;

    // Constructores, getters y setters, etc.
}
