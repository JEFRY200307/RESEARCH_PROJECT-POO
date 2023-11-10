package pe.edu.uni.pag_inicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.uni.pag_inicio.entity.Contacto;
import pe.edu.uni.pag_inicio.entity.Usuarios;

import java.io.Serializable;
import java.util.List;

public interface ContactRepository extends JpaRepository<Contacto, Integer> {
    Contacto findByIdcontacto(int id);

    List<Contacto> findAll();

    Contacto save(Contacto contacto);

    void delete(Contacto contacto);
}
