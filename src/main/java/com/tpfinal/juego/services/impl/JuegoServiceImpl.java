package com.tpfinal.juego.services.impl;

import com.tpfinal.juego.dto.DesarrolladorDTO;
import com.tpfinal.juego.dto.JuegoDTO;
import com.tpfinal.juego.entities.Desarrollador;
import com.tpfinal.juego.entities.Juego;
import com.tpfinal.juego.entities.Tarea;
import com.tpfinal.juego.repository.DesarrolladorRepository;
import com.tpfinal.juego.repository.JuegoRepository;
import com.tpfinal.juego.repository.TareaRepository;
import com.tpfinal.juego.services.JuegoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class JuegoServiceImpl implements JuegoService {

    private JuegoRepository juegoRepository;
    private TareaRepository tareaRepository;
    private DesarrolladorRepository desarrolladorRepository;

    @Override
    public JuegoDTO registroDeJuego(String titulo, String descripcion, Date fechaDeLanzamiento) {
        Juego juego = new Juego();
        juego.setTitulo(titulo);
        juego.setDescripcion(descripcion);
        juego.setFechaLanzamiento(fechaDeLanzamiento);

        return JuegoDTO.fromEntity(juegoRepository.save(juego));
    }

    @Override
    public JuegoDTO agregarJuego(JuegoDTO juegoDTO) {
        // Convertir el JuegoDTO a una entidad Juego

        List<Desarrollador> desarrolladores = new ArrayList<>();
        for (int i = 0; i < juegoDTO.getDesarrolladoresIds().size() ; i++) {
            Optional<Desarrollador> desarrollador = desarrolladorRepository.findById(juegoDTO.getDesarrolladoresIds().get(i));
            desarrolladores.add(desarrollador.get());
        }
        List<Tarea> tareas = new ArrayList<>();
        for (int i = 0; i < juegoDTO.getTareasIds().size() ; i++) {
            Optional<Tarea> tarea = tareaRepository.findById(juegoDTO.getTareasIds().get(i));
            tareas.add(tarea.get());
        }

        Juego juego = new Juego();
        juego.setDescripcion(juegoDTO.getDescripcion());
        juego.setFechaLanzamiento(juegoDTO.getFechaLanzamiento());
        juego.setTitulo(juegoDTO.getTitulo());
        juego.setDesarrolladores(desarrolladores);
        juego.setTareas(tareas);
        juego.setEstado(juegoDTO.getEstado());

        return JuegoDTO.fromEntity(juegoRepository.save(juego));
    }

   @Override
   public List<JuegoDTO> visualizacionDeJuegos() {
        return juegoRepository.findAll().stream().map(juego->JuegoDTO.fromEntity(juego)).collect(Collectors.toList());
    }
    @Override
    public List<JuegoDTO> visualizacionDeJuegosFinalizados() {

        return juegoRepository.findByEstado("finalizado").stream().map(juego->JuegoDTO.fromEntity(juego)).collect(Collectors.toList());
    }


    @Override
    public boolean eliminar(Long id) {
        if(juegoRepository.existsById(id)){
            juegoRepository.deleteById(id);
            return true;
        }

        return false;
    }


    @Override
    public List<JuegoDTO> visualizacionDeJuegosEnProgreso() {
        List<JuegoDTO> juegosDTO = new ArrayList<>();
        juegosDTO.addAll(juegoRepository.findByEstado("en progreso").stream()
                .map(juego->JuegoDTO.fromEntity(juego)).collect(Collectors.toList()));
        return juegosDTO;
    }

    @Override
    public List<DesarrolladorDTO> visualizaciónDeLosDevsDeUnJuego(Long idJuego) {

        return juegoRepository.findById(idJuego).get().getDesarrolladores().stream()
                .map(desarrollador -> DesarrolladorDTO.fromEntity(Optional.ofNullable(desarrollador)))
                .collect(Collectors.toList());
    }


    @Override
    public JuegoDTO asignacionDeUnDesarrolladorAUnJuego(Long idJuego, DesarrolladorDTO desarrolladorDTO) {
        Optional<Juego> juegoOptional = juegoRepository.findById(idJuego);

        if (juegoOptional.isPresent()) {
            Juego juego = juegoOptional.get();

            if (juego.getDesarrolladores() == null) {
                juego.setDesarrolladores(new ArrayList<>());
            }

            boolean exitsDesarrollador =juego.getDesarrolladores().stream()
                    .anyMatch(desarrollador -> desarrollador.getId() == desarrolladorDTO.getId());
            if(!exitsDesarrollador){
                juego.getDesarrolladores().add(desarrolladorRepository.findById(desarrolladorDTO.getId()).get());
                juegoRepository.save(juego);

            }
            return JuegoDTO.fromEntity(juego);
        } else {
            return null;
        }
    }





}
