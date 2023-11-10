package pe.edu.uni.pag_inicio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Creador")
public class Creador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_creador")
    private int idCreador;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    // Relación con proyectos (uno a muchos)
    @OneToMany(mappedBy = "id_creador")
    private List<Proyectos> proyectos;

    @Column(name = "estadoCreador")
    private boolean estadoCreador;

    @Column(name = "es_administrador")
    private boolean esAdministrador;

    @Column(name = "fecha_creacion")
    private Date fecha_creacion;

    @Override
    public String toString() {
        return "Creador{" +
                "id_creador=" + idCreador +
                ", usuario=" + usuario +
                ", proyectos=" + proyectos +
                ", estadoCreador=" + estadoCreador +
                ", esAdministrador=" + esAdministrador +
                ", fecha_creacion=" + fecha_creacion +
                '}';
    }

    // getters and setters
}

