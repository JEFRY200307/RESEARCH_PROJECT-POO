package pe.edu.uni.crowfunding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.crowfunding.model.Donaciones;
import pe.edu.uni.crowfunding.Repository.DonacionesRepository;

@Service
public class DonacionesService {

    private final DonacionesRepository operacionFinancieraRepository;
    @Autowired
    public DonacionesService(DonacionesRepository donacionesRepository) {
        this.operacionFinancieraRepository = donacionesRepository;
    }

    public int donar(Donaciones dto) {
        return operacionFinancieraRepository.agregarDonacion(dto);
    }
}
