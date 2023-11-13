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
    @JoinColumn(name = "id_creador")
    private Creador idcreador;

    @Column(name = "asunto")
    private String asunto;

    @Column(name = "mensaje", columnDefinition = "NVARCHAR(MAX)")
    private String mensaje;

    @Column(name = "fecha_envio")
    private Date fechaEnvio;

    @Column(name = "estado_aprobacion")
    private boolean estadoAprobacion;
    @Column(name = "id_administrador")
    private int idadministrador;

    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    private Proyectos proyecto;

    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", idcreador=" + idcreador +
                ", asunto='" + asunto + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                ", estadoAprobacion=" + estadoAprobacion +
                ", idadministrador=" + idadministrador +
                ", proyecto=" + proyecto +
                '}';
    }

    public Contacto(Creador idcreador, String asunto, String mensaje, Date fechaEnvio, boolean estadoAprobacion, int idadministrador, Proyectos proyecto) {
        this.idcreador = idcreador;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.estadoAprobacion = estadoAprobacion;
        this.idadministrador = idadministrador;
        this.proyecto = proyecto;
    }

    // Agrega constructores, getters y setters, y otros métodos según sea necesario
}
