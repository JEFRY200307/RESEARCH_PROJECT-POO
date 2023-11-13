package pe.edu.uni.pag_inicio.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.edu.uni.pag_inicio.controller.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuariosRepositoryImpl implements UsuariosRepository{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UsuariosRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UsuarioDTO findByEmail(String email) {
        String query = "SELECT * FROM Usuarios WHERE email = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{email}, (resultSet, rowNum) -> {
            UsuarioDTO usuario = new UsuarioDTO();
            resultSet.getLong("id_usuario");
            resultSet.getString("nombre");
            resultSet.getString("apellido");
            resultSet.getString("telefono");
            resultSet.getString("email");
            resultSet.getString("contrasena");
            return usuario;
        });
    }
    @Override
    public Optional<UsuarioDTO> findById(long userId) {
        String query = "SELECT * FROM Usuarios WHERE id_usuario = ?";
        return jdbcTemplate.query(query, new Object[]{userId}, resultSet -> {
            if (resultSet.next()) {
                UsuarioDTO usuario = new UsuarioDTO();
                resultSet.getLong("id_usuario");
                resultSet.getString("nombre");
                resultSet.getString("apellido");
                resultSet.getString("telefono");
                resultSet.getString("email");
                resultSet.getString("contrasena");
                return Optional.of(usuario);
            } else {
                return Optional.empty();
            }
        });
    }
    @Override
    public UsuarioDTO save(UsuarioDTO usuario) {
        if (usuario.getIdUsuario() == 0) {
            String insertQuery = "INSERT INTO Usuarios (nombre, apellido, telefono, email, contrasena) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(insertQuery, usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getEmail(), usuario.getContrasena());
        } else {
            String updateQuery = "UPDATE Usuarios SET nombre = ?, apellido = ?, telefono = ?, email = ?, contrasena = ? WHERE id_usuario = ?";
            jdbcTemplate.update(updateQuery, usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getEmail(), usuario.getContrasena(), usuario.getIdUsuario());
        }
        return usuario;
    }
    @Override
    public void deleteById(long userId) {
        String deleteQuery = "DELETE FROM Usuarios WHERE id_usuario = ?";
        jdbcTemplate.update(deleteQuery, userId);
    }
    @Override
    public List<UsuarioDTO> findAll() {
        String query = "SELECT * FROM Usuarios";
        return jdbcTemplate.query(query, (resultSet, rowNum) -> {
            UsuarioDTO usuario = new UsuarioDTO();
            resultSet.getLong("id_usuario");
            resultSet.getString("nombre");
            resultSet.getString("apellido");
            resultSet.getString("telefono");
            resultSet.getString("email");
            resultSet.getString("contrasena");
            return usuario;
        });
    }
}

