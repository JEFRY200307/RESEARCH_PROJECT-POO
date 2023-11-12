package pe.edu.uni.pag_inicio.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.uni.pag_inicio.controller.dto.ContactoDTO;
import pe.edu.uni.pag_inicio.controller.dto.IdproyectoDTO;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudCreacionDTO;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudModificacionDTO;


import java.util.List;

@Repository
public interface ContactRepository {

    void solicitarguardarProyecto(SolicitudCreacionDTO solicitudCreacionDTO);

    void solicitarmodificarProyecto(SolicitudModificacionDTO solicitudModificacionDTO);

    List<IdproyectoDTO> obtenerProyectosActivosPorCreador(Long idCreador);
}
