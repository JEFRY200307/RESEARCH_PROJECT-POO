package pe.edu.uni.crowfunding.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.Reclamo;

import java.util.List;

@Repository
public class ReclamoRepositoryImpl implements ReclamoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReclamoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Mensajedto crearReclamo(Reclamo reclamoDto) {
        // Verificar si el id_usuario existe
        String sqlUsuario = "SELECT COUNT(1) FROM Usuarios WHERE id_usuario = ?";
        int filasUsuario = jdbcTemplate.queryForObject(sqlUsuario, Integer.class, reclamoDto.getIdusuario());
        if (filasUsuario == 0) {
            throw new RuntimeException("No se encontró un usuario con ID " + reclamoDto.getIdusuario());
        }

        // Insertar el reclamo
        String sqlInsert = "INSERT INTO Reclamo (id_usuario,asunto, mensaje, fecha_reclamo, estado_reclamo) VALUES (?,?, ?, GETDATE(), 0)";
        jdbcTemplate.update(sqlInsert,reclamoDto.getIdusuario(), reclamoDto.getAsunto(), reclamoDto.getMensaje(), reclamoDto.getFecha_reclamo(),reclamoDto.getEstado_reclamo());

        return new Mensajedto(1, "Reclamo creado correctamente.");
    }

    @Override
    public List<Reclamo> getAllReclamo() {
        String sqlSelect = "SELECT * FROM Reclamo WHERE id_reclamo = ?";
        return jdbcTemplate.query(sqlSelect, (rs, rowNum) ->
                        new Reclamo(
                                rs.getInt("id_reclamo"),
                                rs.getInt("id_usuario"),
                                rs.getString("asunto"),
                                rs.getString("mensaje"),
                                rs.getDate("fecha_reclamo"),
                                rs.getInt("estado_reclamo")
                        )
        );
    }
    @Override
    public Mensajedto actualizarReclamo(Reclamo reclamoDto) {
        String sqlUpdate = "UPDATE Reclamo SET  asunto = ?, mensaje = ?, fecha_reclamo = ?,estado_reclamo = ? WHERE id_reclamo = ?";
        int filasActualizadas = jdbcTemplate.update(
                sqlUpdate,
                reclamoDto.getAsunto(),
                reclamoDto.getMensaje(),
                reclamoDto.getFecha_reclamo(),
                reclamoDto.getFecha_reclamo(),
                reclamoDto.getEstado_reclamo(),
                reclamoDto.getIdreclamo()
        );

        if (filasActualizadas > 0) {
            return new Mensajedto(1, "Reclamo actualizado correctamente.");
        } else {
            return new Mensajedto(-1, "No se encontró un reclamo con ID " + reclamoDto.getIdreclamo());
        }
    }

    @Override
    public Mensajedto borrarReclamo(int idReclamo) {
        String sqlDelete = "DELETE FROM Reclamo WHERE id_reclamo = ?";
        int filasBorradas = jdbcTemplate.update(sqlDelete, idReclamo);

        if (filasBorradas > 0) {
            return new Mensajedto(1, "Reclamo borrado exitosamente.");
        } else {
            return new Mensajedto(-1, "No se encontró un reclamo con ID " + idReclamo);
        }
    }
}
