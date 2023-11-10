package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.pag_inicio.controller.dto.ContactoDTO;
import pe.edu.uni.pag_inicio.controller.dto.ProyectoDTO;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudCreacionDTO;
import pe.edu.uni.pag_inicio.entity.Contacto;
import pe.edu.uni.pag_inicio.entity.Creador;
import pe.edu.uni.pag_inicio.entity.Proyectos;
import pe.edu.uni.pag_inicio.repository.ContactRepository;
import pe.edu.uni.pag_inicio.repository.CreadorRepository;

@Service
public class ContactoService implements IContactoService{

    @Autowired
    private ContactRepository contactRepository;

    public void solicitarCreacion(SolicitudCreacionDTO solicitudCreacionDTO) {
        ContactoDTO contactoDTO = solicitudCreacionDTO.getContactoDTO();
        ProyectoDTO proyectoDTO = solicitudCreacionDTO.getProyectoDTO();

        contactRepository.insertarProyecto(proyectoDTO);
        contactRepository.insertarContacto(contactoDTO);

    }

}
