package es.unican.is2.practica5;

@SuppressWarnings("serial")
public class datoErroneoException extends RuntimeException {
	
	// WMC = 1
	// CCog = 0
	
	public datoErroneoException (String mensaje) { // WMC + 1
		super(mensaje);
	}

}
