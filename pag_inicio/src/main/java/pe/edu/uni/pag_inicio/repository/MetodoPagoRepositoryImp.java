package pe.edu.uni.pag_inicio.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import pe.edu.uni.pag_inicio.controller.dto.MetodoPagoDTO;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public int save2(MetodoPagoDTO metodoPago) {
        int filasAfectadas;
        try{
            obtenerConexion();
            String sql = "INSERT INTO MetodoPago VALUES( ?, ?, ?, ?, ?, ?);";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setInt(1, metodoPago.getId_usuario());
            st.setString(2, metodoPago.getTipo_tarjeta());
            st.setString(3, metodoPago.getNombre_titular());
            st.setFloat(4, metodoPago.getPago());
            st.setDate(5, metodoPago.getFecha_expiracion());
            st.setString(6, metodoPago.getCvv());
            filasAfectadas = st.executeUpdate();
            st.close();
            cerrarConexion();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return filasAfectadas;
    }
}
