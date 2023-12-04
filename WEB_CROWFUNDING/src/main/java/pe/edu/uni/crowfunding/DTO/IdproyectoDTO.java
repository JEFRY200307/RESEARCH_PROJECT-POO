package pe.edu.uni.crowfunding.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IdproyectoDTO {
    private int idProyecto;
    private String titulo;
    private String descripcion;
}
