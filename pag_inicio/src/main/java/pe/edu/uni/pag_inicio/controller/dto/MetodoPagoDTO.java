package pe.edu.uni.pag_inicio.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetodoPagoDTO{
    private int id_usuario;
    private String tipo_tarjeta;
    private String nombre_titular;
    private float pago;
    private Date fecha_expiracion;
    private String cvv;
}
