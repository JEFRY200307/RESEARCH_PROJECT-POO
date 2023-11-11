package pe.edu.uni.pag_inicio.repository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudCreacionDTO;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudModificacionDTO;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Date; // Import Date from java.sql

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void guardarProyecto(SolicitudCreacionDTO solicitudCreacionDTO) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Insertar en la tabla Proyectos
        String proyectoSql = "INSERT INTO Proyectos (id_administrador, id_creador, titulo, descripcion, objetivos, fecha_fin, fecha_inicio, image_url, recaudacion, categoria, monto_objetivo, estado, estado_aprobacion) VALUES (2, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(proyectoSql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 1); // Assuming id_creador is a long value
            ps.setString(2, solicitudCreacionDTO.getProyectoDTO().getTitulo());
            ps.setString(3, solicitudCreacionDTO.getProyectoDTO().getDescripcion());
            ps.setString(4, solicitudCreacionDTO.getProyectoDTO().getObjetivos());
            ps.setDate(5, new Date(solicitudCreacionDTO.getProyectoDTO().getFecha_fin().getTime()));
            ps.setDate(6, new Date(solicitudCreacionDTO.getProyectoDTO().getFecha_inicio().getTime()));
            ps.setString(7, solicitudCreacionDTO.getProyectoDTO().getImage_url());
            ps.setDouble(8, solicitudCreacionDTO.getProyectoDTO().getRecaudacion());
            ps.setString(9, solicitudCreacionDTO.getProyectoDTO().getCategoria());
            ps.setDouble(10, solicitudCreacionDTO.getProyectoDTO().getMonto_objetivo());
            return ps;
        }, keyHolder);

        // Obtener el ID del proyecto recién insertado
        long idProyecto = keyHolder.getKey().longValue();

        // Obtener el id_creador del proyecto recién insertado
        long idCreador = jdbcTemplate.queryForObject(
                "SELECT id_creador FROM Proyectos WHERE id_proyecto = ?",
                new Object[]{idProyecto},
                Long.class
        );

        // Insertar en la tabla Contacto
        String contactoSql = "INSERT INTO Contacto (asunto, mensaje, fecha_envio, id_proyecto, id_creador, estado_aprobacion, estado_administrador) VALUES (?, ?, ?, ?, ?, 0, 0)";
        jdbcTemplate.update(contactoSql,
                solicitudCreacionDTO.getContactoDTO().getAsunto(),
                solicitudCreacionDTO.getContactoDTO().getMensaje(),
                new Date(solicitudCreacionDTO.getContactoDTO().getFechaEnvio().getTime()),
                idProyecto,
                idCreador);
    }

    @Override
    @Transactional
    public void modificarProyecto(SolicitudModificacionDTO solicitudModificacionDTO) {
        // Obtener el ID del proyecto por el título
        Long idProyecto = jdbcTemplate.queryForObject(
                "SELECT id_proyecto FROM Proyectos WHERE titulo = ?",
                new Object[]{solicitudModificacionDTO.getProyectoModificacionDTO().getTitulo()},
                Long.class
        );

        // Actualizar en la tabla Proyectos
        String proyectoSql = "UPDATE Proyectos SET descripcion = ?, objetivos = ?, fecha_fin = ?, image_url = ? WHERE id_proyecto = ?";
        jdbcTemplate.update(proyectoSql,
                solicitudModificacionDTO.getProyectoModificacionDTO().getDescripcion(),
                solicitudModificacionDTO.getProyectoModificacionDTO().getObjetivos(),
                new Date(solicitudModificacionDTO.getProyectoModificacionDTO().getFecha_fin().getTime()),
                solicitudModificacionDTO.getProyectoModificacionDTO().getImage_url(),
                idProyecto);

        // Insertar en la tabla Contacto
        String contactoSql = "INSERT INTO Contacto (asunto, mensaje, fecha_envio, id_proyecto, id_creador, estado_aprobacion, estado_administrador) VALUES (?, ?, ?, ?, ?, 0, 0)";
        jdbcTemplate.update(contactoSql,
                solicitudModificacionDTO.getContactoDTO().getAsunto(),
                solicitudModificacionDTO.getContactoDTO().getMensaje(),
                new Date(solicitudModificacionDTO.getContactoDTO().getFechaEnvio().getTime()),
                idProyecto);
    }

}
