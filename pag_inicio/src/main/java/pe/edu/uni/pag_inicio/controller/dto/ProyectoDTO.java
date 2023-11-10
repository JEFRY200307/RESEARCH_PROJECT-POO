package pe.edu.uni.pag_inicio.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProyectoDTO {
    private ContactoDTO contacto;
    private String titulo;
    private String descripcion;
    private String objetivos;
    private Date fechaFin;
    private String imageUrl;
    private double recaudacion;
    private Date fechaInicio;
    private String categoria;
    private double montoObjetivo;
}
