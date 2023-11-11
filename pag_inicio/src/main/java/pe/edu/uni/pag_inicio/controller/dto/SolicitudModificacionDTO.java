package pe.edu.uni.pag_inicio.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SolicitudModificacionDTO {
    // Campos necesarios para la solicitud de modificación
    private IdproyectoDTO idproyectoDTO;
    private ContactoDTO contactoDTO;
    // Getters y setters
}

