package cursojava.spring.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cursojava.spring.springboot.dtos.DatosCompra;

@SpringBootTest(
	webEnvironment = WebEnvironment.RANDOM_PORT
)
class ControladorModeloComprasTests {
	
	@LocalServerPort
	private int puerto;
	
	@Autowired
	private TestRestTemplate rt;
	
	private String url;
	
	@BeforeEach
	public void inicializar() {
		
		url = "http://localhost:" + puerto;
	}
	
	@Test
	public void realizarCompraConTodoOk() {
		
		url += "/comprar";
		
		DatosCompra datos = new DatosCompra();
		datos.setNif("00000008B");
		datos.setCodigos(new String[] { "ART000020", "ART000021" } );
		datos.setCantidades(new Integer[] { 2, 2 });
		
		ResponseEntity<Void> respuesta = rt.postForEntity(url, datos, Void.class);
		
		HttpStatus statusCode = respuesta.getStatusCode();
		
		assertEquals(HttpStatus.OK, statusCode, "Código de estado en la respuesta no es OK");
	}

}








