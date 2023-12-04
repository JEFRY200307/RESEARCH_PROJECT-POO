package pe.edu.uni.crowfunding.Repository;

import org.springframework.stereotype.Repository;
import pe.edu.uni.crowfunding.model.Donaciones;

@Repository
public interface DonacionesRepository {

    int agregarDonacion(Donaciones dto);
}
