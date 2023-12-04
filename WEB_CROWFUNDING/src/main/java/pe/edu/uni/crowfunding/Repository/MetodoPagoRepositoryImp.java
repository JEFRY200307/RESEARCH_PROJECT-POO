package pe.edu.uni.crowfunding.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import pe.edu.uni.crowfunding.model.MetodoPago;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.List;

@Repository
public class MetodoPagoRepositoryImp implements MetodoPagoRepository{
    private final JdbcTemplate jdbcTemplate;
    private Connection conexion;
    @Autowired
    public MetodoPagoRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public int saveMetodoPago(MetodoPago metodoPago) {
        int filasAfectadas = 0;
        try {
            String sql = "INSERT INTO MetodoPago (id_usuario, tipo_tarjeta, nombre_titular, fecha_expiracion, cvv) VALUES (?, ?, ?, ?, ?)";

            filasAfectadas = jdbcTemplate.update(
                    sql,
                    metodoPago.getIdUsuario(),
                    metodoPago.getTipotarjeta(),
                    metodoPago.getNombretitular(),
                    metodoPago.getFechaexpiracion(),
                    metodoPago.getCvv()
            );
        } catch (DataAccessException e) {
            // Imprime o registra el error para diagnóstico
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return filasAfectadas;
    }
    @Override
    public List<MetodoPago> findAllMetodosPago() {
        String sql = "SELECT * FROM MetodoPago";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new MetodoPago(
                        rs.getInt("id_metodopago"),
                        rs.getInt("id_usuario"),
                        rs.getString("tipo_tarjeta"),
                        rs.getString("nombre_titular"),
                        rs.getDate("fecha_expiracion"),
                        rs.getInt("cvv")
                )
        );
    }

    @Override
    public boolean existsByIdUsuario(int idUsuario) {
        String sql = "SELECT COUNT(*) FROM MetodoPago WHERE id_usuario = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, idUsuario);
        return count > 0;
    }
}
