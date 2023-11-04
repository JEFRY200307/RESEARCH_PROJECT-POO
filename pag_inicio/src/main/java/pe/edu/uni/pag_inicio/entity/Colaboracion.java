package pe.edu.uni.pag_inicio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Colaboracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User usuario;
    @ManyToOne
    private Proyectos proyecto;
    private double monto;
    private LocalDateTime fechaColaboracion;

    public Colaboracion(Long id, User usuario, Proyectos proyecto, double monto, LocalDateTime fechaColaboracion) {
        this.id = id;
        this.usuario = usuario;
        this.proyecto = proyecto;
        this.monto = monto;
        this.fechaColaboracion = fechaColaboracion;
    }

    public Colaboracion() {

    }

    @Override
    public String toString() {
        return "Colaboracion{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", proyecto=" + proyecto +
                ", monto=" + monto +
                ", fechaColaboracion=" + fechaColaboracion +
                '}';
    }
}
