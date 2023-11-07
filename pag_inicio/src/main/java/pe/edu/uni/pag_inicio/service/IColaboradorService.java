package pe.edu.uni.pag_inicio.service;

import pe.edu.uni.pag_inicio.entity.OperacionesFinancieras;
import pe.edu.uni.pag_inicio.entity.Proyectos;
import pe.edu.uni.pag_inicio.entity.Usuarios;

import java.util.List;

public interface IColaboradorService {
    void colaborarEnProyecto(Usuarios id, Proyectos proyecto, long monto);
    List<OperacionesFinancieras> obtenerColaboracionesDeUsuario(int id_usuario);
    List<OperacionesFinancieras> obtenerColaboracionesEnProyecto(Proyectos proyecto);
    double calcularMontoTotalContribuido(Usuarios usuario);
    void cancelarColaboracion(OperacionesFinancieras donaciones);
    List<Usuarios> obtenerColaboradoresDeProyecto(Proyectos proyecto);
}
