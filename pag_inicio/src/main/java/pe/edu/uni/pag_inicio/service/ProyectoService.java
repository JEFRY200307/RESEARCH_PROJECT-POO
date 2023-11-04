package pe.edu.uni.pag_inicio.service;

import pe.edu.uni.pag_inicio.entity.Proyectos;

import java.util.List;
import java.util.Optional;

public class ProyectoService implements IProyectoService {

    @Override
    public List<Proyectos> findAll() {
        return null;
    }

    @Override
    public List<Proyectos> findAllByCateogoría(String categoria) {
        return null;
    }

    @Override
    public Optional<Proyectos> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Proyectos> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Proyectos> findByCoincidencia(String incompleto) {
        return Optional.empty();
    }

    @Override
    public List<Proyectos> findProyectosActivos(Boolean activo) {
        return null;
    }

    @Override
    public List<Proyectos> findProyectosMasPopulares(int numero) {
        return null;
    }

    @Override
    public List<Proyectos> findProyectosByCategoria(String category) {
        return null;
    }

    @Override
    public Proyectos save(Proyectos proyecto) {
        return null;
    }

    @Override
    public Proyectos update(Proyectos proyecto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public boolean isProjectFunded(Proyectos proyecto) {
        return false;
    }
}
