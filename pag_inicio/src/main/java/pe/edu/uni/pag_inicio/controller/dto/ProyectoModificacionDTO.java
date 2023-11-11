package pe.edu.uni.pag_inicio.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProyectoModificacionDTO {
    private  String titulo;
    private String descripcion;
    private String objetivos;
    private Date fecha_fin;
    private String image_url;
}

