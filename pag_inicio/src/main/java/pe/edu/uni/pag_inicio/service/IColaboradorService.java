package pe.edu.uni.pag_inicio.service;

import pe.edu.uni.pag_inicio.entity.Colaboracion;
import pe.edu.uni.pag_inicio.entity.Proyectos;
import pe.edu.uni.pag_inicio.entity.User;

import java.util.List;

public interface IColaboradorService {
    void colaborarEnProyecto(User id, Proyectos proyecto, long monto);
    List<Colaboracion> obtenerColaboracionesDeUsuario(int id_usuario);
    List<Colaboracion> obtenerColaboracionesEnProyecto(Proyectos proyecto);
    double calcularMontoTotalContribuido(User usuario);
    void cancelarColaboracion(Colaboracion colaboracion);
    List<User> obtenerColaboradoresDeProyecto(Proyectos proyecto);
}
