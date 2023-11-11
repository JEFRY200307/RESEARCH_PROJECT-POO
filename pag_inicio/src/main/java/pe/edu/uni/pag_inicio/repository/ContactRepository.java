package pe.edu.uni.pag_inicio.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudCreacionDTO;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudModificacionDTO;

@Repository
public interface ContactRepository {
    void guardarProyecto(SolicitudCreacionDTO solicitudCreacionDTO);

    void modificarProyecto(SolicitudModificacionDTO solicitudModificacionDTO);

}
