package es.unican.is2.practica5;

import java.time.LocalDateTime;

public class Movimiento {
	
	// WMC = 6
	// CCog = 0
	
	private String mConcepto;
	private LocalDateTime mFecha;
	private double mImporte;
	
	public Movimiento() {}

	public Movimiento(String mConcepto, LocalDateTime mFecha, double mImporte) {
		this.mConcepto = mConcepto;
		this.mFecha = mFecha;
		this.mImporte = mImporte;
	}

	public double getI() { // WMC + 1
		return mImporte;
	}

	public String getC() { // WMC + 1
		return mConcepto;
	}
	
	public LocalDateTime getF() { // WMC + 1
		return mFecha;
	}
}