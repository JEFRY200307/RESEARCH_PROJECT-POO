package pe.edu.uni.pag_inicio.service;

import pe.edu.uni.pag_inicio.entity.Usuarios;

import java.util.List;

public interface IUsuariosService {
    Usuarios createUser(Usuarios usuario);

    Usuarios updateUser(Usuarios usuario);

    Usuarios getUserById(int userId);

    Usuarios getUserByEmail(String email);

    List<Usuarios> getAllUsers();

    void deleteUser(int userId);
}
