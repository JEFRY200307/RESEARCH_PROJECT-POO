package pe.edu.uni.pag_inicio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_usuario", unique = true)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "contrasena")
    private String contrasenae;
    @Column(name = "rol")
    private String rol;

    // Getters y setters
    // Otras propiedades y métodos

    // Relación con proyectos (uno a muchos)
    @OneToMany(mappedBy = "usuario")
    private List<Proyectos> proyectos;

    @Override
    public String toString() {
        return "Usuarios{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", contrasenae='" + contrasenae + '\'' +
                ", rol='" + rol + '\'' +
                ", proyectos=" + proyectos +
                '}';
    }
}

