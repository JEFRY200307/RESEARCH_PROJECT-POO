package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.pag_inicio.controller.dto.IdproyectoDTO;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudCreacionDTO;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudModificacionDTO;
import pe.edu.uni.pag_inicio.repository.CreadorRepository;

import java.util.List;

@Service
public class ContactoService {

    @Autowired
    private CreadorRepository contactRepository;

    public void solicitarCreacion(SolicitudCreacionDTO solicitudCreacionDTO) {
        contactRepository.solicitarguardarProyecto(solicitudCreacionDTO);
    }

    public void solicitarmodificion(SolicitudModificacionDTO solicitudModificacionDTO) {
        // Agrega lógica adicional si es necesario antes de llamar al repositorio
        contactRepository.solicitarmodificarProyecto(solicitudModificacionDTO);
        // Agrega lógica adicional después de llamar al repositorio si es necesario
    }
    public List<IdproyectoDTO> obtenerProyectosActivosPorCreador(Long idCreador) {
        // Lógica para obtener la lista de proyectos activos por el id_creador
        return contactRepository.obtenerProyectosActivosPorCreador(idCreador);
    }
}

