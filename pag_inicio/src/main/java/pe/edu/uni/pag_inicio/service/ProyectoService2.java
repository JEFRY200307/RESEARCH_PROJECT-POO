package pe.edu.uni.pag_inicio.service;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.uni.pag_inicio.dto.Mensajedto;
import pe.edu.uni.pag_inicio.dto.Proyectodto;
@Service
public class ProyectoService2 {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public Proyectodto Crearpro(Proyectodto dto) {
		 // Verificar si el usuario y el creador existen
        String sqlUsuario = "select count(1) filas from Usuarios where id_usuario = ?";
        int filasUsuario = jdbcTemplate.queryForObject(sqlUsuario, Integer.class, dto.getId_usuario());
        if (filasUsuario == 0) {
            throw new RuntimeException("Código de usuario incorrecto.");
        }

        String sqlCreador = "select count(1) filas from Creador where id_creador = ?";
        int filasCreador = jdbcTemplate.queryForObject(sqlCreador, Integer.class, dto.getId_creador());
        if (filasCreador == 0) {
            throw new RuntimeException("Código de creador incorrecto.");
        }
		
     // Registrar proyecto
        String sqlProyecto = "insert into Proyectos (titulo, descripcion, objetivos, recaudacion, fecha_inicio, fecha_fin, estado, monto_objetivo, id_usuario, id_creador, image_url, categoria) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
            connection -> {
                PreparedStatement ps = connection.prepareStatement(sqlProyecto, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, dto.getTitulo());
                ps.setString(2, dto.getDescripcion());
                ps.setString(3, dto.getObjetivos());
                ps.setFloat(4, dto.getRecaudacion());
                ps.setDate(5, dto.getFecha_inicio());
                ps.setDate(6, dto.getFecha_fin());
                ps.setBoolean(7, dto.getEstado());
                ps.setFloat(8, dto.getMonto_objetivo());
                ps.setInt(9, dto.getId_usuario());
                ps.setInt(10, dto.getId_creador());
                ps.setString(11, dto.getImage_url());
                ps.setString(12, dto.getCategoria());
                return ps;
            },
            keyHolder
        );

        // Obtener el ID generado y establecerlo en el objeto dto
        if (keyHolder.getKey() != null) {
            dto.setId_proyecto(keyHolder.getKey().intValue());
        }

        return dto;	
	}
	public Mensajedto borrarProyecto(int id) {
        String sql = "DELETE FROM Proyectos WHERE id_proyecto = ?";
        int filasBorradas = jdbcTemplate.update(sql, id);

        if (filasBorradas > 0) {
            return new Mensajedto(1, "Proyecto borrado exitosamente.");
        } else {
            return new Mensajedto(-1, "No se encontró un proyecto con ID " + id);
        }
    }
    
}
