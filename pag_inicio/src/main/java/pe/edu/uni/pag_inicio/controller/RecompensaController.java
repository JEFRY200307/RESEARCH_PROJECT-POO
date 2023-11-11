package pe.edu.uni.pag_inicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.pag_inicio.dto.Mensajedto;
import pe.edu.uni.pag_inicio.dto.Recompensadto;
import pe.edu.uni.pag_inicio.service.RecompensaService;

@RestController
@RequestMapping("/recompensa")
public class RecompensaController {
    
    @Autowired
    private RecompensaService recompensaService;

    @PostMapping("/insertar/{idProyecto}")
    public Mensajedto crearRecompensa(@RequestBody Recompensadto dto, @PathVariable int idProyecto) {
        Mensajedto mensaje = null;
        try {
            dto = recompensaService.crearRecompensa(dto, idProyecto);
            mensaje = new Mensajedto(1, "Recompensa creada correctamente: " + dto.getTipo_nivel());
        } catch (Exception e) {
            mensaje = new Mensajedto(-1, "Error: " + e.getMessage());
        }
        return mensaje;
    }
}