package pe.edu.uni.crowfunding.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.Reclamo;
import pe.edu.uni.crowfunding.Repository.ReclamoRepository;

import java.util.List;

@Service
public class ReclamoService {
    private final ReclamoRepository reclamoRepository;
    @Autowired
    public ReclamoService(ReclamoRepository reclamoRepository) {
        this.reclamoRepository = reclamoRepository;
    }

    public Mensajedto crearReclamo (Reclamo reclamoDto){
        return  reclamoRepository.crearReclamo(reclamoDto);
    }
    
    public List<Reclamo> obtenerReclamo (){
        return  reclamoRepository.getAllReclamo();
    }
    
    public Mensajedto actualizarReclamo (Reclamo reclamoDto){
        return  reclamoRepository.actualizarReclamo(reclamoDto);
    }
    
    public Mensajedto borrarReclamo (int idreclamo){
        return  reclamoRepository.borrarReclamo(idreclamo);
    }
    
    
    
    
}

