package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.pag_inicio.entity.Proyectos;
import pe.edu.uni.pag_inicio.repository.ProyectosRepository;
import pe.edu.uni.pag_inicio.service.IProyectosService;

import java.util.List;

@Service
public class ProyectosService implements IProyectosService {

    private final ProyectosRepository proyectosRepository; // Asumiendo que tienes un repositorio de proyectos

    @Autowired
    public ProyectosService(ProyectosRepository proyectosRepository) {
        this.proyectosRepository = proyectosRepository;
    }

    @Override
    public Proyectos createProyecto(Proyectos proyecto) {
        return proyectosRepository.save(proyecto);
    }

    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return proyectosRepository.save(proyecto);
    }

    @Override
    public Proyectos getProyectoById(int idProyecto) {
        return (Proyectos) proyectosRepository.findById(idProyecto).orElse(null);
    }


    @Override
    public List<Proyectos> getAllProyectos() {
        return proyectosRepository.findAll();
    }

    @Override
    public void deleteProyecto(int idProyecto) {
        proyectosRepository.deleteById(idProyecto);
    }
}
