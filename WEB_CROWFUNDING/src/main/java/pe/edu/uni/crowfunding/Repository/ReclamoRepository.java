package pe.edu.uni.crowfunding.Repository;

import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.Reclamo;

import java.util.List;

public interface ReclamoRepository {

    Mensajedto crearReclamo(Reclamo reclamoDto);

    List<Reclamo> getAllReclamo();

    Mensajedto actualizarReclamo(Reclamo reclamoDto);

    Mensajedto borrarReclamo(int idReclamo);
}
