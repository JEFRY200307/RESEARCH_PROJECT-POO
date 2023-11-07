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
@Table(name = "Creador")
public class Creador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_creador;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    private boolean estadoCreador;

    private Date fecha_creacion;

    @Override
    public String toString() {
        return "Creador{" +
                "id_creador=" + id_creador +
                ", usuario=" + usuario +
                ", estadoCreador=" + estadoCreador +
                ", fecha_creacion=" + fecha_creacion +
                '}';
    }
// getters and setters
}

