package pe.edu.uni.pag_inicio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CatalogoRecompensas")
public class CatalogoRecompensas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recompcatal")
    private int idRecompCatal;

    @Column(name = "nombre_recompensa", length = 250, nullable = false)
    private String nombreRecompensa;

    @Column(name = "valor_recompensa", nullable = false)
    private int valorRecompensa;

    @Override
    public String toString() {
        return "CatalogoRecompensas{" +
                "idRecompCatal=" + idRecompCatal +
                ", nombreRecompensa='" + nombreRecompensa + '\'' +
                ", valorRecompensa=" + valorRecompensa +
                '}';
    }
// Constructores, getters y setters
}
