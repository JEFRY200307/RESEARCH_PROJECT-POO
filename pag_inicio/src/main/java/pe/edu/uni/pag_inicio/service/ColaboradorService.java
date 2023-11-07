package pe.edu.uni.pag_inicio.service;

import pe.edu.uni.pag_inicio.entity.OperacionesFinancieras;
import pe.edu.uni.pag_inicio.entity.Proyectos;
import pe.edu.uni.pag_inicio.entity.Usuarios;

import java.util.List;

public class ColaboradorService implements IColaboradorService{
    @Override
    public void colaborarEnProyecto(Usuarios id, Proyectos proyecto, long monto) {

    }

    @Override
    public List<OperacionesFinancieras> obtenerColaboracionesDeUsuario(int id_usuario) {
        return null;
    }

    @Override
    public List<OperacionesFinancieras> obtenerColaboracionesEnProyecto(Proyectos proyecto) {
        return null;
    }

    @Override
    public double calcularMontoTotalContribuido(Usuarios usuario) {
        return 0;
    }

    @Override
    public void cancelarColaboracion(OperacionesFinancieras donaciones) {

    }

    @Override
    public List<Usuarios> obtenerColaboradoresDeProyecto(Proyectos proyecto) {
        return null;
    }
}
