package pe.edu.uni.pag_inicio.repository;

import pe.edu.uni.pag_inicio.entity.*;

import java.util.List;

public interface AdminRepository {
    List<Proyectos> findAllProyectos();
    List<Usuarios> findAllUsuarios();
    List<Creador> findAllCreadores();
    List<MetodoPago> findAllMetodosPago();
    void aprobarProyecto(int idProyecto, boolean aprobar);
    void bloquearCreador(int idCreador, boolean bloquear);
    void agregarAdministrador(int idUsuario, boolean esAdmin);
    List<OperacionesFinancieras> findAllOperacionesFinancieras();
    List<Recompensas> findAllRecompensas();
    void responderContacto(int idContacto, String respuesta);


}
