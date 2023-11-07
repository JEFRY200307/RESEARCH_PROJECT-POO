package pe.edu.uni.pag_inicio.service;

import pe.edu.uni.pag_inicio.entity.Usuarios;

public interface IContactoService {
    void registrarUsuario(Usuarios usuario);
    void actualizarUsuario(Usuarios usuario);
    void eliminarUsuario(Usuarios usuario);
}
