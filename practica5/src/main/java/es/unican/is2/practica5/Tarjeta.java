package es.unican.is2.practica5;

public abstract class Tarjeta {
	protected String mNumero, mTitular;		
	protected CuentaAhorro mCuentaAsociada;

	public Tarjeta(String numero, String titular, CuentaAhorro c) { // WMC + 1
		mNumero = numero;
		mTitular = titular;
		mCuentaAsociada = c;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException; // WMC + 1

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double x) // WMC + 1
			throws saldoInsuficienteException, datoErroneoException;
	
}