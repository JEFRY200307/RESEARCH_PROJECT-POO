package pe.edu.uni.pag_inicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.uni.pag_inicio.entity.Contacto;
import pe.edu.uni.pag_inicio.entity.Creador;

import java.util.List;
@Repository
public interface CreadorRepository extends JpaRepository<Creador, Integer> {
    Creador findByIdCreador(int id);
    Creador findByEsAdministradorAndEstadoCreador(boolean esAdministrador, boolean estadoCreador);
}
