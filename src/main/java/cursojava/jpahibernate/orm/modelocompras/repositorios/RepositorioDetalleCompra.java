package cursojava.jpahibernate.orm.modelocompras.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import cursojava.jpahibernate.orm.modelocompras.entidades.DetalleCompra;
import cursojava.jpahibernate.orm.modelocompras.entidades.DetalleCompraPk;

public interface RepositorioDetalleCompra extends JpaRepository<DetalleCompra, DetalleCompraPk> {

}
