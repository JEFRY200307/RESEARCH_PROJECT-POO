package pe.edu.uni.crowfunding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.Usuario;
import pe.edu.uni.crowfunding.Repository.UsuariosRepository;


import java.util.List;

@Service
public class UsuariosService{
    private final UsuariosRepository usuariosRepository;
    @Autowired
    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public Usuario createUser(Usuario usuario) {

        return usuariosRepository.save(usuario);
    }

    public Usuario updateUser(int idUsuario, Usuario usuarioDTO) {

        return usuariosRepository.update(idUsuario,usuarioDTO);
    }
    public Usuario getUserById(int userId) {
        return usuariosRepository.findById(userId);
    }

    public Usuario getUserByEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }

    public  List<Usuario> getAllCreador (){
        return  usuariosRepository.findByEsCreador(true);
    }
    public List<Usuario> getAllUsers() {

        return usuariosRepository.findAll();
    }

    public Mensajedto deleteUser(int userId) {
        // Agregar lógica para eliminar un usuario por su ID desde el repositorio
        return usuariosRepository.deleteById(userId);

    }
}
