package pe.edu.uni.pag_inicio.repository;


import org.springframework.stereotype.Repository;
import pe.edu.uni.pag_inicio.controller.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariosRepository {

    UsuarioDTO findByEmail(String email);

    Optional<UsuarioDTO> findById(long userId);

    UsuarioDTO save(UsuarioDTO usuario);

    void deleteById(long userId);

    List<UsuarioDTO> findAll();
}
