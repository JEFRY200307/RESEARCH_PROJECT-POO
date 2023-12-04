package pe.edu.uni.crowfunding.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.uni.crowfunding.DTO.MensajeAdminDTO;

@Repository
public class AdminRepositoryImpl implements AdminRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public MensajeAdminDTO responderSolicitud(int idContacto, MensajeAdminDTO mensajeAdminDTO) {
        // Consulta SQL para actualizar la respuesta y el asunto de respuesta en la tabla Contacto
        String sql = "UPDATE Contacto SET estado_aprobacion = ?, respuesta = ?, asunto_respuesta = ? WHERE id_contacto = ?";

        // Ejecutar la actualización
        jdbcTemplate.update(
                sql,
                mensajeAdminDTO.isEstadoAprobacion(),
                mensajeAdminDTO.getRespuesta(),
                mensajeAdminDTO.getAsuntoRespuesta(),
                idContacto
        );
        return mensajeAdminDTO;
    }
}
