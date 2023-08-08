package cursojava.spring.springboot.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cursojava.jpahibernate.orm.modelocompras.servicios.NegocioException;
import cursojava.jpahibernate.orm.modelocompras.servicios.ServiciosModeloCompras;
import cursojava.spring.springboot.dtos.DatosCompra;

@RestController
public class ModeloComprasControlador {
	
	@Autowired
	private ServiciosModeloCompras srvCompras;
	
//	@RequestMapping(
//		method = RequestMethod.POST
//	)
	@PostMapping(
		path = "/comprar",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> realizarCompra(@RequestBody DatosCompra datos) {
		
		try {
			
			srvCompras.efectuarCompra(datos.getNif(), datos.getCodigos(), datos.getCantidades());
			
			return ResponseEntity.ok().build();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity.internalServerError().build();
		}
	}

}
