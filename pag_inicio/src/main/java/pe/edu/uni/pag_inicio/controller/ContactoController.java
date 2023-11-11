package pe.edu.uni.pag_inicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudCreacionDTO;
import pe.edu.uni.pag_inicio.controller.dto.SolicitudModificacionDTO;
import pe.edu.uni.pag_inicio.service.ContactoService;

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
    @PostMapping("/modificacion")
    public ResponseEntity<String> solicitarModificacionProyecto(@RequestBody SolicitudModificacionDTO solicitud) {
        contactoService.modificarProyecto(solicitud);
        return new ResponseEntity<>("Solicitud de modificación enviada correctamente", HttpStatus.OK);
    }

}
