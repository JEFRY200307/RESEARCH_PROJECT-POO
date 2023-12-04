package pe.edu.uni.crowfunding.Repository;


import org.springframework.stereotype.Repository;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.Usuario;

import java.util.List;

@Repository
public interface UsuariosRepository {

    Usuario findByEmail(String email);

    List<Usuario> findByEsCreador(boolean esCreador);

    Usuario findById(int userId);

    Usuario save(Usuario usuario);

    Usuario update(int idUsuario, Usuario usuarioDTO);

    Mensajedto deleteById(int userId);
    List<Usuario> findAll();
}
