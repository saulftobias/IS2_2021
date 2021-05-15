package es.unican.is2.practica5;

import java.time.LocalDate;

public abstract class Tarjeta {
	
	// WMC = 1
	// CCog = 0
	
	protected String mNumero, mTitular;		
	protected CuentaAhorro mCuentaAsociada;
	protected LocalDate fechaCaducidad;

	public Tarjeta(String numero, String titular, CuentaAhorro c, LocalDate fecha) { // WMC + 1
		mNumero = numero;
		mTitular = titular;
		mCuentaAsociada = c;
		fechaCaducidad = fecha;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException; // WMC + 0 (No se cuenta el metodo abstracto)

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double x) // WMC + 0 (No se cuenta el metodo abstracto)
			throws saldoInsuficienteException, datoErroneoException;
	
}