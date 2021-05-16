package es.unican.is2.practica5;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {
	
	// WMC = 12
	// CCog = 5

	private List<Movimiento> mMovimientos;
	public CuentaAhorro(String numCuenta) { // WMC + 1
		super(numCuenta);
		mMovimientos = new LinkedList<Movimiento>();
	}

	public void ingresar(double x) throws datoErroneoException { // WMC + 1
		ingresar("Ingreso en efectivo", x);
	}
	
	public void ingresar(String concepto, double x) throws datoErroneoException { // WMC + 1
		
		if (x <= 0) throw new datoErroneoException("No se puede ingresar una cantidad negativa"); // WMC + 1 CCog + 1

		this.mMovimientos.add(new Movimiento(concepto, LocalDateTime.now(), x));
	}

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // WMC + 1
		retirar("Retirada de efectivo", x);
	}

	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException { // WMC + 1
		 
		if (getSaldo() < x) throw new saldoInsuficienteException("Saldo insuficiente"); // WMC + 1 CCog + 1
		if (x <= 0) throw new datoErroneoException("No se puede retirar una cantidad negativa"); // WMC + 1 CCog + 1
		
		this.mMovimientos.add(new Movimiento(concepto, LocalDateTime.now(), -x));
	}

	public double getSaldo() { // WMC + 1
		
		double r = 0.0;
		
		for (Movimiento m: mMovimientos) { // WMC + 1 CCog + 1
			r += m.getI(); 
		}
		
		return r;
	}

	public void addMovimiento(Movimiento m) { // WMC + 1
		mMovimientos.add(m);
	}

	public List<Movimiento> getMovimientos() { // WMC + 1
		return mMovimientos;
	}
}