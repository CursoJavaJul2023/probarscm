package cursojava.jpahibernate.orm.modelocompras.repositorios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cursojava.jpahibernate.orm.modelocompras.entidades.ClienteCompras;

// Interfaz base de todos los repositorios de Spring Data
// public interface RepositorioClientesCompras extends Repository<ClienteCompras, String> {
	
// Interfaz para incorporar métodos CRUD
// public interface RepositorioClientesCompras extends CrudRepository<ClienteCompras, String> {

// Interfaz para incorporar soporte de ordenación y paginación
// public interface RepositorioClientesCompras extends PagingAndSortingRepository<ClienteCompras, String> {
	
// Interfaz para emplear SOLO desde JPA
public interface RepositorioClientesCompras extends JpaRepository<ClienteCompras, String> {

	// Podemos definir métodos para llevar a cabo consultas
	
	// 1. Mediante métodos que emplean nomenclatura documentada en Spring Data
	
	Optional<ClienteCompras> findByNif(String nif);
	
	List<ClienteCompras> findByNombreLikeAndApellidosLikeOrderByNifDesc(String nombre, String apellidos);
		
	List<ClienteCompras> findByFechaNacimiento(LocalDate fecha);
	
	// 2. Mediante consultas JPAQL
	
	@Query("select cte from ClienteCompras cte where cte.fechaNacimiento = ?1")
	List<ClienteCompras> buscarClientesPorFechaNacimiento(LocalDate fecha);
	
	@Query("select cte from ClienteCompras cte where cte.fechaNacimiento between :inicio and :fin")	
	List<ClienteCompras> buscarClientesPorFechaNacimientoEnIntervalo(@Param("inicio") LocalDate fechaInicio, @Param("fin") LocalDate fechaFin);
	
	@Query(name = "ClienteCompras.porCompraEnRangeDeFechas")
	List<ClienteCompras> buscarPorCompraEntreFechas(@Param("inicio") LocalDate fechaInicio, @Param("fin") LocalDate fechaFin);
	
	// Se busca la consulta con el nombre "ClienteCompras.porArticuloComprado"
	List<ClienteCompras> porArticuloComprado(@Param("codigoArticulo") String codigo);
	
	// 3. Mediante consultas con SQL nativo
	
	@Query(
		nativeQuery = true,
		value = "SELECT * FROM DEMOS.CLIENTES WHERE DATE(FECHAALTA) = CURRENT_DATE"
	)
//	@Query(
//		nativeQuery = true,
//		name = "ClienteCompras.porAltaEntreFechas"
//	)
	List<ClienteCompras> porAltaEnElDiaActual();
	
}





