package pe.edu.uni.pag_inicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.uni.pag_inicio.entity.Contacto;

import java.io.Serializable;

public interface ContactRepository extends JpaRepository<Contacto, Integer> {
    public Contacto findById(int id);
}
