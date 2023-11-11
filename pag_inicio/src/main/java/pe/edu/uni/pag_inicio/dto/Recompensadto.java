package pe.edu.uni.pag_inicio.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Recompensadto {
	
	private int id_proyecto;
	private String tipo_nivel;
	private String nombre_recompensa;
	private float valor_recompensa;
}
