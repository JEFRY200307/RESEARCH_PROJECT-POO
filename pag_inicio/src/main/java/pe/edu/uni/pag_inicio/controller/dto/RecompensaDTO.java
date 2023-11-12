package pe.edu.uni.pag_inicio.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecompensaDTO {
    private int idRecompensa;
    private int idProyecto;
    private String tipoNivel;
    private String nombreRecompensa;
    private float valorRecompensa;
}
