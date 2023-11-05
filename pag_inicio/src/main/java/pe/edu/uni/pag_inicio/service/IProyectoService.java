package pe.edu.uni.pag_inicio.service;
import pe.edu.uni.pag_inicio.entity.Proyectos;

import java.util.List;
import java.util.Optional;

public interface IProyectoService {
    //GET
    List<Proyectos> findAll();
    List<Proyectos> findAllByCateogoría(String categoria);
    Optional<Proyectos> findById(int id);
    Optional<Proyectos> findByName(String name);
    List<Proyectos> findByCoincidencia(String incompleto);
    List<Proyectos> findProyectosActivos(Boolean activo);
    List<Proyectos> findProyectosMasPopulares(int numero);
    List<Proyectos> findProyectosByCategoria(String category);
    //CREATE/UPDATE
    Proyectos save(Proyectos proyecto);
    Proyectos update(Proyectos proyecto);
    //DELETE
    void deleteById(Long id);
    void deleteAll();
    //ADICIONLES
    boolean isProjectFunded(Proyectos proyecto);
}
