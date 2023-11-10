package pe.edu.uni.pag_inicio.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.edu.uni.pag_inicio.controller.dto.ContactoDTO;
import pe.edu.uni.pag_inicio.controller.dto.ProyectoDTO;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertarProyecto(ProyectoDTO proyectoDTO) {
        int id_del_administrador_por_defecto = 1;  // Reemplaza esto con el valor adecuado
        String sql = "INSERT INTO proyectos (titulo, descripcion, objetivos, fecha_fin, estado, image_url, recaudacion, fecha_inicio, categoria, monto_objetivo, estado_aprobacion, id_creador, id_administrador) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {
                proyectoDTO.getTitulo(),
                proyectoDTO.getDescripcion(),
                proyectoDTO.getObjetivos(),
                proyectoDTO.getFechaFin(),
                false,
                proyectoDTO.getImageUrl(),
                proyectoDTO.getRecaudacion(),
                proyectoDTO.getFechaInicio(),
                proyectoDTO.getCategoria(),
                proyectoDTO.getMontoObjetivo(),
                false,
                2,  // Corregir el parámetro id_creador
                id_del_administrador_por_defecto
        };

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void insertarContacto(ContactoDTO contactoDTO) {
        String sqlContacto = "INSERT INTO  Contacto (asunto, mensaje, fecha_envio) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlContacto, contactoDTO.getAsunto(), contactoDTO.getMensaje(), contactoDTO.getFechaEnvio());
    }
}
