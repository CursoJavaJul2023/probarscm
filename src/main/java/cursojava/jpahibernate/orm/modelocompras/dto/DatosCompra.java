package cursojava.jpahibernate.orm.modelocompras.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosCompra {
	
	private String codigo;
	private String nif;
	private LocalDate fecha;
	private BigDecimal total;
	private Integer numero;
	private LocalDateTime fechaAlta;

}
