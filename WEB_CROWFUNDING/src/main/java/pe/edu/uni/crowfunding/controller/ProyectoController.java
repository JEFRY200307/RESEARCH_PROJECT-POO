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
@RequestMapping("/UniOpportunity/login/proyectos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProyectoController {
    
    @Autowired
    private ProyectoService proyectoService;
    @GetMapping("/categoria")
    public ResponseEntity<?> obtenerProyectosPorCategoria(@RequestParam(name = "categoria") String categoria) {
        try {
            List<Proyecto> proyectos = proyectoService.obtenerProyectosPorCategoria(categoria);

            if (proyectos.isEmpty()) {
                // No hay proyectos para la categoría especificada
                return new ResponseEntity<>("No hay proyectos para la categoría: " + categoria, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(proyectos, HttpStatus.OK);
        } catch (Exception e) {
            // Log de la excepción
            e.printStackTrace();
            return new ResponseEntity<>("Error al obtener proyectos por categoría", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/catalogo")
    public ResponseEntity<?> obtenerProyectosResumidos() {
        try {
            List<IdproyectoDTO> proyectos = proyectoService.getAllProyectos();

            if (proyectos.isEmpty()) {
                // No hay proyectos en el catálogo
                return new ResponseEntity<>("No hay proyectos en el catálogo", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(proyectos, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Log de la excepción
            e.printStackTrace();
            return new ResponseEntity<>("Error al obtener proyectos resumidos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/catalogo/detallado/{idProyecto}")
    public ResponseEntity<Proyecto> obtenerDetallesProyecto(@PathVariable int idProyecto) {
        try {
            Proyecto proyectoDetallado = proyectoService.obtenerProyectoPorId(idProyecto);

            if (proyectoDetallado != null) {
                return new ResponseEntity<>(proyectoDetallado, HttpStatus.OK);
            } else {
                // Si no se encuentra el proyecto, puedes devolver un código 404 (Not Found)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public Mensajedto borrarProyecto(@PathVariable int id) {
        return proyectoService.borrarproyecto(id);
    }
}

