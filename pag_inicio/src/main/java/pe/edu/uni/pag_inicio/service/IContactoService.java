package pe.edu.uni.pag_inicio.service;

import pe.edu.uni.pag_inicio.entity.Contacto;
import pe.edu.uni.pag_inicio.entity.Usuarios;

import java.util.List;

public interface IContactoService {
    List<Contacto> getContactMessagesByUser(Usuarios usuario);
    Contacto saveContactMessage(Contacto message);
}
