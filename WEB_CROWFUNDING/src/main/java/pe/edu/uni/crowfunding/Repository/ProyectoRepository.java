package pe.edu.uni.crowfunding.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.uni.crowfunding.DTO.IdproyectoDTO;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.Proyecto;
import pe.edu.uni.crowfunding.model.Solicitud;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProyectoRepository {

    Proyecto saveProyecto(Proyecto dto);

    List<Proyecto> obtenerProyectosPorCategoria(String categoria);


    List<Proyecto> findAllProyectos();

    Mensajedto borrarProyecto(int id);

    Proyecto obtenerProyectoPorId(int idProyecto);

    Proyecto actualizarProyecto(int idProyecto, Solicitud solicitud);

    List<IdproyectoDTO> obtenerProyectosAprobadosPorCreador(int idCreador);
}
