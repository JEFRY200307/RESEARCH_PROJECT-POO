package pe.edu.uni.pag_inicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.pag_inicio.entity.Proyectos;
import pe.edu.uni.pag_inicio.service.ProyectosService;

import java.util.List;

@RestController
@RequestMapping("/proyectos")
public class ProyectosController {

    private final ProyectosService proyectosService;

    @Autowired
    public ProyectosController(ProyectosService proyectosService) {
        this.proyectosService = proyectosService;
    }

    @PostMapping("/create")
    public Proyectos createProyecto(@RequestBody Proyectos proyecto) {
        return proyectosService.createProyecto(proyecto);
    }

    @PutMapping("/update")
    public Proyectos updateProyecto(@RequestBody Proyectos proyecto) {
        return proyectosService.updateProyecto(proyecto);
    }

    @GetMapping("/{idProyecto}")
    public Proyectos getProyectoById(@PathVariable int idProyecto) {
        return proyectosService.getProyectoById(idProyecto);
    }

    @GetMapping("/all")
    public List<Proyectos> getAllProyectos() {
        return proyectosService.getAllProyectos();
    }

    @DeleteMapping("/delete/{idProyecto}")
    public void deleteProyecto(@PathVariable int idProyecto) {
        proyectosService.deleteProyecto(idProyecto);
    }
}
