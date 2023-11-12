package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.pag_inicio.entity.Usuarios;
import pe.edu.uni.pag_inicio.repository.UsuariosRepository;

import java.util.List;

@Service
public class UsuariosService implements IUsuariosService {
    private final UsuariosRepository usuariosRepository;

    @Autowired
    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public Usuarios createUser(Usuarios usuario) {
        // Agregar lógica para crear un nuevo usuario, por ejemplo, validación y almacenamiento en el repositorio
        return usuariosRepository.save(usuario);
    }

    @Override
    public Usuarios updateUser(Usuarios usuario) {
        // Agregar lógica para actualizar la información de un usuario, por ejemplo, validación y almacenamiento en el repositorio
        return usuariosRepository.save(usuario);
    }

    @Override
    public Usuarios getUserById(int userId) {
        // Agregar lógica para obtener un usuario por su ID desde el repositorio
        return (Usuarios) usuariosRepository.findById(userId).orElseGet(() -> new Usuarios());
    }


    @Override
    public Usuarios getUserByEmail(String email) {
        // Agregar lógica para obtener un usuario por su dirección de correo electrónico desde el repositorio
        return usuariosRepository.findByCorreo(email);
    }

    @Override
    public List<Usuarios> getAllUsers() {
        // Agregar lógica para obtener una lista de todos los usuarios desde el repositorio
        return usuariosRepository.findAll();
    }

    @Override
    public void deleteUser(Long userId) {
        // Agregar lógica para eliminar un usuario por su ID desde el repositorio
        usuariosRepository.deleteById(userId);
    }
}
