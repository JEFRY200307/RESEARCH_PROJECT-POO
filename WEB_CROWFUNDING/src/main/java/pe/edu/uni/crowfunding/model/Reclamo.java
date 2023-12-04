package pe.edu.uni.crowfunding.model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reclamo {
    private int idreclamo;
    private int idusuario;
    private String asunto;
    private String mensaje;
    private Date fecha_reclamo;
    private int estado_reclamo;
}
