package pe.edu.uni.pag_inicio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Proyectos")
public class Proyectos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto",unique = true)
    private int id_proyecto;

    @Column(name = "titulo",nullable = false, length = 200)
    private String titulo;

    @Column(name = "descripcion",nullable = false, length = 4000)
    private String descripcion;

    @Column(name = "objetivos",nullable = false, length = 4000)
    private String objetivos;

    @Column(name = "recaudacion",nullable = false)
    private int recaudacion;

    @Column(name = "fecha_inicio",nullable = false)
    private Date fecha_inicio;

    @Column(name = "fecha_fin",nullable = false)
    private Date fecha_fin;

    @Column(name = "estado",nullable = false)
    private boolean estado;

    @Column(name = "monto_objetivo",nullable = false)
    private int monto_objetivo;

    @Column(name = "image_url",nullable = false, length = 250)
    private String image_url;

    @Column(name = "categoria",nullable = false, length = 50)
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;
    @ManyToOne
    @JoinColumn(name = "id_creador")
    private Creador id_creador;

    // Constructor, getters, and setters


    @Override
    public String toString() {
        return "Proyectos{" +
                "id_proyecto=" + id_proyecto +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", objetivos='" + objetivos + '\'' +
                ", recaudacion=" + recaudacion +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", estado=" + estado +
                ", monto_objetivo=" + monto_objetivo +
                ", image_url='" + image_url + '\'' +
                ", categoria='" + categoria + '\'' +
                ", usuario=" + usuario +
                ", id_creador=" + id_creador +
                '}';
    }
}

