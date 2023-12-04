package pe.edu.uni.crowfunding.Repository;

import org.springframework.transaction.annotation.Transactional;

import pe.edu.uni.crowfunding.DTO.MensajeAdminDTO;

public interface AdminRepository {
    @Transactional
    MensajeAdminDTO responderSolicitud(int idContacto, MensajeAdminDTO mensajeAdminDTO);
}
