package es.unican.is2.practica5;

@SuppressWarnings("serial")
public class saldoInsuficienteException extends RuntimeException {

	public saldoInsuficienteException (String mensaje) { // WMC + 1
		super(mensaje);
	}
}
