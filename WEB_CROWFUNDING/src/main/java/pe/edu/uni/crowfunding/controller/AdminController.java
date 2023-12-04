package pe.edu.uni.crowfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.crowfunding.DTO.MensajeAdminDTO;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.*;
import pe.edu.uni.crowfunding.service.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SolicitudService solicitudService;
    @Autowired
    private UsuariosService usuariosService;
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private ReclamoService reclamoService;
    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping("/proyectos")
    public List<Proyecto> getAllProyectos() {
        return proyectoService.getAllProyectos();
    }

    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuariosService.getAllUsers();
    }

    @GetMapping("/creadores")
    public List<Usuario> getAllCreadores() {
        return usuariosService.getAllCreador();
    }

    @GetMapping("/metodospago")
    public List<MetodoPago> getAllMetodosPago() {
        return metodoPagoService.fillAllMetodoPago();
    }

    @PostMapping("/aprobar/{idSolicitud}")
    public ResponseEntity<String> aprobarSolicitud(@PathVariable int idSolicitud) {
        try {
            // Obtener la solicitud
            Solicitud solicitud = solicitudService.obtenerSolicitudPorId(idSolicitud);

            // Verificar si la solicitud existe
            if (solicitud == null) {
                return new ResponseEntity<>("Solicitud no encontrada con ID: " + idSolicitud, HttpStatus.NOT_FOUND);
            }

            // Verificar si la solicitud ya fue procesada
            if (solicitud.getAprobacion() != 0) {
                return new ResponseEntity<>("La solicitud ya ha sido procesada.", HttpStatus.BAD_REQUEST);
            }

            // Aprobar la solicitud
            solicitud.setAprobacion(2);  // 2 para aprobado

            // Actualizar la solicitud en la base de datos
            solicitudService.actualizarSolicitud(solicitud);

            // Verificar si se debe actualizar el proyecto
            if (solicitud.getAprobacion() == 2) {
                // Obtener el proyecto asociado a la solicitud
                Proyecto proyecto = solicitudService.actualizarProyectoDesdeSolicitud(idSolicitud, true);

                // Verificar si el proyecto existe
                if (proyecto == null) {
                    return new ResponseEntity<>("Proyecto no encontrado con ID: " + solicitud.getIdproyecto(), HttpStatus.NOT_FOUND);
                }

                // Actualizar el proyecto en la base de datos
                // Aquí puedes llamar al método correspondiente de tu servicio o repositorio de proyectos
                // proyectoService.actualizarProyecto(proyecto.getIdproyecto(), proyecto);
            }

            return new ResponseEntity<>("Solicitud aprobada exitosamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/rechazar/{idSolicitud}")
    public ResponseEntity<String> rechazarSolicitud(@PathVariable int idSolicitud) {
        try {
            // Obtener la solicitud
            Solicitud solicitud = solicitudService.obtenerSolicitudPorId(idSolicitud);

            // Verificar si la solicitud existe
            if (solicitud == null) {
                return new ResponseEntity<>("Solicitud no encontrada con ID: " + idSolicitud, HttpStatus.NOT_FOUND);
            }

            // Verificar si la solicitud ya fue procesada
            if (solicitud.getAprobacion() != 0) {
                return new ResponseEntity<>("La solicitud ya ha sido procesada.", HttpStatus.BAD_REQUEST);
            }

            // Rechazar la solicitud
            solicitud.setAprobacion(1);  // 1 para rechazado

            // Actualizar la solicitud en la base de datos
            solicitudService.actualizarSolicitud(solicitud);

            // Puedes realizar acciones adicionales después del rechazo si es necesario

            return new ResponseEntity<>("Solicitud rechazada exitosamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/borrarproyecto/{id}")
    public ResponseEntity<String> borrarProyecto(@PathVariable int id) {
        try {
            Mensajedto mensaje = proyectoService.borrarproyecto(id);
            if (mensaje !=null){
                return new ResponseEntity<>("Se borro exitosamente", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Se borro exitosamente"+ id,  HttpStatus.NOT_FOUND);
            }
        }catch (RuntimeException e){
            return new ResponseEntity<>("Error al procesar la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping ("/reclamo")
    public  ResponseEntity<List<Reclamo>> obtenerReclamos (){
        List<Reclamo> reclamos = reclamoService.obtenerReclamo();
        return new ResponseEntity<>(reclamos,HttpStatus.OK);
    }
}
