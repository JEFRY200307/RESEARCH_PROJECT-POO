package pe.edu.uni.pag_inicio.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactoDTO {
    private int idUsuario;
    private String asunto;
    private String mensaje;
    private Date fechaEnvio;
// Constructor, getters, and setters
}
