package pe.edu.uni.crowfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.crowfunding.model.MetodoPago;
import pe.edu.uni.crowfunding.service.MetodoPagoService;

@RestController
@RequestMapping("/metodo-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService service;

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarMetodo(
            @RequestBody MetodoPago dto
            ) {
        service.agregarMetodoPago(dto);
        return ResponseEntity.ok("Creado exitosamente");
    }


}
