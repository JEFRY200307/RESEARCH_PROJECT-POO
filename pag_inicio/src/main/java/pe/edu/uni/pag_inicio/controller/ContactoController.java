package pe.edu.uni.pag_inicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.pag_inicio.controller.dto.ContactoDTO;
import pe.edu.uni.pag_inicio.controller.dto.ProyectoDTO;
import pe.edu.uni.pag_inicio.entity.Contacto;
import pe.edu.uni.pag_inicio.entity.Creador;
import pe.edu.uni.pag_inicio.entity.Proyectos;
import pe.edu.uni.pag_inicio.service.ContactoService;

@RestController
@RequestMapping("/contacto")
public class ContactoController {

    private final ContactoService contactoService;

    @Autowired
    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }
    @PostMapping("/solicitar-creacion")
    public ResponseEntity<String> solicitarCreacion(@RequestBody ContactoDTO contactoDTO, @RequestBody ProyectoDTO proyectoDTO) {
        contactoService.solicitarCreacion(contactoDTO, proyectoDTO);
        return ResponseEntity.ok("Solicitud de creación enviada con éxito.");
    }

    @PostMapping("/solicitar-modificacion")
    public ResponseEntity<String> solicitarModificacion(@RequestBody ContactoDTO contactoDTO, @RequestBody ProyectoDTO proyectoDTO) {
        contactoService.solicitarModificacion(contactoDTO, proyectoDTO);
        return ResponseEntity.ok("Solicitud de modificación enviada con éxito.");
    }


}
