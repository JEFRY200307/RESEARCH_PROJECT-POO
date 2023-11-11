package pe.edu.uni.pag_inicio.repository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.edu.uni.pag_inicio.controller.dto.IdproyectoDTO;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudCreacionDTO;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudModificacionDTO;

import java.util.List;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void solicitarguardarProyecto(SolicitudCreacionDTO solicitudCreacionDTO) {
        // Insertar en la tabla Contacto
        String contactoSql = "INSERT INTO Contacto (asunto, mensaje,id_proyecto, id_creador, estado_aprobacion, estado_administrador) VALUES (?, ?, ?, ?, 0, 0)";
        jdbcTemplate.update(contactoSql,
                solicitudCreacionDTO.getContactoDTO().getAsunto(),
                solicitudCreacionDTO.getContactoDTO().getMensaje());
    }

    @Override
    @Transactional
    public void solicitarmodificarProyecto(SolicitudModificacionDTO solicitudModificacionDTO) {
        String contactoSql = "INSERT INTO Contacto (asunto, mensaje, id_proyecto, estado_aprobacion, estado_administrador) VALUES (?, ?, ?, 0, 0)";
        jdbcTemplate.update(contactoSql,
                solicitudModificacionDTO.getContactoDTO().getAsunto(),
                solicitudModificacionDTO.getContactoDTO().getMensaje(),
            solicitudModificacionDTO.getIdproyectoDTO().getIdProyecto());
    }

    public List<IdproyectoDTO> obtenerProyectosActivosPorCreador(Long idCreador) {
        String sql = "SELECT id_proyecto, titulo FROM Proyectos WHERE id_creador = ? AND estado = 1";

        return jdbcTemplate.query(sql, new Object[]{idCreador}, (rs, rowNum) -> {
            IdproyectoDTO proyectoDTO = new IdproyectoDTO();
            proyectoDTO.setIdProyecto(rs.getInt("id_proyecto"));
            proyectoDTO.setTitulo(rs.getString("titulo"));
            return proyectoDTO;
        });
    }
}


