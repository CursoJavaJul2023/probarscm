package cursojava.jpahibernate.orm.modelocompras.servicios.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cursojava.jpahibernate.orm.modelocompras.entidades.Articulo;
import cursojava.jpahibernate.orm.modelocompras.entidades.ClienteCompras;
import cursojava.jpahibernate.orm.modelocompras.entidades.Compra;
import cursojava.jpahibernate.orm.modelocompras.entidades.DetalleCompra;
import cursojava.jpahibernate.orm.modelocompras.repositorios.RepositorioArticulo;
import cursojava.jpahibernate.orm.modelocompras.repositorios.RepositorioClientesCompras;
import cursojava.jpahibernate.orm.modelocompras.repositorios.RepositorioCompra;
import cursojava.jpahibernate.orm.modelocompras.repositorios.RepositorioDetalleCompra;
import cursojava.jpahibernate.orm.modelocompras.servicios.CodigoError;
import cursojava.jpahibernate.orm.modelocompras.servicios.NegocioException;
import cursojava.jpahibernate.orm.modelocompras.servicios.ServiciosModeloCompras;

@Service
@Transactional(rollbackFor = NegocioException.class)
public class ServiciosModeloComprasBean implements ServiciosModeloCompras {
	
	@Autowired
	private RepositorioClientesCompras repoCliente;
	
	@Autowired
	private RepositorioCompra repoCompra;
	
	@Autowired
	private RepositorioDetalleCompra repoDetalle;
	
	@Autowired
	private RepositorioArticulo repoArticulo;
	
	
	/**
	 *
	 * Realiza la compra de los articulos con las cantidades indicados
	 * Actualiza la cantidad de artículos disponibles
	 * 
	 * 
	 * @param nifCliente
	 * @param codigosArticulos
	 * @param cantidadesArticulos
	 * @return El código de la compra
	 * @throws NegocioException
	 * 
	 * 	Si el cliente no existe
	 *  Si algún código de artículo no existe
	 *  Si no hay cantidad suficiente de algún artículo
	 *  Opcional
	 *  Si se han efectuado más de cuatro compras por parte del cliente
	 */
	@Override
	public String efectuarCompra(
			String nifCliente, 
			String[] codigosArticulos, 
			Integer[] cantidadesArticulos) throws NegocioException {
		
		try {
			Optional<ClienteCompras> opCliente = repoCliente.findById(nifCliente);
			if(opCliente.isEmpty()) {
				throw new NegocioException(CodigoError.COMPRAS_CLIENTE_NO_EXISTE, "Cliente no existe con nif " + nifCliente);
			}
			
			ArrayList<String> listaCodigos = new ArrayList<String>();
			for(String codigo : codigosArticulos) {
				listaCodigos.add(codigo);
			}
			
			List<Articulo> articulos = repoArticulo.findAllById(listaCodigos);
			if(articulos.size() != codigosArticulos.length) {
				throw new NegocioException(CodigoError.COMPRAS_ARTICULO_NO_EXISTE, "Se ha intentado comprar artículo no existente");
			}

			Map<String, Integer> codArticuloCantidad = new HashMap<String, Integer>();
			
			for(int i = 0; i < codigosArticulos.length; i++) {
				String codigoArticulo = codigosArticulos[i];
				Integer cantidadArticulo = cantidadesArticulos[i];
				
				codArticuloCantidad.put(codigoArticulo, cantidadArticulo);
				
				if(articulos.stream().filter(
						a -> a.getCodigo().equals(codigoArticulo) && a.getCantidad() >= cantidadArticulo 
				).count() < 1) {
					throw new NegocioException(CodigoError.COMPRAS_ARTICULO_CANTIDAD_INSUFICIENTE,
							"Artículo " + codigoArticulo + " no tiene cantidad pedida " + cantidadArticulo);
				}
			}
			
			ClienteCompras cliente = opCliente.get();
			
			Compra compra = new Compra(
				String.format("%s%6d", cliente.getNif(), System.currentTimeMillis() % 1_000_000),
				cliente,
				LocalDate.now()
			);
			
			repoCompra.save(compra);
			
			BigDecimal totalCompra = BigDecimal.ZERO;
			
			for(Articulo articulo : articulos) {
				
				Integer cantidad = codArticuloCantidad.get(articulo.getCodigo());
				
				DetalleCompra detalle = new DetalleCompra(compra, articulo, cantidad);
				
				repoDetalle.save(detalle);
				
				articulo.setCantidad(articulo.getCantidad() - cantidad);
				
				totalCompra = totalCompra.add(articulo.getPrecioUnidad().multiply(BigDecimal.valueOf(cantidad)));
			}
			
			compra.setTotal(totalCompra);		
			
			repoCompra.save(compra);
			
			return compra.getCodigo();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new NegocioException(CodigoError.COMPRAS_ERROR_EN_COMPRA, "No pudo realizar la compra", e);
		}
		
	}

}
