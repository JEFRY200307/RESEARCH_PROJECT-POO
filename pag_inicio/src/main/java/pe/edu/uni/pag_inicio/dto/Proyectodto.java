package pe.edu.uni.pag_inicio.dto;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Proyectodto {
	
	private int id_proyecto;
	private String titulo;
	private String descripcion;
	private String objetivos;
	private float recaudacion;
	private Date fecha_inicio;
	private Date fecha_fin;
	private Boolean estado;
	private float monto_objetivo;
	private String image_url;
	private int id_usuario;
	private int id_creador;
	private String categoria;
}
