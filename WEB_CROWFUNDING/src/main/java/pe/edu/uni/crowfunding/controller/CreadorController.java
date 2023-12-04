package pe.edu.uni.crowfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.crowfunding.DTO.IdproyectoDTO;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.*;
import pe.edu.uni.crowfunding.service.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/UniOpportunity/login/catalogo/solicitud")
public class CreadorController {

    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private SolicitudService solicitudService;
    @Autowired
    private MetodoPagoService metodoPagoService;

    // Metodos de Proyecto
    @PostMapping("/crearproyecto")
    public ResponseEntity<Mensajedto> crearProyecto(@PathVariable int idUsuario, @RequestBody Proyecto proyecto) {
        try {
            // Verificar si el usuario tiene un método de pago
            if (!metodoPagoService.usuarioTieneMetodoPago(idUsuario)) {
                return new ResponseEntity<>(new Mensajedto(-1, "El usuario no tiene un método de pago registrado."), HttpStatus.BAD_REQUEST);
            }

            // Crear el proyecto
            proyecto.setIdusuario(idUsuario);
            proyecto = proyectoService.crearProyecto(proyecto);

            return new ResponseEntity<>(new Mensajedto(1, "Proyecto creado correctamente: " + proyecto.getIdproyecto()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensajedto(-1, "Error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/todosLosProyectos/editarproyecto/{idProyecto}")
    public ResponseEntity<String> obtenerDetallesProyecto(@RequestBody int idproyecto) {

        // Llamar al servicio para solicitar la modificación del proyecto
        proyectoService.obtenerProyectoPorId(idproyecto);

        return new ResponseEntity<>("Solicitud de modificación exitosa", HttpStatus.OK);
    }
    @PostMapping("/todosLosProyectos/editarproyecto/{idProyecto}/modificar")
    public ResponseEntity<String> solicitarmodificion(@PathVariable int idProyecto,@RequestBody Solicitud solicitud) {

        // Llamar al servicio para solicitar la modificación del proyecto
        solicitudService.solModProyecto(solicitud);

        return new ResponseEntity<>("Solicitud de modificación exitosa", HttpStatus.OK);
    }

    @GetMapping("/todosLosProyectos")
    public ResponseEntity<?> obtenerProyectosActivosPorCreador(@PathVariable int idCreador) {
        List<IdproyectoDTO> proyectos = proyectoService.getAllProyectorbyCreador(idCreador);

        if (proyectos == null || proyectos.isEmpty()) {
            String mensaje = "No hay ningún proyecto aprobado todavía.";
            List<String> mensajeList = Collections.singletonList(mensaje);
            return new ResponseEntity<>(mensajeList, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }



}
