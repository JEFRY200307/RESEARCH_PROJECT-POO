package pe.edu.uni.crowfunding.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.uni.crowfunding.DTO.IdproyectoDTO;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.Proyecto;
import pe.edu.uni.crowfunding.model.Solicitud;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProyectoRepositoryImpl implements ProyectoRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProyectoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Proyecto saveProyecto(Proyecto proyecto) {
        String sqlProyecto = "INSERT INTO Proyectos (titulo, descripcion, objetivos, recaudacion, fecha_inicio, fecha_fin, estado, aprobacion, monto_objetivo, image_url, id_usuario, categoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlProyecto, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, proyecto.getTitulo());
            ps.setString(2, proyecto.getDescripcion());
            ps.setString(3, proyecto.getObjetivos());
            ps.setFloat(4, 0.0f); // Valor inicial para recaudacion
            ps.setDate(5, proyecto.getFecha_inicio());
            ps.setDate(6, proyecto.getFecha_fin());
            ps.setBoolean(7, false); // Valor inicial para estado
            ps.setInt(8, 0); // Valor inicial para aprobacion
            ps.setFloat(9, proyecto.getMonto_objetivo());
            ps.setString(10, proyecto.getImage_url());
            ps.setInt(11, proyecto.getIdusuario());
            ps.setString(12, proyecto.getCategoria());
            return ps;
            }, keyHolder);

        if (keyHolder.getKey() != null) {
            proyecto.setIdproyecto(keyHolder.getKey().intValue());
        }
        return proyecto;
    }


    @Override
    public List<Proyecto> obtenerProyectosPorCategoria(String categoria) {
        // Lógica para obtener proyectos por categoría desde la base de datos
        String sql = "SELECT * FROM Proyectos WHERE categoria = ?";
        return jdbcTemplate.query(sql, new Object[]{categoria}, (rs, rowNum) ->
                new Proyecto(
                        rs.getInt("idproyecto"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("objetivos"),
                        rs.getFloat("recaudacion"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getBoolean("estado"),
                        rs.getInt("aprobacion"),
                        rs.getFloat("monto_objetivo"),
                        rs.getString("image_url"),
                        rs.getInt("id_usuario"),
                        rs.getString("categoria")
                )
        );
    }
    @Override
    public List<Proyecto> findAllProyectos() {
        String sql = "SELECT * FROM Proyectos";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Proyecto(
                        rs.getInt("id_proyecto"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("objetivos"),
                        rs.getFloat("recaudacion"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getBoolean("estado"),
                        rs.getInt("aprobacion"),
                        rs.getFloat("monto_objetivo"),
                        rs.getString("image_url"),
                        rs.getInt("id_usuario"),
                        rs.getString("categoria")
                )
        );
    }
    @Override
    public Mensajedto borrarProyecto(int id) {
        String sql = "DELETE FROM Proyectos WHERE id_proyecto = ?";
        int filasBorradas = jdbcTemplate.update(sql, id);

        if (filasBorradas > 0) {
            return new Mensajedto(1, "Proyecto borrado exitosamente.");
        } else {
            return new Mensajedto(-1, "No se encontró un proyecto con ID " + id);
        }
    }

    @Override
    public Proyecto obtenerProyectoPorId(int idProyecto) {
        String sql = "SELECT * FROM Proyectos WHERE id_proyecto = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{idProyecto}, (rs, rowNum) ->
                    new Proyecto(
                            rs.getInt("idproyecto"),
                            rs.getString("titulo"),
                            rs.getString("descripcion"),
                            rs.getString("objetivos"),
                            rs.getFloat("recaudacion"),
                            rs.getDate("fecha_inicio"),
                            rs.getDate("fecha_fin"),
                            rs.getBoolean("estado"),
                            rs.getInt("aprobacion"),
                            rs.getFloat("monto_objetivo"),
                            rs.getString("image_url"),
                            rs.getInt("id_usuario"),
                            rs.getString("categoria")
            )
            );
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No se encontró ningún proyecto con la id");
            return null;
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Proyecto actualizarProyecto(int idProyecto, Solicitud solicitud) {
        // Obtén la información actual del proyecto
        Proyecto proyectoActual = obtenerProyectoPorId(idProyecto);

        // Verifica qué campos se deben actualizar y construye la consulta SQL dinámicamente
        StringBuilder sqlBuilder = new StringBuilder("UPDATE Proyectos SET ");
        List<Object> parametros = new ArrayList<>();

        if (solicitud.getDescripcion() != null) {
            sqlBuilder.append("descripcion = ?, ");
            parametros.add(solicitud.getDescripcion());
        }

        if (solicitud.getObjetivos() != null) {
            sqlBuilder.append("objetivos = ?, ");
            parametros.add(solicitud.getObjetivos());
        }

        if (solicitud.getFechafin() != null) {
            sqlBuilder.append("fecha_fin = ?, ");
            parametros.add(solicitud.getFechafin());
        }

        if (solicitud.getImage() != null) {
            sqlBuilder.append("image_url = ?, ");
            parametros.add(solicitud.getImage());
        }

        // Elimina la última coma de la consulta SQL
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 2);

        sqlBuilder.append("WHERE id_proyecto = ?");
        parametros.add(idProyecto);

        // Convierte la lista de parámetros a un array
        Object[] parametrosArray = parametros.toArray();

        // Ejecuta la actualización en la base de datos y obtén el número de filas afectadas
        int filasAfectadas = jdbcTemplate.update(sqlBuilder.toString(), parametrosArray);

        // Si al menos una fila fue actualizada, devuelve el proyecto actualizado; de lo contrario, devuelve null
        return filasAfectadas > 0 ? proyectoActual : null;
    }

    @Override
    public List<IdproyectoDTO> obtenerProyectosAprobadosPorCreador(int idCreador) {
        String sql = "SELECT id_proyecto, titulo,descripcion FROM Proyectos WHERE id_creador = ? AND aprobacion = 1";

        return jdbcTemplate.query(sql, new Object[]{idCreador}, (rs, rowNum) -> {
            IdproyectoDTO proyectoDTO = new IdproyectoDTO();
            proyectoDTO.setIdProyecto(rs.getInt("id_proyecto"));
            proyectoDTO.setTitulo(rs.getString("titulo"));
            proyectoDTO.setDescripcion(rs.getString("descripcion"));
            return proyectoDTO;
        });
    }
}
