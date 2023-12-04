package pe.edu.uni.crowfunding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Solicitud {
    private int idsolicitud;
    private String descripcion;
    private String objetivos;
    private Date fechafin;
    private int aprobacion;
    private String image;
    private int idproyecto;
    private int idusuario;
    // Constructor, getters, and setters
}
