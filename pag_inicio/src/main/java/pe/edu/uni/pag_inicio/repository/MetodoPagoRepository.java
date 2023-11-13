package pe.edu.uni.pag_inicio.repository;

import org.springframework.stereotype.Repository;
import pe.edu.uni.pag_inicio.controller.dto.MetodoPagoDTO;

@Repository
public interface MetodoPagoRepository {
    int save2(MetodoPagoDTO metodoPago);

}
