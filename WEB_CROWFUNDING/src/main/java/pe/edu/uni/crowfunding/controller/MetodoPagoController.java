package pe.edu.uni.crowfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.MetodoPago;
import pe.edu.uni.crowfunding.service.MetodoPagoService;

@RestController
@RequestMapping("/metodo-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarMetodo(@RequestBody MetodoPago dto) {
        try {
            metodoPagoService.agregarMetodoPago(dto);
            return ResponseEntity.ok("Creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar el método de pago: " + e.getMessage());
        }
    }


}
