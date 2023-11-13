package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.pag_inicio.controller.dto.ProyectoDTO;
import pe.edu.uni.pag_inicio.repository.ProyectoRepository;
import java.util.List;
@Service
public class ProyectoService{

    @Autowired
    private ProyectoRepository proyectoRepository;
    public List<ProyectoDTO> obtenerProyectosPorCategoria(String categoria) {
     return proyectoRepository.obtenerProyectosPorCategoria(categoria);
    }
}
