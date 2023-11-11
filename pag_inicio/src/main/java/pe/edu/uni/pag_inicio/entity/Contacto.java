package pe.edu.uni.pag_inicio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    @Column(name = "asunto")
    private String asunto;

    @Column(name = "mensaje", columnDefinition = "NVARCHAR(MAX)")
    private String mensaje;

    @Column(name = "nombre_archivo", columnDefinition = "NVARCHAR(MAX)")
    private String nombreArchivo;

    @Column(name = "fecha_envio")
    private Date fechaEnvio;

    @Override
    public String toString() {
        return "Formularios{" +
                "idFormulario=" + id +
                ", usuario=" + usuario +
                ", asunto='" + asunto + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", nombreArchivo='" + nombreArchivo + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                '}';
    }
// Agrega constructores, getters y setters, y otros métodos según sea necesario
}
