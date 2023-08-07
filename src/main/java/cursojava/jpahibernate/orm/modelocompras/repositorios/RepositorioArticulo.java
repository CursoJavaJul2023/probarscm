package cursojava.jpahibernate.orm.modelocompras.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import cursojava.jpahibernate.orm.modelocompras.entidades.Articulo;

public interface RepositorioArticulo extends JpaRepository<Articulo, String> {

}
