package pe.edu.uni.pag_inicio.repository;

import pe.edu.uni.pag_inicio.controller.dto.*;

import java.util.List;

public interface AdminRepository {
    List<ProyectoDTO> findAllProyectos();
    List<UsuarioDTO> findAllUsuarios();
    List<CreadorDTO> findAllCreadores();
    List<MetodoPagoDTO> findAllMetodosPago();

    ProyectoDTO Crearproyecto(ProyectoDTO dto);
    ProyectoDTO actualizarProyecto(int idProyecto, ProyectoDTO proyectoDTO);

    Mensajedto borrarProyecto(int id, String s);

    List<OperacionesFinancierasDTO> findAllOperacionesFinancieras();
    List<RecompensaDTO> findAllRecompensas();
    void responderContacto(int idContacto, String respuesta);
}
