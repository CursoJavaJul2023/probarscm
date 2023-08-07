package cursojava.jpahibernate.orm.modelocompras.enums;

public enum Subscripcion {
	
	ACTIVA('S', 100),
	NO_ACTIVA('N', 200);
	
	private char codigo;
	private int numero;
	
	private Subscripcion(char codigo, int numero) {
		this.codigo = codigo;
		this.numero = numero;
	}

	public char getCodigo() {
		return codigo;
	}

	public int getNumero() {
		return numero;
	}

}
