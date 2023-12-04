package pe.edu.uni.crowfunding.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.uni.crowfunding.model.Donaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class DonacionesRepositoryImpl implements DonacionesRepository {
    private final JdbcTemplate jdbcTemplate;
    private Connection conexion;
    @Autowired
    public DonacionesRepositoryImpl(JdbcTemplate jdbcTemplate) {
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
    public int agregarDonacion(Donaciones dto) {
        int filasAfectadas;
        try{
            obtenerConexion();
            String sql = "INSERT INTO Donaciones VALUES( ?, ?, ?, ?, ?);";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setInt(1, dto.getIdproyecto());
            st.setInt(2, dto.getIdusuario());
            st.setFloat(3, dto.getMonto());
            st.setDate(4, dto.getFechaOperacion());
            filasAfectadas = st.executeUpdate();
            st.close();
            cerrarConexion();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return filasAfectadas;
    }
}
