package Modelo;

import java.util.Date;

public class Seguro {
	
	// Atributos de la clase
	private int potenciaCV;
	Cliente cliente;
	Cobertura cobertura;
	Date fechaUltimoSiniestro;
	
	public Date getFechaUltimoSiniestro() {
		return fechaUltimoSiniestro;
	}

	public void setFechaUltimoSiniestro(Date fechaUltimoSiniestro) {
		this.fechaUltimoSiniestro = fechaUltimoSiniestro;
	}

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
}
