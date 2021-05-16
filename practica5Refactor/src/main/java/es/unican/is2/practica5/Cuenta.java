package es.unican.is2.practica5;

public abstract class Cuenta {
	
	// WMC = 2
	// CCog = 0
	
	private String numCuenta;
	
	public Cuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	
	public String getNumCuenta() {
		return numCuenta;
	}
	
	public abstract double getSaldo();
}
