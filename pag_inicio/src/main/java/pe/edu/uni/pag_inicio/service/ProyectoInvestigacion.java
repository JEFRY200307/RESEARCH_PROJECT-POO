package pe.edu.uni.pag_inicio.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProyectoInvestigacion {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String,Object>> getAll(){
		String sql ="select categoria,";
		sql = sql.concat("id_proyecto, titulo,descripcion, ");
		sql = sql.concat("objetivos, recaudacion,fecha_inicio ");
		sql = sql.concat("fecha_fin ,fecha_fin ,estado ,monto_objetivo, image_url, id_usuario, id_creador ");
		sql = sql.concat("from Proyectos ");
		sql = sql.concat("where categoria like '%medio_ambiente%'");
		return jdbcTemplate.queryForList(sql);
	}
}
