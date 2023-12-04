package pe.edu.uni.crowfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.crowfunding.model.Donaciones;
import pe.edu.uni.crowfunding.service.DonacionesService;

@RestController
@RequestMapping("/donaciones")
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
