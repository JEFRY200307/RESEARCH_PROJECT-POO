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
@Table(name = "OperacionesFinancieras")
public class OperacionesFinancieras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacion")
    private int idOperacion;

    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    private Proyectos proyecto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    private int monto;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_operacion")
    private Date fechaOperacion;

    private int valoracion;

    @Override
    public String toString() {
        return "OperacionesFinancieras{" +
                "idOperacion=" + idOperacion +
                ", proyecto=" + proyecto +
                ", usuario=" + usuario +
                ", monto=" + monto +
                ", fechaOperacion=" + fechaOperacion +
                ", valoracion=" + valoracion +
                '}';
    }
// Constructores, getters y setters
}
