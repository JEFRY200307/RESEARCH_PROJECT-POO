package pe.edu.uni.crowfunding.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Mensajedto {
		
	private Integer codigo; // 1: ok -1: error
	private String mensaje; 

}


