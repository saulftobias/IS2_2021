package es.unican.is2.practica5;

import java.util.List;

public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	public CuentaValores(String numCuenta, List<Valor> valores) { // WMC + 1
		super(numCuenta);
		this.valores = valores;
	}
	
	public List<Valor> getValores() { // WMC + 1
		return valores;
	}
	
	public void anhadeValor(Valor v) { // WMC + 1
		valores.add(v);
	}
	
	
}
