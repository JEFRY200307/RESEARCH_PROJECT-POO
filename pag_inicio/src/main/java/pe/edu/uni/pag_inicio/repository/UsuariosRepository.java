package pe.edu.uni.pag_inicio.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.edu.uni.pag_inicio.entity.Usuarios;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuariosRepository {
    private final JdbcTemplate jdbcTemplate;

    public UsuariosRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Usuarios findByEmail(String email) {
        String query = "SELECT * FROM Usuarios WHERE email = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{email}, (resultSet, rowNum) -> {
            Usuarios usuario = new Usuarios();
            usuario.setId(resultSet.getLong("id_usuario"));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setApellido(resultSet.getString("apellido"));
            usuario.setTelefono(resultSet.getString("telefono"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setContrasena(resultSet.getString("contrasena"));
            return usuario;
        });
    }

    public Optional<Usuarios> findById(long userId) {
        String query = "SELECT * FROM Usuarios WHERE id_usuario = ?";
        return jdbcTemplate.query(query, new Object[]{userId}, resultSet -> {
            if (resultSet.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setId(resultSet.getLong("id_usuario"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setTelefono(resultSet.getString("telefono"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setContrasena(resultSet.getString("contrasena"));
                return Optional.of(usuario);
            } else {
                return Optional.empty();
            }
        });
    }

    public Usuarios save(Usuarios usuario) {
        if (usuario.getId() == null) {
            String insertQuery = "INSERT INTO Usuarios (nombre, apellido, telefono, email, contrasena) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(insertQuery, usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getEmail(), usuario.getContrasena());
        } else {
            String updateQuery = "UPDATE Usuarios SET nombre = ?, apellido = ?, telefono = ?, email = ?, contrasena = ? WHERE id_usuario = ?";
            jdbcTemplate.update(updateQuery, usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getEmail(), usuario.getContrasena(), usuario.getId());
        }
        return usuario;
    }

    public void deleteById(long userId) {
        String deleteQuery = "DELETE FROM Usuarios WHERE id_usuario = ?";
        jdbcTemplate.update(deleteQuery, userId);
    }

    public List<Usuarios> findAll() {
        String query = "SELECT * FROM Usuarios";
        return jdbcTemplate.query(query, (resultSet, rowNum) -> {
            Usuarios usuario = new Usuarios();
            usuario.setId(resultSet.getLong("id_usuario"));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setApellido(resultSet.getString("apellido"));
            usuario.setTelefono(resultSet.getString("telefono"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setContrasena(resultSet.getString("contrasena"));
            return usuario;
        });
    }
}

