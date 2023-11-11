package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudCreacionDTO;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudModificacionDTO;
import pe.edu.uni.pag_inicio.repository.ContactRepository;

@Service
public class ContactoService {

    @Autowired
    private ContactRepository contactRepository;

    public void solicitarCreacion(SolicitudCreacionDTO solicitudCreacionDTO) {
        contactRepository.guardarProyecto(solicitudCreacionDTO);
    }

    public void modificarProyecto(SolicitudModificacionDTO solicitudModificacionDTO) {
        // Agrega lógica adicional si es necesario antes de llamar al repositorio
        contactRepository.modificarProyecto(solicitudModificacionDTO);
        // Agrega lógica adicional después de llamar al repositorio si es necesario
    }
}

