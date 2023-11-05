package pe.edu.uni.pag_inicio.service;

import pe.edu.uni.pag_inicio.entity.Proyectos;
import pe.edu.uni.pag_inicio.repository.RepoProyectos;

import java.util.*;

public class ProyectoService implements IProyectoService {

    private  RepoProyectos repositoriodeproyectos;
    public ProyectoService(RepoProyectos repositoriodeproyectos){
        this.repositoriodeproyectos=repositoriodeproyectos;
    }
    @Override
    public List<Proyectos> findAll() {
        return repositoriodeproyectos.findAll();
    }

    @Override
    public List<Proyectos> findAllByCateogoría(String categoria) {
        return repositoriodeproyectos.findByCategoria(categoria);
    }

    @Override
    public Optional<Proyectos> findById(int id) {
        return repositoriodeproyectos.findById(id);
    }

    @Override
    public Optional<Proyectos> findByName(String name) {
        return repositoriodeproyectos.findByName(name);
    }

    @Override
    public List<Proyectos> findByCoincidencia(String incompleto) {
        return repositoriodeproyectos.findByCoincidencia(incompleto);
    }

    @Override
    public List<Proyectos> findProyectosActivos(Boolean activo) {
        return repositoriodeproyectos.findByActivo(activo);
    }

    @Override
    public List<Proyectos> findProyectosMasPopulares(int numero) {
        List<Proyectos> proyectosMasPopulares = new ArrayList<>();

        // Obtener todos los proyectos agrupados por categoría y ordenados por proximidad a goal
        List<Proyectos> proyectosOrdenadosPorCategoriaYGoal = repositoriodeproyectos.findProyectosOrdenadosPorCategoriaYGoal(0);

        // Iterar sobre los proyectos ordenados
        Map<String, Integer> categoriaContadores = new HashMap<>();
        for (Proyectos proyecto : proyectosOrdenadosPorCategoriaYGoal) {
            String categoria = proyecto.getCategoria();

            if (!categoriaContadores.containsKey(categoria)) {
                categoriaContadores.put(categoria, 0);
            }

            int contador = categoriaContadores.get(categoria);
            if (contador < numero) {
                proyectosMasPopulares.add(proyecto);
                categoriaContadores.put(categoria, contador + 1);
            }
        }

        return proyectosMasPopulares;
    }

    @Override
    public List<Proyectos> findProyectosByCategoria(String category) {
        return repositoriodeproyectos.findByCategoria(category);
    }

    @Override
    public Proyectos save(Proyectos proyecto) {
        return repositoriodeproyectos.save(proyecto);
    }

    @Override
    public Proyectos update(Proyectos proyecto) {
        Optional<Proyectos> existingProyecto = repositoriodeproyectos.findById(proyecto.getId());
        if (existingProyecto.isPresent()) {
            Proyectos updatedProyecto = existingProyecto.get();
            updatedProyecto.setName(proyecto.getName());
            updatedProyecto.setFecha(proyecto.getFecha());
            updatedProyecto.setGoal(proyecto.getGoal());
            return repositoriodeproyectos.save(updatedProyecto);
        } else {
            throw new IllegalArgumentException("El proyecto no existe en la base de datos");
        }
    }

    @Override
    public void deleteById(Long id) {
        repositoriodeproyectos.deleteById(Math.toIntExact(id));
    }

    @Override
    public void deleteAll() {
        repositoriodeproyectos.deleteAll();
    }

    @Override
    public boolean isProjectFunded(Proyectos proyecto) {
        // Obtener el monto adquirido del proyecto
        long montoAdquirido = proyecto.getMontoAdquirido();
        // Obtener el monto objetivo del proyecto
        long montoObjetivo = proyecto.getGoal();

        // Verificar si el monto adquirido es menor al monto objetivo
        return montoAdquirido < montoObjetivo;
    }
}
