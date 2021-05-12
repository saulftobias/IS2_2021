package es.unican.is2.practica5;

@SuppressWarnings("serial")
public class saldoInsuficienteException extends RuntimeException {
	
	// WMC = 1
	// CCog = 0

	public saldoInsuficienteException (String mensaje) { // WMC + 1
		super(mensaje);
	}
}
