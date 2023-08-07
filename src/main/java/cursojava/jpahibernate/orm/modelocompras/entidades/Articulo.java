package cursojava.jpahibernate.orm.modelocompras.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(schema = "DEMOS", name = "ARTICULOS")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Articulo {

	@Id
	@EqualsAndHashCode.Include
	@NonNull
	private String codigo;
	
	@NonNull
	private String nombre;
	
	private String descripcion;

	@NonNull
	private BigDecimal precioUnidad;
	
	private Float pesoGramos;
	
	private Float altoMm;
	
	private Float anchoMm;
	
	private Float largoMm;
	
	private Integer cantidad;
	
	@Column(insertable = false, updatable = false)
	private Integer numero;
	
	@Column(insertable = false, updatable = false)
	private LocalDateTime fechaAlta;
	
	@OneToMany(mappedBy = "articulo")
	@ToString.Exclude
	private Set<DetalleCompra> detalle;

}
