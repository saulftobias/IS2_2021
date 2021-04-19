package Modelo;

public class Seguro {
	
	// Atributos de la clase
	private int potencia;
	Cliente cliente;
	Cobertura cobertura;
	
	
	public Seguro(int potencia, Cliente cliente, Cobertura cobertura) {
		this.potencia = potencia;
		this.cliente = cliente;
		this.cobertura = cobertura;
	}

	public double precio() {
		// TODO Auto-generated method stub
		return 0;
	}

}
