package pe.edu.uni.crowfunding.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeAdminDTO {
    private  int idcreador;
    private boolean estadoAprobacion;
    private String respuesta;
    private String asuntoRespuesta;

}
