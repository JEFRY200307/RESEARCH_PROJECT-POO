package pe.edu.uni.crowfunding.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import pe.edu.uni.crowfunding.model.MetodoPago;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MetodoPagoRepositoryImp implements MetodoPagoRepository{
    private final JdbcTemplate jdbcTemplate;
    private Connection conexion;
    @Autowired
    public MetodoPagoRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void obtenerConexion() {
        try{
            conexion = jdbcTemplate.getDataSource().getConnection();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    private void cerrarConexion(){
        try {
            conexion.close();
            conexion = null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public int saveMetodoPago(MetodoPago metodoPago) {
        int filasAfectadas;
        try{
            obtenerConexion();
            String sql = "INSERT INTO MetodoPago VALUES( ?, ?, ?, ?, ?);";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setInt(1, metodoPago.getIdUsuario());
            st.setString(2, metodoPago.getTipotarjeta());
            st.setString(3, metodoPago.getNombretitular());
            st.setDate(4, metodoPago.getFechaexpiracion());
            st.setInt(5, metodoPago.getCvv());
            filasAfectadas = st.executeUpdate();
            st.close();
            cerrarConexion();
        }catch(SQLException e){
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
