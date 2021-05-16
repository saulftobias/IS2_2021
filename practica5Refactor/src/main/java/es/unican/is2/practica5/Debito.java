package es.unican.is2.practica5;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	// WMC = 7
	// CCog = 2
	
	private double saldoDiarioDisponible;
	private static final double LIMITE_DEBITO = 1000;

	public Debito(String numero, String titular, CuentaAhorro c, LocalDate fecha) { // WMC + 1
		super(numero, titular, c, fecha);
	}
	
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // WMC + 1
		if (saldoDiarioDisponible<x) { // WMC + 1 CCog + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Retirada en cajero autom�tico", x);
		saldoDiarioDisponible-=x;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // WMC + 1
		if (saldoDiarioDisponible<x) { // WMC + 1 CCog + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	public LocalDate getCaducidadDebito() { // WMC + 1
		return fechaCaducidad;
	}
	
	/**
	 * M�todo invocado autom�ticamente a las 00:00 de cada d�a
	 */
	public void restableceSaldo() { // WMC + 1
		saldoDiarioDisponible = LIMITE_DEBITO;
	}
	
	public CuentaAhorro getCuentaAsociada() { // WMC + 1
		return mCuentaAsociada;
	}
	
	public double getLimiteDebito() { // WMC + 1
		return LIMITE_DEBITO;
	}
}