package cursojava.jpahibernate.orm.modelocompras.entidades;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCompraPk implements Serializable {
	
	@NonNull
	private String compra;
	
	@NonNull
	private String articulo;

}
