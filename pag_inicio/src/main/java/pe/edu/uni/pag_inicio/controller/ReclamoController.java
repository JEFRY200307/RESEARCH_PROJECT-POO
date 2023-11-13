package pe.edu.uni.pag_inicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.pag_inicio.controller.dto.Mensajedto;
import pe.edu.uni.pag_inicio.controller.dto.ReclamoDto;
import pe.edu.uni.pag_inicio.service.ReclamoService;

@RestController
@RequestMapping("/reclamo")
public class ReclamoController {

    @Autowired
    private ReclamoService reclamoService;

    @PostMapping("/crear")
    public Mensajedto crearReclamo(@RequestBody ReclamoDto reclamoDto) {
        Mensajedto mensaje;
        try {
            mensaje = reclamoService.crearReclamo(reclamoDto);
        } catch (Exception e) {
            mensaje = new Mensajedto(-1, "Error: " + e.getMessage());
        }
        return mensaje;
    }
}
