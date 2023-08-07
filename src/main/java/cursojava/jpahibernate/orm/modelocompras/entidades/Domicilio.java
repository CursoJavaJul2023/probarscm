package cursojava.jpahibernate.orm.modelocompras.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Domicilio {
	
	private String domicilio;
	@Column(name = "CP")
	private Integer codigoPostal;
	private String localidad;
	private String provincia;

}
