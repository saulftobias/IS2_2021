package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {
	
	// WMC = 
	// CCog = 

	private static final double LIMITE_DEBITO = 1000;

	private List<Movimiento> mMovimientos;
	private LocalDate mFechaDeCaducidadTarjetaDebito;
	private LocalDate mFechaDeCaducidadTarjetaCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) {
		super(numCuenta);
		this.mFechaDeCaducidadTarjetaDebito = date;
		this.mFechaDeCaducidadTarjetaCredito = date2;
		mMovimientos = new LinkedList<Movimiento>();
		limiteDebito = LIMITE_DEBITO;
	}

	public void ingresar(double x) throws datoErroneoException {
		ingresar("Ingreso en efectivo", x);
	}
	
	public void ingresar(String concepto, double x) throws datoErroneoException {
		
		if (x <= 0) throw new datoErroneoException("No se puede ingresar una cantidad negativa");

		this.mMovimientos.add(new Movimiento(concepto, LocalDateTime.now(), x));
	}

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		retirar("Retirada de efectivo", x);
	}

	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException {
		
		if (getSaldo() < x) throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0) throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		this.mMovimientos.add(new Movimiento(concepto, LocalDateTime.now(), -x));
	}

	public double getSaldo() {
		
		double r = 0.0;
		
		for (Movimiento m: mMovimientos) {
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