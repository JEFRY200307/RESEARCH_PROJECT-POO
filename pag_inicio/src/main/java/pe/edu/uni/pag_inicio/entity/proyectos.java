package pe.edu.uni.pag_inicio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@Data
@Entity // Added @Entity annotation
@Table(name = "proyectos") // Corrected table name with underscore
public class proyectos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String fecha;
    private long goal;
    private Integer promotores;

    public proyectos(int id, String name, String fecha, long goal, Integer promotores) {
        this.id = id;
        this.name = name;
        this.fecha = fecha;
        this.goal = goal;
        this.promotores = promotores;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "Nombre=" + name +
                ", fecha límite='" + fecha + '\'' +
                ", meta=" + goal +
                ", promotores=" + promotores +
                '}';
    }
}

