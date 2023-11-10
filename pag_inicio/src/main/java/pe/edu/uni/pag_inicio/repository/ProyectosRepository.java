package pe.edu.uni.pag_inicio.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.edu.uni.pag_inicio.entity.Proyectos;

import java.util.List;
import java.util.Optional;

@Repository
public class ProyectosRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProyectosRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Proyectos> findAll() {
        String query = "SELECT * FROM Proyectos";
        return jdbcTemplate.query(query, (resultSet, rowNum) -> {
            Proyectos proyecto = new Proyectos();
            proyecto.setId_proyecto(resultSet.getInt("id_proyecto"));
            proyecto.setTitulo(resultSet.getString("titulo"));
            // Establecer otros campos de Proyectos según la estructura de tu tabla en la base de datos
            return proyecto;
        });
    }


    public void deleteById(int idProyecto) {
        String query = "DELETE FROM Proyectos WHERE id_proyecto = ?";
        jdbcTemplate.update(query, idProyecto);
    }


    public Optional<Proyectos> findById(int id_proyecto){
            String query = "SELECT * FROM Proyectos WHERE id_proyecto = ?";
            return jdbcTemplate.query(query, new Object[]{id_proyecto}, resultSet -> {
                if (resultSet.next()) {
                    Proyectos proyecto = new Proyectos();
                    proyecto.setId_proyecto(resultSet.getInt("id_proyecto"));
                    proyecto.setTitulo(resultSet.getString("titulo"));
                    // Establecer otros campos de Proyectos según la estructura de tu tabla en la base de datos
                    return Optional.of(proyecto);
                } else {
                    return Optional.empty();
                }
            });
        }

    public Proyectos save(Proyectos proyecto) {
        if (proyecto.getId_proyecto() == 0) {
            // Nuevo proyecto: Realiza una inserción en la base de datos
            String insertQuery = "INSERT INTO Proyectos (titulo, descripcion, objetivos, recaudacion, fecha_inicio, fecha_fin, estado, monto_objetivo, image_url, categoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(insertQuery, proyecto.getTitulo(), proyecto.getDescripcion(), proyecto.getObjetivos(), proyecto.getRecaudacion(), proyecto.getFecha_inicio() , proyecto.getFecha_fin() , proyecto.isEstado(), proyecto.getMonto_objetivo(), proyecto.getImage_url(), proyecto.getCategoria());
        } else {
            // Proyecto existente: Realiza una actualización en la base de datos
            String updateQuery = "UPDATE Proyectos SET titulo = ?, descripcion = ?, objetivos = ?, recaudacion = ?, fecha_inicio = ?, fecha_fin = ?, estado = ?, monto_objetivo = ?, image_url = ?, categoria = ? WHERE id_proyecto = ?";
            jdbcTemplate.update(updateQuery, proyecto.getTitulo(), proyecto.getDescripcion(), proyecto.getObjetivos(), proyecto.getRecaudacion(), proyecto.getFecha_inicio() , proyecto.getFecha_fin(), proyecto.isEstado(), proyecto.getMonto_objetivo(), proyecto.getImage_url(), proyecto.getCategoria(), proyecto.getId_proyecto());
        }
        return proyecto;
    }
}
