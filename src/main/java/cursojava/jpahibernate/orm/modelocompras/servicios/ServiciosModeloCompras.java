package cursojava.jpahibernate.orm.modelocompras.servicios;


public interface ServiciosModeloCompras {

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
	String efectuarCompra(String nifCliente, String[] codigosArticulos, Integer[] cantidadesArticulos)
			throws NegocioException;

}
