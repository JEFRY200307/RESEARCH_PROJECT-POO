package pe.edu.uni.pag_inicio.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReclamoDto {
    private int id_reclamo;
    private int id_usuario;
    private String mensaje;
    private String tipo_reclamo;
    private Date fecha_reclamo;
}