package pe.edu.uni.pag_inicio.service;

import pe.edu.uni.pag_inicio.entity.Proyectos;

import java.util.List;

public interface IProyectosService {
        Proyectos createProyecto(Proyectos proyecto);

        Proyectos updateProyecto(Proyectos proyecto);

        Proyectos getProyectoById(int idProyecto);

        List<Proyectos> getAllProyectos();

        void deleteProyecto(int idProyecto);
    }


