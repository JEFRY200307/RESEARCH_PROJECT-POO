package pe.edu.uni.crowfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.uni.crowfunding.DTO.IdproyectoDTO;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.Proyecto;
import pe.edu.uni.crowfunding.service.ProyectoService;

import java.util.List;


@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
    
    @Autowired
    private ProyectoService proyectoService;
    @GetMapping("/categoria")
    public ResponseEntity<List<Proyecto>> obtenerProyectosPorCategoria(@RequestParam(name = "categoria") String categoria) {
        try {
            List<Proyecto> proyectos = proyectoService.obtenerProyectosPorCategoria(categoria);
            return new ResponseEntity<>(proyectos, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Manejo de errores, puedes personalizar según tus necesidades
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ver")
    public ResponseEntity<List<Proyecto>> obtenerProyecto() {
        List<Proyecto> proyecto = proyectoService.getAllProyectos();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    @GetMapping("/ver/{idcreador}")
    public ResponseEntity<List<IdproyectoDTO>> obtenerProyectoPorIdcreador(@PathVariable int idcreador) {
        List<IdproyectoDTO> proyecto = proyectoService.getAllProyectorbyCreador(idcreador);
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public Mensajedto borrarProyecto(@PathVariable int id) {
        return proyectoService.borrarproyecto(id);
    }
}

