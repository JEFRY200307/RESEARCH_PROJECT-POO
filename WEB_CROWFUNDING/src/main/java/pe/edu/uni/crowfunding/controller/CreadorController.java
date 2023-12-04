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

import java.util.List;

@RestController
@RequestMapping("/solicitud")
public class CreadorController {

    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private SolicitudService solicitudService;
    @Autowired
    private MetodoPagoService metodoPagoService;
    @Autowired
    private  ReclamoService reclamoService;

    // Metodos de Proyecto
    @PostMapping("/crearproyecto/{idUsuario}")
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
    @PostMapping("/actualizarproyecto/{id}")
    public ResponseEntity<String> solicitarmodificion(@RequestBody Solicitud solicitud) {

        // Llamar al servicio para solicitar la modificación del proyecto
        solicitudService.solModProyecto(solicitud);

        return new ResponseEntity<>("Solicitud de modificación exitosa", HttpStatus.OK);
    }

    @GetMapping("/activos/{idCreador}")
    public ResponseEntity<List<IdproyectoDTO>> obtenerProyectosActivosPorCreador(@PathVariable int idCreador) {
        List<IdproyectoDTO> proyectos = proyectoService.getAllProyectorbyCreador(idCreador);
        if (proyectos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }
    @PostMapping("/crearReclamo/{idUsuario}")
    public Mensajedto crearReclamo(@PathVariable int idUsuario,@RequestBody Reclamo reclamo) {
        Mensajedto mensaje;
        try {
            reclamo.setIdusuario(idUsuario);
            mensaje = reclamoService.crearReclamo(reclamo);

        } catch (Exception e) {
            mensaje = new Mensajedto(-1,"Error: "+ e.getMessage());
        }
        return mensaje;
    }

}
