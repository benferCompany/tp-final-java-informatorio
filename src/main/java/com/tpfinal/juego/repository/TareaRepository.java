package com.tpfinal.juego.repository;

import com.tpfinal.juego.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByDesarrolladorId(Long idDesarrollador);
    List<Tarea> findByJuegoId(Long juegoId);
    List<Tarea> findByEstado(String estado);
    List<Tarea> findByFechaLimiteBetween(Date startOfDay, Date endOfDay);
    List<Tarea> findByEstadoAndFechaLimiteBetween(String estado,Date startOfDay, Date endOfDay);
    List<Tarea> findByFechaLimiteBefore(Date fechaLimite);

}
