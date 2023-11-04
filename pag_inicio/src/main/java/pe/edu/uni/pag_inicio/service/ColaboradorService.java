package pe.edu.uni.pag_inicio.service;

import pe.edu.uni.pag_inicio.entity.Colaboracion;
import pe.edu.uni.pag_inicio.entity.Proyectos;
import pe.edu.uni.pag_inicio.entity.User;

import java.util.List;

public class ColaboradorService implements IColaboradorService{
    @Override
    public void colaborarEnProyecto(User id, Proyectos proyecto, long monto) {

    }

    @Override
    public List<Colaboracion> obtenerColaboracionesDeUsuario(int id_usuario) {
        return null;
    }

    @Override
    public List<Colaboracion> obtenerColaboracionesEnProyecto(Proyectos proyecto) {
        return null;
    }

    @Override
    public double calcularMontoTotalContribuido(User usuario) {
        return 0;
    }

    @Override
    public void cancelarColaboracion(Colaboracion colaboracion) {

    }

    @Override
    public List<User> obtenerColaboradoresDeProyecto(Proyectos proyecto) {
        return null;
    }
}
