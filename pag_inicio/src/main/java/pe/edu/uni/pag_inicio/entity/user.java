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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Id_usuario", unique = true)
    private Long id;
    @Column(name = "Nombre_usuario")
    private String nombre;
    @Column(name = "Teléfono", unique = true)
    private String telefono;
    @Column(name = "Correo", unique = true)
    private String correo;
    @Column(name = "Tipo_cliente")
    private String tipoCliente;
    @Column(name = "Password")
    private String password;// Puede ser "colaborador" o "creador de proyectos"

    // Getters y setters
    // Otras propiedades y métodos

    // Relación con proyectos (uno a muchos)
    @OneToMany(mappedBy = "Propietario")
    private List<Proyectos> proyectos;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", tipoCliente='" + tipoCliente + '\'' +
                ", password='" + password + '\'' +
                ", proyectos=" + proyectos +
                '}';
    }
}

