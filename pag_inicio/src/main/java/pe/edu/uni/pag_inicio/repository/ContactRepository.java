package pe.edu.uni.pag_inicio.repository;

import org.springframework.stereotype.Repository;
import pe.edu.uni.pag_inicio.controller.dto.ContactoDTO;
import pe.edu.uni.pag_inicio.controller.dto.ProyectoDTO;

@Repository
public interface ContactRepository {
    void insertarProyecto(ProyectoDTO proyectoDTO);

    void insertarContacto(ContactoDTO contactoDTO);
}
