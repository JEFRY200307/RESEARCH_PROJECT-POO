package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
        String sqlProyecto = "insert into Proyectos (titulo, descripcion, objetivos, recaudacion, fecha_inicio, fecha_fin, estado, monto_objetivo, id_usuario, id_creador, image_url, categoria) ";
        sqlProyecto += "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(
            sqlProyecto,
            dto.getTitulo(),
            dto.getDescripcion(),
            dto.getObjetivos(),
            dto.getRecaudacion(),
            dto.getFecha_inicio(), // Asegúrate de que sea de tipo java.sql.Date
            dto.getFecha_fin(), // Asegúrate de que sea de tipo java.sql.Date
            dto.getEstado(),
            dto.getMonto_objetivo(),
            dto.getId_usuario(),
            dto.getId_creador(),
            dto.getImage_url(),
            dto.getCategoria()
        );

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
