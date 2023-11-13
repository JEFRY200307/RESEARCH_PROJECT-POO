package pe.edu.uni.pag_inicio.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.edu.uni.pag_inicio.controller.dto.IdproyectoDTO;
import pe.edu.uni.pag_inicio.controller.dto.ProyectoDTO;

import java.util.List;
import java.util.Map;

@Repository
public class ProyectoRepositoryImpl implements ProyectoRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProyectoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<ProyectoDTO> obtenerProyectosPorCategoria(String categoria) {
        // Lógica para obtener proyectos por categoría desde la base de datos
        String sql = "SELECT * FROM Proyectos WHERE categoria = ?";
        return jdbcTemplate.query(sql, new Object[]{categoria}, (rs, rowNum) ->
                new ProyectoDTO(
                        rs.getInt("id_proyecto"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("objetivos"),
                        rs.getFloat("recaudacion"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getBoolean("estado"),
                        rs.getFloat("monto_objetivo"),
                        rs.getString("image_url"),
                        rs.getString("categoria"),
                        rs.getInt("id_creador"),
                        rs.getInt("id_administrador")
                )
        );
    }
}
