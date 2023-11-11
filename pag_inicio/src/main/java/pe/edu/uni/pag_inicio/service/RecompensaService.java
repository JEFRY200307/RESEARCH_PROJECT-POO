package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.uni.pag_inicio.dto.Mensajedto;
import pe.edu.uni.pag_inicio.dto.Recompensadto;

@Service
public class RecompensaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Recompensadto crearRecompensa(Recompensadto recompensaDto, int idProyecto) {
        // Verificar si el proyecto existe
        String sqlProyecto = "SELECT COUNT(1) FROM Proyectos WHERE id_proyecto = ?";
        int filasProyecto = jdbcTemplate.queryForObject(sqlProyecto, Integer.class, idProyecto);
        if (filasProyecto == 0) {
            throw new RuntimeException("No se encontró un proyecto con ID " + idProyecto);
        }

        // Insertar la recompensa
        String sqlInsert = "INSERT INTO Recompensas (id_proyecto, tipo_nivel, nombre_recompensa, valor_recompensa) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sqlInsert, idProyecto, recompensaDto.getTipo_nivel(), recompensaDto.getNombre_recompensa(), recompensaDto.getValor_recompensa());

        return recompensaDto;
    }

    public Mensajedto borrarRecompensa(int idRecompensa) {
        String sql = "DELETE FROM Recompensas WHERE id_recompensa = ?";
        int filasBorradas = jdbcTemplate.update(sql, idRecompensa);

        if (filasBorradas > 0) {
            return new Mensajedto(1, "Recompensa borrada exitosamente.");
        } else {
            return new Mensajedto(-1, "No se encontró una recompensa con ID " + idRecompensa);
        }
    }
}
