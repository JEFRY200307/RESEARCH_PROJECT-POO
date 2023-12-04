package pe.edu.uni.crowfunding.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.uni.crowfunding.model.MetodoPago;

import java.util.List;

@Repository
public interface MetodoPagoRepository {
    int saveMetodoPago(MetodoPago metodoPago);

    List<MetodoPago> findAllMetodosPago();

    boolean existsByIdUsuario(int idUsuario);
}
