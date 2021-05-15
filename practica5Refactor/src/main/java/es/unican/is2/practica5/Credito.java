package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Credito extends Tarjeta {
	
	// WMC = 
	// CCog = 
	
	private double mCredito;
	private List<Movimiento> mMovimientosMensuales;
	private List<Movimiento> mhistoricoMovimientos;
	
	
	public Credito(String numero, String titular, CuentaAhorro c, double credito) { // WMC 
		super(numero, titular, c);
		mCredito = credito;
		mMovimientosMensuales = new LinkedList<Movimiento>();
		mhistoricoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // WMC 
		if (x<0) // WMC + 1 CCog + 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		LocalDateTime now = LocalDateTime.now();
		x += x * 0.05; // A�adimos una comisi�n de un 5%
		Movimiento m = new Movimiento("Retirada en cajero autom�tico", now, -x);
		
		if (getGastosAcumulados()+x > mCredito)
			throw new saldoInsuficienteException("Cr�dito insuficiente");
		else {
			mMovimientosMensuales.add(m);
		}
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // WMC 
		if (x<0) // WMC CCog 
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > mCredito) // WMC  CCog 
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		
		LocalDateTime now = LocalDateTime.now();
		Movimiento m = new Movimiento("Compra a cr�dito en: " + datos, now, -x);
		mMovimientosMensuales.add(m);
	}
	
    public double getGastosAcumulados() { // WMC 
		return - getImporteAcumulado();
	}
	
	
	public LocalDate getCaducidadCredito() { // WMC
		return this.mCuentaAsociada.getCaducidadCredito();
	}

	/**
	 * M�todo que se invoca autom�ticamente el d�a 1 de cada mes
	 */
	public void liquidar() { // WMC
		LocalDateTime now = LocalDateTime.now();
		double r = getImporteAcumulado();
		Movimiento liq = new Movimiento("Liquidaci�n de operaciones tarjeta cr�dito", now, r);
	
		if (r != 0) // WMC1 CCog
			mCuentaAsociada.addMovimiento(liq);
		
		mhistoricoMovimientos.addAll(mMovimientosMensuales);
		mMovimientosMensuales.clear();
	}

	private double getImporteAcumulado() {
		double r = 0.0;
		for (Movimiento m : mMovimientosMensuales) { // WMC1 CCog
			r += m.getI();
		}
		return r;
	}

	public List<Movimiento> getMovimientosUltimoMes() { // WMC CCog
		return mMovimientosMensuales;
	}
	
	public Cuenta getCuentaAsociada() { // WMC CCog
		return mCuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() { // WMC CCog
		return mhistoricoMovimientos;
	}

}