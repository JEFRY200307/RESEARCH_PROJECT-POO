package pe.edu.uni.pag_inicio.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.pag_inicio.service.ProyectoTecnologia;


@RestController
@RequestMapping("proyecto/tecnologia")
public class TecnologiaController {
	@Autowired
	private ProyectoTecnologia tecnoService;

	@GetMapping
	public List<Map<String, Object>> getAll(){
		return tecnoService.getAll();
	}
	
	
}


