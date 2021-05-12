package es.unican.is2.practica5;

import java.time.LocalDateTime;

public class Movimiento {
	
	// WMC = 6
	// CCog = 0
	
	private String mConcepto;
	private LocalDateTime mFecha;
	private double mImporte;
	
	public Movimiento() {
		
	}

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

	public void setC(String newMConcepto) { // WMC + 1
		mConcepto = newMConcepto;
	}

	public LocalDateTime getF() { // WMC + 1
		return mFecha;
	}

	public void setF(LocalDateTime newMFecha) { // WMC + 1
		mFecha = newMFecha;
	}

	public void setI(double newMImporte) { // WMC + 1
		mImporte = newMImporte;
	}
}