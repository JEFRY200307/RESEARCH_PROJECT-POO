package pe.edu.uni.crowfunding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MetodoPago {
    private int idmetodopago;
    private int idUsuario;
    private String tipotarjeta;
    private String nombretitular;
    private Date fechaexpiracion;
    private int cvv;
}
