package pe.edu.uni.pag_inicio.service;

import pe.edu.uni.pag_inicio.entity.User;

public interface IContactoService {
    void registrarUsuario(User usuario);
    void actualizarUsuario(User usuario);
    void eliminarUsuario(User usuario);
}
