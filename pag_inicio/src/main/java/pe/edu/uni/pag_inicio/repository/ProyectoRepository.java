package pe.edu.uni.pag_inicio.repository;

import org.springframework.stereotype.Repository;
import pe.edu.uni.pag_inicio.controller.dto.ProyectoDTO;

import java.util.List;

@Repository
public interface ProyectoRepository {
    List<ProyectoDTO> obtenerProyectosPorCategoria(String categoria);
}
