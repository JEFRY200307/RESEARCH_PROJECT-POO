package pe.edu.uni.crowfunding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proyecto {
    private int idproyecto;
    private String titulo;
    private String descripcion;
    private String objetivos;
    private float recaudacion;
    private Date fecha_inicio;
    private Date fecha_fin;
    private boolean estado;
    private int aprobacion;
    private float monto_objetivo;
    private String image_url;
    private int idusuario;
    private String categoria;

}
