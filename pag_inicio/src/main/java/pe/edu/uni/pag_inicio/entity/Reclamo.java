package pe.edu.uni.pag_inicio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Reclamo")
public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reclamo")
    private int idReclamo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    @Column(columnDefinition = "NVARCHAR(MAX)", nullable = false)
    private String mensaje;

    @Column(name = "tipo_reclamo", length = 2, nullable = false)
    private String tipoReclamo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_reclamo")
    private Date fechaReclamo;

    @Override
    public String toString() {
        return "Reclamo{" +
                "idReclamo=" + idReclamo +
                ", usuario=" + usuario +
                ", mensaje='" + mensaje + '\'' +
                ", tipoReclamo='" + tipoReclamo + '\'' +
                ", fechaReclamo=" + fechaReclamo +
                '}';
    }
// Constructores, getters y setters
}