package Modelo;

public class Seguro {
	
	// Atributos de la clase
	private int potencia;
	Cliente cliente;
	Cobertura cobertura;
	
	
	public Seguro(int potencia, Cliente cliente, Cobertura cobertura) throws DatoIncorrectoException {
		this.potencia = potencia;
		this.cliente = cliente;
		this.cobertura = cobertura;
	}

	public double precio() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cobertura getCobertura() {
		return cobertura;
	}

	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}
}
