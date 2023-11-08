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
    @Column(name = "id_contacto",unique = true)
    private int idcontacto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    @Column(name = "asunto")
    private String asunto;

    @Column(name = "mensaje", columnDefinition = "NVARCHAR(MAX)")
    private String mensaje;

    @Column(name = "fecha_envio")
    private Date fechaEnvio;

    @Column(name = "estado_aprobacion")
    private boolean estadoAprobacion;

    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    private Proyectos proyecto;

    @Override
    public String toString() {
        return "Contacto{" +
                "idcontacto=" + idcontacto +
                ", usuario=" + usuario +
                ", asunto='" + asunto + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                ", estadoAprobacion=" + estadoAprobacion +
                ", proyecto=" + proyecto +
                '}';
    }

    public Contacto(Usuarios usuario, String asunto, String mensaje, Date fechaEnvio, boolean estadoAprobacion, Proyectos proyecto) {
        this.usuario = usuario;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.estadoAprobacion = estadoAprobacion;
        this.proyecto = proyecto;
    }

    // Agrega constructores, getters y setters, y otros métodos según sea necesario
}
