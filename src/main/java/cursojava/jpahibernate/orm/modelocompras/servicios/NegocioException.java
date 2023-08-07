package cursojava.jpahibernate.orm.modelocompras.servicios;


public class NegocioException extends Exception {
	
	private CodigoError codigo = CodigoError.NINGUNO;

	public NegocioException() {
	}

	public NegocioException(String message, Throwable cause) {
		super(message, cause);
	}

	public NegocioException(String message) {
		super(message);
	}

	public NegocioException(CodigoError codigo, String message, Throwable cause) {
		super(message, cause);
		this.codigo = codigo;
	}

	public NegocioException(CodigoError codigo, String message) {
		super(message);
		this.codigo = codigo;
	}

	public CodigoError getCodigo() {
		return codigo;
	}	

}
