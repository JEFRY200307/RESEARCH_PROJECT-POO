package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.pag_inicio.controller.dto.*;
import pe.edu.uni.pag_inicio.repository.AdminRepository;

import java.util.List;


@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<ProyectoDTO> findAllProyectos() {
        return adminRepository.findAllProyectos();
    }
    public List<UsuarioDTO> findAllUsuarios() {
        return adminRepository.findAllUsuarios();
    }
    public List<CreadorDTO> findAllCreadores() {
        return adminRepository.findAllCreadores();
    }
    public List<MetodoPagoDTO> findAllMetodosPago() {
        return adminRepository.findAllMetodosPago();
    }

    public ProyectoDTO crearProyecto(ProyectoDTO dto) {
        return adminRepository.Crearproyecto(dto);
    }
    public ProyectoDTO actualizarProyecto(int idProyecto, ProyectoDTO proyectoDTO) {
        return adminRepository.actualizarProyecto(idProyecto, proyectoDTO);
    }
    public Mensajedto borrarProyecto(int id, String s) {
        return adminRepository.borrarProyecto(id, s);
    }

    public List<OperacionesFinancierasDTO> findAllOperacionesFinancieras() {
        return adminRepository.findAllOperacionesFinancieras();
    }
    public List<RecompensaDTO> findAllRecompensas() {
        return adminRepository.findAllRecompensas();
    }

    public void responderContacto(int idContacto, String respuesta) {
        adminRepository.responderContacto(idContacto, respuesta);
    }


}
