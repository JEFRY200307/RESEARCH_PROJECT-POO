package pe.edu.uni.pag_inicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.uni.pag_inicio.controller.dto.ProyectoDTO;
import pe.edu.uni.pag_inicio.service.ProyectoService;

import java.util.List;


@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
    
    @Autowired
    private ProyectoService proyectoService;

    @GetMapping("/categoria")
    public ResponseEntity<List<ProyectoDTO>> obtenerProyectosPorCategoria(@RequestParam(name = "categoria") String categoria) {
        try {
            List<ProyectoDTO> proyectos = proyectoService.obtenerProyectosPorCategoria(categoria);
            return new ResponseEntity<>(proyectos, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Manejo de errores, puedes personalizar según tus necesidades
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

