package pe.edu.uni.crowfunding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Donaciones {
    private int idproyecto;
    private int idusuario;
    private float monto;
    private Date fechaOperacion;
}
