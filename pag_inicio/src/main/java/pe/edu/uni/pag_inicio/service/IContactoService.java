package pe.edu.uni.pag_inicio.service;

import pe.edu.uni.pag_inicio.controller.dto.ContactoDTO;
import pe.edu.uni.pag_inicio.controller.dto.ProyectoDTO;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudCreacionDTO;
import pe.edu.uni.pag_inicio.entity.Contacto;
import pe.edu.uni.pag_inicio.entity.Proyectos;

import java.util.Date;


public interface IContactoService {
    void solicitarCreacion(SolicitudCreacionDTO solicitudCreacionDTO);

    //void solicitarCreacion(ContactoDTO contactoDTO, ProyectoDTO proyectoDTO);
    //void solicitarModificacion(ProyectoDTO proyectoDTO, ContactoDTO contactoDTO);

}
