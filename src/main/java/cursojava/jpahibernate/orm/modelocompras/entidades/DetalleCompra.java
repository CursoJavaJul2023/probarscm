package cursojava.jpahibernate.orm.modelocompras.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = "DEMOS", name = "DETALLECOMPRAS")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@IdClass(DetalleCompraPk.class)
public class DetalleCompra {

	@Id
	@NonNull
	@ManyToOne
	@JoinColumn(name = "COMPRA", referencedColumnName = "CODIGO")
	private Compra compra;
	
	@Id
	@NonNull
	@ManyToOne
	@JoinColumn(name = "ARTICULO", referencedColumnName = "CODIGO")
	private Articulo articulo;
	
	@NonNull
	private Integer cantidad;
		
	@Column(insertable = false, updatable = false)
	private Integer numero;
	
}
