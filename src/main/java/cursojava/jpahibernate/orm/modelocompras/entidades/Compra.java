package cursojava.jpahibernate.orm.modelocompras.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = "DEMOS", name = "COMPRAS")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(
	{
		@NamedQuery(
			name = "Compra.porArticuloEnRangoDePrecio",
			
//			query = "select cta.codigo,cta.cliente.nif,cta.fecha,cta.total,cta.numero,cta.fechaAlta " +
//					 "from Compra cta join cta.detalle det " +
//					 "where det.articulo.precioUnidad between :precioMinimo and :precioMaximo"
					 
			query = "select cta " +
					 "from Compra cta join cta.detalle det join fetch cta.cliente " +
					 "where det.articulo.precioUnidad between :precioMinimo and :precioMaximo"
					 
		),
		@NamedQuery(
			name = "Compra.porArticuloEnRangoDePrecio2",			
//			query = "select cta.codigo as codigo ,cta.cliente.nif as nif,cta.fecha as fecha,cta.total as total ,cta.numero as numero ,cta.fechaAlta as fechaAlta " +
//					 "from Compra cta join cta.detalle det " +
//					 "where det.articulo.precioUnidad between :precioMinimo and :precioMaximo"	
					 
			query = "select new cursojava.jpahibernate.orm.modelocompras.dto.DatosCompra(cta.codigo,cta.cliente.nif,cta.fecha,cta.total,cta.numero,cta.fechaAlta) " +
					 "from Compra cta join cta.detalle det " +
					 "where det.articulo.precioUnidad between :precioMinimo and :precioMaximo"					 
					 
		)
	}
)
public class Compra {

	@Id
	@EqualsAndHashCode.Include
	@NonNull
	private String codigo;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "CLIENTE", referencedColumnName = "NIF")
	private ClienteCompras cliente;

	@NonNull
	private LocalDate fecha;
	
	private BigDecimal total;
	
	@Column(insertable = false, updatable = false)
	private Integer numero;
	
	@Column(insertable = false, updatable = false)
	private LocalDateTime fechaAlta;
	
	@OneToMany(mappedBy = "compra")
	@ToString.Exclude
	private Set<DetalleCompra> detalle;
}
