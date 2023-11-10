package pe.edu.uni.pag_inicio.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SolicitudCreacionDTO {
    private ContactoDTO contactoDTO;
    private ProyectoDTO proyectoDTO;

    // Constructor, getters y setters
}
