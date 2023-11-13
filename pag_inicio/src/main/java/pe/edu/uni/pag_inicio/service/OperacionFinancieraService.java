package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.pag_inicio.controller.dto.OperacionesFinancierasDTO;
import pe.edu.uni.pag_inicio.repository.OperacionFinancieraRepository;

@Service
public class OperacionFinancieraService {

    private final OperacionFinancieraRepository operacionFinancieraRepository;
    @Autowired
    public OperacionFinancieraService(OperacionFinancieraRepository operacionFinancieraRepository) {
        this.operacionFinancieraRepository = operacionFinancieraRepository;
    }

    public int donar(OperacionesFinancierasDTO dto) {
        return operacionFinancieraRepository.agregarDonacion(dto);
    }
}
