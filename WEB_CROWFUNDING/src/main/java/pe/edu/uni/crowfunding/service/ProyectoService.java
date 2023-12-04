package pe.edu.uni.crowfunding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.crowfunding.DTO.IdproyectoDTO;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.Proyecto;
import pe.edu.uni.crowfunding.Repository.ProyectoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;
    @Autowired
    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    // Metodos de Proyecto
    public List<Proyecto> obtenerProyectosPorCategoria(String categoria) {
        return proyectoRepository.obtenerProyectosPorCategoria(categoria);
    }

    public List<Proyecto> getAllProyectos() {
        return proyectoRepository.findAllProyectos();
    }
    public  Proyecto crearProyecto (Proyecto proyecto){
        return  proyectoRepository.saveProyecto(proyecto);
    }

    public Mensajedto borrarproyecto (int idproyecto){
        return proyectoRepository.borrarProyecto(idproyecto);
    }

    public List<IdproyectoDTO> getAllProyectorbyCreador(int idCreador){
        return  proyectoRepository.obtenerProyectosAprobadosPorCreador(idCreador);
    }
}
