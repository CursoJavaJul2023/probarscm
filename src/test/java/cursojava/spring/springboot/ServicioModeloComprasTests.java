package cursojava.spring.springboot;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cursojava.jpahibernate.orm.modelocompras.entidades.Compra;
import cursojava.jpahibernate.orm.modelocompras.entidades.DetalleCompra;
import cursojava.jpahibernate.orm.modelocompras.repositorios.RepositorioCompra;
import cursojava.jpahibernate.orm.modelocompras.servicios.CodigoError;
import cursojava.jpahibernate.orm.modelocompras.servicios.NegocioException;
import cursojava.jpahibernate.orm.modelocompras.servicios.ServiciosModeloCompras;

@SpringBootTest(
	webEnvironment = WebEnvironment.NONE
)
@Transactional(propagation = Propagation.SUPPORTS)
@Rollback
class ServicioModeloComprasTests {
	
	@Autowired
	private ServiciosModeloCompras servicioCompras;
	
	@Autowired
	private RepositorioCompra repoCompra;
	
	private String codigoCompra;
	
	@Test
	public void probarCompraConTodasCondicionesOk() {
		
		String nifCliente = "00000002B";
		String[] codigosArticulos = { "ART000010","ART000011",  "ART000012" };
		Integer[] cantidadesArticulos = { 2, 2, 2 };
		
		assertDoesNotThrow(
			() -> {				
				codigoCompra = servicioCompras.efectuarCompra(nifCliente, codigosArticulos, cantidadesArticulos);				
			},
			"Hay error al realizar compra"
		);
		
		assertNotNull(codigoCompra, "Código de compra nulo");
		
		Optional<Compra> optCompra = repoCompra.buscarPorCodigoCargandoDetalle(codigoCompra);
		
		assertTrue(optCompra.isPresent(), "No hay compra en la base de datos");
		
		Compra compra = optCompra.get();
		
		Set<DetalleCompra> detalle = compra.getDetalle();
				
		for (String codigoArticulo : codigosArticulos) {
			boolean estaArticuloEnDetalle =
				detalle.stream().anyMatch( det -> det.getArticulo().getCodigo().equals(codigoArticulo));
			assertTrue(estaArticuloEnDetalle, "Artículo comprado no está en detalle");
		}
		
		BigDecimal totalCompraCalculado = BigDecimal.ZERO;
		
		for(DetalleCompra det : detalle) {
			totalCompraCalculado = totalCompraCalculado.add(
				det.getArticulo().getPrecioUnidad().multiply(BigDecimal.valueOf(det.getCantidad()))
			);
		}
		
		assertEquals(totalCompraCalculado, compra.getTotal(), "El total de compra es incorrecto");
		
	}
	
	@Test
	public void probarCompraConClienteNoExistente() {
		
		String nifCliente = "NINGUNO";
		String[] codigosArticulos = { "ART000010","ART000011",  "ART000012" };
		Integer[] cantidadesArticulos = { 2, 2, 2 };
		
		NegocioException ex = assertThrows(
			NegocioException.class,
			() -> {				
				codigoCompra = servicioCompras.efectuarCompra(nifCliente, codigosArticulos, cantidadesArticulos);				
			},
			"Se pudo realizar compra"
		);
		
		assertEquals(CodigoError.COMPRAS_ERROR_EN_COMPRA, ex.getCodigo(), "Código de incorrecto");
		
		try {
			NegocioException exCausa = (NegocioException) ex.getCause();
			
			assertEquals(CodigoError.COMPRAS_CLIENTE_NO_EXISTE, exCausa.getCodigo(), "Código de incorrecto");
			
		} catch (Exception e) {
			fail("La causa del problema no es NegocioExcepcion");
		}
	}
	


}
