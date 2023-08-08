package cursojava.spring.springboot.dtos;

import lombok.Data;

@Data
public class DatosCompra {
	
	private String nif;
	
	private String[] codigos;
	
	private Integer[] cantidades;
	
//	private List<String> codigos;
//	
//	private List<Integer> cantidades;

}
