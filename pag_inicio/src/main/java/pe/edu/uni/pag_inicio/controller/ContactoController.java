package pe.edu.uni.pag_inicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.pag_inicio.controller.dto.IdproyectoDTO;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudCreacionDTO;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudModificacionDTO;
import pe.edu.uni.pag_inicio.service.ContactoService;

import java.util.List;

@RestController
@RequestMapping("/contacto")
public class ContactoController {

    private final ContactoService contactoService;

    @Autowired
    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }
    @PostMapping("/crear")
    public ResponseEntity<String> solicitarCreacion(@RequestBody SolicitudCreacionDTO solicitudCreacionDTO) {
        contactoService.solicitarCreacion(solicitudCreacionDTO);
        return ResponseEntity.ok("Proyecto creado correctamente. Pendiente de aprobación por parte del admin.");
    }
    @PostMapping("/modificar")
    public ResponseEntity<String> solicitarmodificion(@RequestBody SolicitudModificacionDTO solicitudModificacionDTO) {
        try {
            contactoService.solicitarmodificion(solicitudModificacionDTO);
            return new ResponseEntity<>("Solicitud de modificación exitosa", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al procesar la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/activos/{idCreador}")
    public ResponseEntity<List<IdproyectoDTO>> obtenerProyectosActivosPorCreador(@PathVariable Long idCreador) {
        List<IdproyectoDTO> proyectos = contactoService.obtenerProyectosActivosPorCreador(idCreador);
        if (proyectos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }

}
