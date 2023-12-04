package pe.edu.uni.crowfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.crowfunding.model.Donaciones;
import pe.edu.uni.crowfunding.service.DonacionesService;

@RestController
@RequestMapping("/UniOpportunity/login/proyectos/catalogo/detallado")
@CrossOrigin(origins = "http://localhost:3000")
public class DonacionesController {

    @Autowired
    private final DonacionesService service = null;


    @PostMapping("/donar")
    public ResponseEntity<String> donar(
            @RequestBody Donaciones dto
            ) {

        service.donar(dto);
        return ResponseEntity.ok("");
    }
}
