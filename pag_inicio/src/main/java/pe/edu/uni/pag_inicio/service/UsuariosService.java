package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.pag_inicio.controller.dto.UsuarioDTO;
import pe.edu.uni.pag_inicio.repository.UsuariosRepositoryImpl;

import java.util.List;

@Service
public class UsuariosService{
    @Autowired
    private UsuariosRepositoryImpl usuariosRepository;

    public UsuariosService(UsuariosRepositoryImpl usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public UsuarioDTO createUser(UsuarioDTO usuario) {

        return usuariosRepository.save(usuario);
    }

    public UsuarioDTO updateUser(UsuarioDTO usuario) {

        return usuariosRepository.save(usuario);
    }
    public UsuarioDTO getUserById(int userId) {
        return (UsuarioDTO) usuariosRepository.findById(userId).orElseGet(() -> new UsuarioDTO());
    }

    public UsuarioDTO getUserByEmail(String email) {

        return usuariosRepository.findByEmail(email);
    }


    public List<UsuarioDTO> getAllUsers() {

        return usuariosRepository.findAll();
    }

    public void deleteUser(int userId) {
        usuariosRepository.deleteById(userId);
    }
}
