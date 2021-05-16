package es.unican.is2.practica5;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	// WMC = 5
	// CCog = 1
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) { // WMC + 1
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String zip, String localidad) { // WMC + 1
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	public double getSaldoTotal() { // WMC + 1
		
		double total = 0.0;
		
		for (Cuenta c: Cuentas) { // WMC + 1 CCog + 1
			total += c.getSaldo();
		}
		
		return total;
	}
	
	public void anhadeCuenta(Cuenta c) { // WMC + 1
		Cuentas.add(c);
	}
}