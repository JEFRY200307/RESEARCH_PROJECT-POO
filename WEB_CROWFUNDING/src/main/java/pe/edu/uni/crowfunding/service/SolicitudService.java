package pe.edu.uni.crowfunding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.uni.crowfunding.Repository.ProyectoRepository;
import pe.edu.uni.crowfunding.Repository.SolicitudRepository;
import pe.edu.uni.crowfunding.model.Proyecto;
import pe.edu.uni.crowfunding.model.Solicitud;


@Service
public class SolicitudService {
    private final SolicitudRepository solicitudRepository;
    private final ProyectoRepository proyectoRepository;
    @Autowired
    public SolicitudService(SolicitudRepository solicitudRepository, ProyectoRepository proyectoRepository) {
        this.solicitudRepository = solicitudRepository;
        this.proyectoRepository = proyectoRepository;
    }
    public Solicitud obtenerSolicitudPorId(int idSolicitud){
      return  solicitudRepository.obtenerSolicitudPorId(idSolicitud);
    }

    public void actualizarSolicitud(Solicitud solicitud){
        solicitudRepository.actualizarSolicitud(solicitud);
    }

    public Solicitud solModProyecto (Solicitud solicitud){
        return  solicitudRepository.solicitarmodificarProyecto(solicitud);
    }
    public Proyecto actualizarProyectoDesdeSolicitud(int idSolicitud, boolean aprobar) {
        // Obtener la información de la solicitud
        Solicitud solicitud = solicitudRepository.obtenerSolicitudPorId(idSolicitud);

        // Verificar si la solicitud existe
        if (solicitud == null) {
            throw new RuntimeException("Solicitud no encontrada con ID: " + idSolicitud);
        }

        // Verificar si la solicitud ya fue aprobada o rechazada
        if (solicitud.getAprobacion() != 0) {
            throw new RuntimeException("La solicitud ya ha sido procesada.");
        }

        // Obtener la información actual del proyecto asociado a la solicitud
        Proyecto proyecto = proyectoRepository.obtenerProyectoPorId(solicitud.getIdproyecto());

        // Verificar si el proyecto existe
        if (proyecto == null) {
            throw new RuntimeException("Proyecto no encontrado con ID: " + solicitud.getIdproyecto());
        }

        // Actualizar el proyecto según la decisión de la solicitud
        if (aprobar) {
            // Actualizar el proyecto solo si la solicitud fue aprobada
            proyecto = proyectoRepository.actualizarProyecto(proyecto.getIdproyecto(), solicitud);
        }

        // Actualizar el estado de aprobación en la solicitud
        solicitud.setAprobacion(aprobar ? 2 : 1); // 2 para aprobado, 1 para rechazado
        solicitudRepository.actualizarSolicitud(solicitud);

        return proyecto;
    }
}
