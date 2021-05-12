package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {
	
	// WMC = 
	// CCog = 


	private List<Movimiento> mMovimientos;
	private LocalDate mFechaDeCaducidadTarjetaDebito;
	private LocalDate mFechaDeCaducidadTarjetaCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) {
		super(numCuenta);
		this.mFechaDeCaducidadTarjetaDebito = date;
		this.mFechaDeCaducidadTarjetaCredito = date2;
		mMovimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	public void ingresar(double x) throws datoErroneoException {
		if (x <= 0)
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		LocalDateTime now = LocalDateTime.now();
		Movimiento m = new Movimiento("Ingreso en efectivo", now, x);
		this.mMovimientos.add(m);
	}

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // WMC + 1
		if (x <= 0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getSaldo() < x)
			throw new saldoInsuficienteException("Saldo insuficiente");
		LocalDateTime now = LocalDateTime.now();
		Movimiento m = new Movimiento("Retirada de efectivo", now, -x);
		this.mMovimientos.add(m);
	}

	public void ingresar(String concepto, double x) throws datoErroneoException { // WMC + 1
		if (x <= 0)
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		LocalDateTime now = LocalDateTime.now();
		Movimiento m = new Movimiento(concepto, now, x);
		this.mMovimientos.add(m);
	}

	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException { // WMC + 1
		if (getSaldo() < x)
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		LocalDateTime now = LocalDateTime.now();
		Movimiento m = new Movimiento(concepto, now, -x);
		this.mMovimientos.add(m);
	}

	public double getSaldo() {
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) { // WMC + 1 CCog + 1
			Movimiento m = (Movimiento) mMovimientos.get(i);
			r += m.getI();
		}
		return r;
	}

	public void addMovimiento(Movimiento m) {
		mMovimientos.add(m);
	}

	public List<Movimiento> getMovimientos() {
		return mMovimientos;
	}

	public LocalDate getCaducidadDebito() {
		return this.mFechaDeCaducidadTarjetaDebito;
	}

	public LocalDate getCaducidadCredito() {
		return this.mFechaDeCaducidadTarjetaCredito;
	}

	public double getLimiteDebito() {
		return limiteDebito;
	}

}