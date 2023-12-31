package pe.edu.uni.crowfunding.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.edu.uni.crowfunding.DTO.CredencialesDTO;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.Usuario;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuariosRepositoryImpl implements UsuariosRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuariosRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Usuario findByEmail(String email) {
        String query = "SELECT * FROM Usuarios WHERE email = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{email}, (resultSet, rowNum) -> {
            Usuario usuario = new Usuario();
            usuario.setIdusuario(resultSet.getInt("id_usuario"));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setApellido(resultSet.getString("apellido"));
            usuario.setTelefono(resultSet.getString("telefono"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setContrasena(resultSet.getString("contrasena"));
            return usuario;
        });
    }

    @Override
    public List<Usuario> findByEsCreador(boolean isCreador) {
        String sql = "SELECT * FROM Usuarios WHERE es_creador = ?";
        return jdbcTemplate.query(sql, new Object[]{isCreador}, (resultSet, rowNum) -> {
            Usuario usuario = new Usuario();
            usuario.setIdusuario(resultSet.getInt("id_usuario"));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setApellido(resultSet.getString("apellido"));
            usuario.setTelefono(resultSet.getString("telefono"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setContrasena(resultSet.getString("contrasena"));
            return usuario;
        });
    }

    @Override
    public Usuario findById(int userId) {
        String query = "SELECT * FROM Usuarios WHERE id_usuario = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{userId}, (resultSet, rowNum) -> {
            Usuario usuario = new Usuario();
            usuario.setIdusuario(resultSet.getInt("id_usuario"));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setApellido(resultSet.getString("apellido"));
            usuario.setTelefono(resultSet.getString("telefono"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setContrasena(resultSet.getString("contrasena"));
            return usuario;
        });
    }
    @Override
    public CredencialesDTO findByNombre(String nombre) {
        String sql = "SELECT * FROM Usuarios WHERE nombre = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{nombre}, (rs, rowNum) ->
                new CredencialesDTO(
                        rs.getString("nombre"),
                        rs.getString("contrasena")
                )
        );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Usuario save(Usuario usuario) {
        if (usuario.getIdusuario() == 0) {
            String insertQuery = "INSERT INTO Usuarios (nombre, apellido, telefono, email, contrasena, es_administrador,es_creador) VALUES (?, ?, ?, ?, ?,0,0)";
            jdbcTemplate.update(insertQuery, usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getEmail(), usuario.getContrasena());
        }
        return usuario;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Usuario update(int idUsuario, Usuario usuarioDTO) {
        // Obtén la información actual del usuario
        Usuario usuarioActual = findById(idUsuario);

        // Verifica qué campos se deben actualizar y construye la consulta SQL dinámicamente
        StringBuilder updateQueryBuilder = new StringBuilder("UPDATE Usuarios SET ");
        List<Object> parametros = new ArrayList<>();

        if (usuarioDTO.getNombre() != null) {
            updateQueryBuilder.append("nombre = ?, ");
            parametros.add(usuarioDTO.getNombre());
        }

        if (usuarioDTO.getApellido() != null) {
            updateQueryBuilder.append("apellido = ?, ");
            parametros.add(usuarioDTO.getApellido());
        }

        if (usuarioDTO.getTelefono() != null) {
            updateQueryBuilder.append("telefono = ?, ");
            parametros.add(usuarioDTO.getTelefono());
        }

        if (usuarioDTO.getEmail() != null) {
            updateQueryBuilder.append("email = ?, ");
            parametros.add(usuarioDTO.getEmail());
        }

        if (usuarioDTO.getContrasena() != null) {
            updateQueryBuilder.append("contrasena = ?, ");
            parametros.add(usuarioDTO.getContrasena());
        }

        // Elimina la última coma de la consulta SQL
        updateQueryBuilder.deleteCharAt(updateQueryBuilder.length() - 2);

        updateQueryBuilder.append("WHERE id_usuario = ?");
        parametros.add(idUsuario);

        // Convierte la lista de parámetros a un array
        Object[] parametrosArray = parametros.toArray();

        // Ejecuta la actualización en la base de datos y obtén el número de filas afectadas
        int filasAfectadas = jdbcTemplate.update(updateQueryBuilder.toString(), parametrosArray);

        // Si al menos una fila fue actualizada, devuelve el usuario actualizado; de lo contrario, devuelve null
        return filasAfectadas > 0 ? usuarioActual : null;
    }

    @Override
    public Mensajedto deleteById(int userId) {
        // Eliminar de la tabla Usuarios
        String deleteUsuarioQuery = "DELETE FROM Usuarios WHERE id_usuario = ?";
        int filasBorradasUsuario = jdbcTemplate.update(deleteUsuarioQuery, userId);

        // Eliminar de la tabla MetodoPago
        String deleteMetodoPagoQuery = "DELETE FROM MetodoPago WHERE id_usuario = ?";
        jdbcTemplate.update(deleteMetodoPagoQuery, userId);

        // Eliminar de la tabla Proyectos
        String deleteProyectosQuery = "DELETE FROM Proyectos WHERE id_usuario = ?";
        jdbcTemplate.update(deleteProyectosQuery, userId);

        // Eliminar de la tabla Donaciones
        String deleteDonacionesQuery = "DELETE FROM Donaciones WHERE id_usuario = ?";
        jdbcTemplate.update(deleteDonacionesQuery, userId);

        // Eliminar de la tabla Solicitud
        String deleteSolicitudQuery = "DELETE FROM Solicitud WHERE id_usuario = ?";
        jdbcTemplate.update(deleteSolicitudQuery, userId);

        // Eliminar de la tabla Reclamo
        String deleteReclamoQuery = "DELETE FROM Reclamo WHERE id_usuario = ?";
        jdbcTemplate.update(deleteReclamoQuery, userId);

        // Puedes continuar con otras tablas relacionadas...

        // Verificar si al menos una tabla ha tenido filas borradas
        if (filasBorradasUsuario > 0) {
            return new Mensajedto(1, "Usuario y relaciones eliminados exitosamente.");
        } else {
            return new Mensajedto(-1, "No se encontró un usuario con ID: " + userId);
        }
    }

    @Override
    public List<Usuario> findAll() {
        String query = "SELECT * FROM Usuarios";
        return jdbcTemplate.query(query, (resultSet, rowNum) -> {
            Usuario usuario = new Usuario();
            usuario.setIdusuario(resultSet.getInt("id_usuario"));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setApellido(resultSet.getString("apellido"));
            usuario.setTelefono(resultSet.getString("telefono"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setContrasena(resultSet.getString("contrasena"));
            return usuario;
        });
    }
}

