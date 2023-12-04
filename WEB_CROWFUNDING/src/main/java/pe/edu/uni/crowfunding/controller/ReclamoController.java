package pe.edu.uni.crowfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.Reclamo;
import pe.edu.uni.crowfunding.service.ReclamoService;

@RestController
@RequestMapping("/reclamo")
public class ReclamoController {

    @Autowired
    private ReclamoService reclamoService;

    @PostMapping("/crear")
    public Mensajedto crearReclamo(@RequestBody Reclamo reclamoDto) {
        Mensajedto mensaje;
        try {
            mensaje = reclamoService.crearReclamo(reclamoDto);
        } catch (Exception e) {
            mensaje = new Mensajedto(-1, "Error: " + e.getMessage());
        }
        return mensaje;
    }

    @PutMapping("/actualizar")
    public  Mensajedto actualizarReclamo(@RequestBody Reclamo reclamoDto){
        return reclamoService.actualizarReclamo(reclamoDto);
    }

    @DeleteMapping("/borrar/{idReclamo}")
    public Mensajedto borrarReclamo (@PathVariable int idReclamo){
        return reclamoService.borrarReclamo(idReclamo);
    }

}
