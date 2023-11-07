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
@Table(name = "MetodoPago")
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_metodopago;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;
    @Column(name = "tipo_tarjeta")
    private String tipo_tarjeta;
    @Column(name = "nombre_titular")
    private String nombre_titular;
    @Column(name = "pago")
    private int pago;
    @Column(name = "fecha_expiracion")
    private Date fecha_expiracion;
    @Column(name = "cvv")
    private String cvv;

    @Override
    public String toString() {
        return "MetodoPago{" +
                "id_metodopago=" + id_metodopago +
                ", usuario=" + usuario +
                ", tipo_tarjeta='" + tipo_tarjeta + '\'' +
                ", nombre_titular='" + nombre_titular + '\'' +
                ", pago=" + pago +
                ", fecha_expiracion=" + fecha_expiracion +
                ", cvv='" + cvv + '\'' +
                '}';
    }
// getters and setters
}
