package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConnectionChecker {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void checkConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");
            System.out.println("Conexión a la base de datos establecida correctamente.");
        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}

