package pe.edu.uni.pag_inicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.pag_inicio.dto.Mensajedto;
import pe.edu.uni.pag_inicio.dto.Proyectodto;
import pe.edu.uni.pag_inicio.service.ProyectoService2;


@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
    
    @Autowired
    private ProyectoService2 proyService;

    @PostMapping("/crear")
    public Mensajedto crearProyecto(@RequestBody Proyectodto dto) {
        Mensajedto mensaje = null; new Mensajedto(1, "Proceso ok." );
        try {
            dto = proyService.Crearpro(dto);
            mensaje = new Mensajedto(1,"Proyecto creado correctamente: " + dto.getId_proyecto());
        } catch (Exception e) {
            mensaje = new Mensajedto(-1, "Error: " + e.getMessage());
        }
        return mensaje;
    }
    @DeleteMapping("/{id}")
    public Mensajedto borrarProyecto(@PathVariable int id) {
        return proyService.borrarProyecto(id);
    }
    
}

