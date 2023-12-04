package pe.edu.uni.crowfunding.Repository;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.edu.uni.crowfunding.model.*;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class SolicitudRepositoryImpl implements SolicitudRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SolicitudRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    @Transactional
    public Solicitud solicitarmodificarProyecto(Solicitud solicitud) {
        String solicitudSql = "INSERT INTO Contacto (descripcion,objetivos, fecha_fin, aprobacion,image_url, id_proyecto,id_usuario) VALUES (?, ?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(solicitudSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,solicitud.getDescripcion());
            ps.setString(2,solicitud.getObjetivos());
            ps.setDate(3,solicitud.getFechafin());
            ps.setInt(4,0);
            ps.setString(5,solicitud.getImage());
            ps.setInt(6,solicitud.getIdproyecto());
            ps.setInt(7,solicitud.getIdusuario());
            return ps;
            }, keyHolder);
        return solicitud;
    }
    @Override
    public Solicitud obtenerSolicitudPorId(int idSolicitud) {
        String sql = "SELECT * FROM Solicitud WHERE id_solicitud = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idSolicitud}, (resultSet, rowNum) -> {
            Solicitud solicitud = new Solicitud();
            solicitud.setIdsolicitud(resultSet.getInt("id_solicitud"));
            solicitud.setDescripcion(resultSet.getString("descripcion"));
            solicitud.setObjetivos(resultSet.getString("objetivos"));
            solicitud.setFechafin(resultSet.getDate("fecha_fin"));
            solicitud.setAprobacion(resultSet.getInt("aprobacion"));
            solicitud.setImage(resultSet.getString("image_url"));
            solicitud.setIdproyecto(resultSet.getInt("id_proyecto"));
            solicitud.setIdusuario(resultSet.getInt("id_usuario"));
            // Setear otros atributos según la estructura de tu tabla
            return solicitud;
        });
    }
    @Override
    public void actualizarSolicitud(Solicitud solicitud) {
        String sql = "UPDATE Solicitud SET descripcion = ?, objetivos = ?, fecha_fin = ?, aprobacion = ?, image_url = ? WHERE id_solicitud = ?";

        jdbcTemplate.update(sql,
                solicitud.getDescripcion(),
                solicitud.getObjetivos(),
                solicitud.getFechafin(),
                solicitud.getAprobacion(),
                solicitud.getImage(),
                solicitud.getIdsolicitud());
    }

}


