package pe.edu.uni.pag_inicio.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Recompensas")
public class Recompensas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recompensa")
    private int idRecompensa;

    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    private Proyectos proyecto;

    @ManyToOne
    @JoinColumn(name = "id_recompcatal")
    private CatalogoRecompensas catalogoRecompensa;

    @Column(name = "tipo_nivel", length = 3, nullable = false)
    private String tipoNivel;

    // Constructores, getters y setters
}
