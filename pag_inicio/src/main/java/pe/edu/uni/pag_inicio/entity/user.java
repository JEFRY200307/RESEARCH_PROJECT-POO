package pe.edu.uni.pag_inicio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nombre;
        private String telefono;
        private String correo;
        private String tipoCliente; // Puede ser "colaborador" o "creador de proyectos"

        // Getters y setters
        // Otras propiedades y métodos

        // Relación con proyectos (uno a muchos)
        @OneToMany(mappedBy = "propietario")
        private List<proyectos> proyectos;

    public user(Long id, String nombre, String telefono, String correo, String tipoCliente) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.tipoCliente = tipoCliente;
        this.proyectos = proyectos;
    }


    }

