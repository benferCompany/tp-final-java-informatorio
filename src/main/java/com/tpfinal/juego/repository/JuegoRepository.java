package com.tpfinal.juego.repository;

import com.tpfinal.juego.entities.Desarrollador;
import com.tpfinal.juego.entities.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Long> {
    List<Juego> findByEstado(String estado);
    /*@Query("SELECT d FROM Juego d WHERE d.id = :juegoId")
    Juego findByJuegoId(@Param("juegoId") Long juegoId);
    @Query("SELECT d FROM Desarrollador d WHERE d.juego.id = :juegoId")
    List<Desarrollador> findByDesarroladorById(@Param("juegoId") Long juegoId);*/
}
