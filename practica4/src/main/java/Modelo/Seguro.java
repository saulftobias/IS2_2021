package Modelo;

import java.time.LocalDate;

public class Seguro {
	
	// Atributos de la clase
	private int potenciaCV;
	Cliente cliente;
	Cobertura cobertura;
	LocalDate fechaUltimoSiniestro = null;
	
	public Seguro(int potencia, Cliente cliente, Cobertura cobertura) throws DatoIncorrectoException {
		
		if (potencia <= 0) throw new DatoIncorrectoException();
		if (cliente == null) throw new DatoIncorrectoException();
		this.potenciaCV = potencia;
		this.cliente = cliente;
		this.cobertura = cobertura;
	}

	public double precio() {
		
		double precio = 0;
		
		//calculo del precio base
		
		switch (cobertura) {
		case TODO_RIESGO: 
			precio = 1000;
			break;
		case TERCEROS:
			precio = 400;
			break;
		case TERCEROS_LUNAS:
			precio = 600;
			break;
		default:
			break;
		}
		
		//calculo extra potencia
		
		if (potenciaCV < 90) {
			precio *= 1;
		} else if (potenciaCV <= 110) {
			precio *= 1.05;
		} else {
			precio *= 1.20;
		}
		
		//calculo a�adido por siniestralidad
		
		if (fechaUltimoSiniestro == null || fechaUltimoSiniestro.isBefore(LocalDate.now().minusYears(3))) {
			precio += 0;
		} else if (fechaUltimoSiniestro.isBefore(LocalDate.now().minusYears(1))) {
			precio += 50;
		} else {
			precio += 200;
		}
		
		//calculo descuento discapacidad 
		
		if (cliente.isMinusvalia()) precio *= 0.75;
		
		return precio;
	}

	public int getPotencia() {
		return potenciaCV;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public Cobertura getCobertura() {
		return cobertura;
	}

	public void setFechaUltimoSiniestro(LocalDate fechaUltimoSiniestro) {
		this.fechaUltimoSiniestro = fechaUltimoSiniestro;
	}

}
