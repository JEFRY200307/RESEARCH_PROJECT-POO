package pe.edu.uni.pag_inicio.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.uni.pag_inicio.controller.dto.Mensajedto;
import pe.edu.uni.pag_inicio.controller.dto.ReclamoDto;


@Service
public class ReclamoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Mensajedto crearReclamo(ReclamoDto reclamoDto) {
        // Verificar si el id_usuario existe
        String sqlUsuario = "SELECT COUNT(1) FROM Usuarios WHERE id_usuario = ?";
        int filasUsuario = jdbcTemplate.queryForObject(sqlUsuario, Integer.class, reclamoDto.getId_usuario());
        if (filasUsuario == 0) {
            throw new RuntimeException("No se encontró un usuario con ID " + reclamoDto.getId_usuario());
        }

        // Insertar el reclamo
        String sqlInsert = "INSERT INTO Reclamo (id_usuario, mensaje, tipo_reclamo, fecha_reclamo) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sqlInsert, reclamoDto.getId_usuario(), reclamoDto.getMensaje(), reclamoDto.getTipo_reclamo(), reclamoDto.getFecha_reclamo());

        return new Mensajedto(1, "Reclamo creado correctamente.");
    }

    public ReclamoDto obtenerReclamo(int idReclamo) {
        String sqlSelect = "SELECT * FROM Reclamo WHERE id_reclamo = ?";
        return jdbcTemplate.queryForObject(sqlSelect, (rs, rowNum) ->
                        new ReclamoDto(
                                rs.getInt("id_reclamo"),
                                rs.getInt("id_usuario"),
                                rs.getString("mensaje"),
                                rs.getString("tipo_reclamo"),
                                rs.getDate("fecha_reclamo")
                        ),
                idReclamo
        );
    }

    public Mensajedto actualizarReclamo(ReclamoDto reclamoDto) {
        String sqlUpdate = "UPDATE Reclamo SET mensaje = ?, tipo_reclamo = ?, fecha_reclamo = ? WHERE id_reclamo = ?";
        int filasActualizadas = jdbcTemplate.update(
                sqlUpdate,
                reclamoDto.getMensaje(),
                reclamoDto.getTipo_reclamo(),
                reclamoDto.getFecha_reclamo(),
                reclamoDto.getId_reclamo()
        );

        if (filasActualizadas > 0) {
            return new Mensajedto(1, "Reclamo actualizado correctamente.");
        } else {
            return new Mensajedto(-1, "No se encontró un reclamo con ID " + reclamoDto.getId_reclamo());
        }
    }

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
