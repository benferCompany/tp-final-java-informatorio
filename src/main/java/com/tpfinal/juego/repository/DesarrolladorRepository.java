package com.tpfinal.juego.repository;

import com.tpfinal.juego.entities.Desarrollador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesarrolladorRepository extends JpaRepository<Desarrollador, Long> {
    //List<Desarrollador> findByJuegoId(Long juegoId);
    /*@Query("SELECT d FROM Desarrollador d WHERE d.id = :desarrolladorId")
    Desarrollador findByDesarrolladorId(@Param("desarrolladorId") Long desarrolladorId);*/
}
