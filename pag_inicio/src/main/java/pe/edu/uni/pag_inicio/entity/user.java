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
    private String tipoCliente; // Puede ser "colaborador" o "creador de proyectos"

    // Getters y setters
    // Otras propiedades y métodos

    // Relación con proyectos (uno a muchos)
    @OneToMany(mappedBy = "Propietario")
    private List<Proyectos> proyectos;

    public User(Long id, String nombre, String telefono, String correo, String tipoCliente) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.tipoCliente = tipoCliente;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", tipoCliente='" + tipoCliente + '\'' +
                ", proyectos=" + proyectos +
                '}';
    }
}

