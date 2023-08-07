package cursojava.jpahibernate.orm.modelocompras.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import cursojava.jpahibernate.orm.modelocompras.entidades.Compra;

public interface RepositorioCompra extends JpaRepository<Compra, String> {

}
