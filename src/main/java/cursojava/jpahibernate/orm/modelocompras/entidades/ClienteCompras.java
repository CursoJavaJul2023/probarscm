package cursojava.jpahibernate.orm.modelocompras.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cursojava.jpahibernate.orm.modelocompras.conversores.ConversorDeSubcripcion;
import cursojava.jpahibernate.orm.modelocompras.enums.MedioDePago;
import cursojava.jpahibernate.orm.modelocompras.enums.Subscripcion;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = "DEMOS", name = "CLIENTES")
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
			name = "ClienteCompras.porCorreo",
			query = "select c from ClienteCompras c where c.email = :correo"
		),
		@NamedQuery(
			name = "ClienteCompras.porUsuarioyClave",
			query = "select c from ClienteCompras c where c.usuario = :usuario and c.clave = :clave"
		),
		@NamedQuery(
			name = "ClienteCompras.porCompraEnRangeDeFechas",
			query = "select c from ClienteCompras c join c.compras cta where cta.fecha between :inicio and :fin"
		),
		@NamedQuery(
			name = "ClienteCompras.porArticuloComprado",
			query = "select c from ClienteCompras c join c.compras cta join cta.detalle det " +
					"where det.articulo.codigo = :codigoArticulo"
		)				
				
	}
)
public class ClienteCompras {

	@Id
	@EqualsAndHashCode.Include
	@NonNull
	private String nif;

	@NonNull
	private String nombre;

	@NonNull
	private String apellidos;

	@Embedded
	private Domicilio domicilio;

	@NonNull
	private String email;
	
	@NonNull
	private String usuario;
	
	@NonNull
	private String clave;

	private LocalDate fechaNacimiento;
	
	private BigDecimal descuento;
	
	@Enumerated(EnumType.STRING)
	private MedioDePago medioDePago;
	
	private String comentarios;
	
	@Convert(converter = ConversorDeSubcripcion.class)
	private Subscripcion subscripcion;

	@Column(insertable = false, updatable = false)
	private Integer numero;
	
//	@Column(name = "FECHAALTA", insertable = false, updatable = false)
	@Column(insertable = false, updatable = false)
	private LocalDateTime fechaAlta;
	
	@OneToMany(mappedBy = "cliente")
	@ToString.Exclude
	private Set<Compra> compras;
		
}





