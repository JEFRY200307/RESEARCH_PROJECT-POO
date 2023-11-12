package pe.edu.uni.pag_inicio.repository;

import pe.edu.uni.pag_inicio.entity.*;

import java.util.List;

public class AdminRepositoryImpl implements AdminRepository{
    @Override
    public List<Proyectos> findAllProyectos() {
        return null;
    }

    @Override
    public List<Usuarios> findAllUsuarios() {
        return null;
    }

    @Override
    public List<Creador> findAllCreadores() {
        return null;
    }

    @Override
    public List<MetodoPago> findAllMetodosPago() {
        return null;
    }

    @Override
    public void aprobarProyecto(int idProyecto, boolean aprobar) {

    }

    @Override
    public void bloquearCreador(int idCreador, boolean bloquear) {

    }

    @Override
    public void agregarAdministrador(int idUsuario, boolean esAdmin) {

    }

    @Override
    public List<OperacionesFinancieras> findAllOperacionesFinancieras() {
        return null;
    }

    @Override
    public List<Recompensas> findAllRecompensas() {
        return null;
    }

    @Override
    public void responderContacto(int idContacto, String respuesta) {

    }
}
