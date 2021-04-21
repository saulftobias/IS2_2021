package Modelo;

import java.time.LocalDate;
import java.util.Date;

public class Seguro {
	
	// Atributos de la clase
	private int potenciaCV;
	Cliente cliente;
	Cobertura cobertura;
	LocalDate fechaUltimoSiniestro;
	
	public Seguro(int potencia, Cliente cliente, Cobertura cobertura) throws DatoIncorrectoException {
		this.potenciaCV = potencia;
		this.cliente = cliente;
		this.cobertura = cobertura;
	}

	public double precio() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPotencia() {
		return potenciaCV;
	}

	public void setPotencia(int potencia) {
		this.potenciaCV = potencia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cobertura getCobertura() {
		return cobertura;
	}

	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}
	
	public LocalDate getFechaUltimoSiniestro() {
		return fechaUltimoSiniestro;
	}

	public void setFechaUltimoSiniestro(LocalDate fechaUltimoSiniestro) {
		this.fechaUltimoSiniestro = fechaUltimoSiniestro;
	}

}
