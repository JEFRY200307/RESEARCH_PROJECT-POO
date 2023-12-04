package pe.edu.uni.crowfunding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.crowfunding.model.MetodoPago;
import pe.edu.uni.crowfunding.Repository.MetodoPagoRepository;

import java.util.List;

@Service
public class MetodoPagoService {
    private final MetodoPagoRepository metodoPagoRepository;
    private final UsuariosService usuariosService;

    @Autowired
    public MetodoPagoService(
            MetodoPagoRepository metodoPagoRepository,
            UsuariosService usuariosService
    ) {
        this.metodoPagoRepository = metodoPagoRepository;
        this.usuariosService = usuariosService;
    }

    public void agregarMetodoPago(MetodoPago metodoPagoDto) {
        if (usuariosService.getUserById(metodoPagoDto.getIdUsuario()) != null) {
            metodoPagoRepository.saveMetodoPago(metodoPagoDto);
        }
    }
    public List<MetodoPago> fillAllMetodoPago (){
        return  metodoPagoRepository.findAllMetodosPago();
    }
    public boolean usuarioTieneMetodoPago(int idUsuario) {
        // Verificar si el usuario tiene un método de pago registrado
        return metodoPagoRepository.existsByIdUsuario(idUsuario);
    }
}

