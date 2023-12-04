package pe.edu.uni.crowfunding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private int idusuario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String contrasena;
    private Boolean esadministrador;
    private Boolean escreador;
}
