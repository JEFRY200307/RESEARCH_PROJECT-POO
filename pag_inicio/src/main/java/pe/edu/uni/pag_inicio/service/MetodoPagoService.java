package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.pag_inicio.controller.dto.MetodoPagoDTO;
import pe.edu.uni.pag_inicio.repository.MetodoPagoRepository;
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

    public void agregarMetodoPago(MetodoPagoDTO metodoPagoDto) {
        if (usuariosService.getUserById(metodoPagoDto.getId_usuario()) != null) {
            metodoPagoRepository.save2(metodoPagoDto);
        }
    }
}

