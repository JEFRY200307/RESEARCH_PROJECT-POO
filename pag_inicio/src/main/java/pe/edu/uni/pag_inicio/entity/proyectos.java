package pe.edu.uni.pag_inicio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@Data
@Entity // Added @Entity annotation
@Table(name = "Proyectos") // Corrected table name with underscore
public class Proyectos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Id_proyecto", unique = true)
    private int id;
    @Column(name = "Nombre", unique = true)
    private String name;
    @Column(name = "Fecha_máx")
    private String fecha;
    @Column(name = "Monto_alcanzado")
    private long monto_alcanzado;
    @Column(name = "Meta")
    private long goal;
    @Column(name = "Num_colaboradores")
    private Integer promotores;
    @Column(name = "Categoría")
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "Propietario_id") // Nombre de la columna que representa la relación
    private User propietario;

    public Proyectos(int id, String name, String fecha, long monto_alcanzado, long goal, Integer promotores, String categoria, User propietario) {
        this.id = id;
        this.name = name;
        this.fecha = fecha;
        this.monto_alcanzado = monto_alcanzado;
        this.goal = goal;
        this.promotores = promotores;
        this.categoria = categoria;
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Proyectos{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fecha='" + fecha + '\'' +
                ", monto_alcanzado=" + monto_alcanzado +
                ", goal=" + goal +
                ", promotores=" + promotores +
                ", categoria='" + categoria + '\'' +
                ", propietario=" + propietario +
                '}';
    }
}

