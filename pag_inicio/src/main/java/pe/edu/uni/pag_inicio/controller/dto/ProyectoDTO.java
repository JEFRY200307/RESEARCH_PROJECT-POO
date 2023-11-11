package pe.edu.uni.pag_inicio.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProyectoDTO {
    private String titulo;
    private String descripcion;
    private String objetivos;
    private Date fecha_fin;
    private String image_url;
    private double recaudacion;
    private Date fecha_inicio;
    private String categoria;
    private double monto_objetivo;
}