package cursojava.jpahibernate.orm.modelocompras.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cursojava.jpahibernate.orm.modelocompras.entidades.Compra;

@Transactional
public interface RepositorioCompra extends JpaRepository<Compra, String> {
	
	@Query("select cpa from Compra cpa join fetch cpa.detalle dte join fetch dte.articulo join fetch cpa.cliente where cpa.codigo = ?1")
	Optional<Compra> buscarPorCodigoCargandoDetalle(String codigo);

}
