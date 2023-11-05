package pe.edu.uni.pag_inicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.uni.pag_inicio.entity.Proyectos;
import java.util.List;
import java.awt.*;
import java.util.Optional;
@Repository
public interface RepoProyectos extends JpaRepository<Proyectos, Integer> {
    @Query("SELECT p FROM Proyectos p WHERE FUNCTION('LEVENSHTEIN', p.name, :incompleto) <= 2")
    List<Proyectos> findByCoincidencia(@Param("incompleto") String incompleto);

    @Query("SELECT p FROM Proyectos p " +
            "ORDER BY p.categoria, ABS(p.goal - :goal) ASC")
    List<Proyectos> findProyectosOrdenadosPorCategoriaYGoal(@Param("goal") long goal);

    List<Proyectos> findByCategoria(String categoria);

    Optional<Proyectos> findByName(String name);

    List<Proyectos> findByActivo(Boolean activo);
}