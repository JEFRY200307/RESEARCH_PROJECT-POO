package pe.edu.uni.crowfunding.Repository;

import org.springframework.stereotype.Repository;

import pe.edu.uni.crowfunding.model.*;

@Repository
public interface SolicitudRepository {
    Solicitud solicitarmodificarProyecto(Solicitud solicitud);

    Solicitud obtenerSolicitudPorId(int idSolicitud);

    void actualizarSolicitud(Solicitud solicitud);
}
