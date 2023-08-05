package com.tpfinal.juego.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    @ManyToMany
    @JoinTable(name = "juego_desarrollador",
            joinColumns = @JoinColumn(name = "juego_id"),
            inverseJoinColumns = @JoinColumn(name = "desarrollador_id"))
    @JsonManagedReference
    private List<Desarrollador> desarrolladores;
    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Tarea> tareas;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date fechaLanzamiento;
    private String estado;
}

