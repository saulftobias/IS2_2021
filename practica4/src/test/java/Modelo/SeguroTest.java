package Modelo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class SeguroTest {
	
	private static final int SEGUNDOS_EN_ANHO = 31536000;
	
	// Atributos de la clase
	private Seguro seguro;
	private Cliente cliente;
	
	@Before
	public void setUp() throws Exception {
		cliente = new Cliente("Enrique", "Vallejo", false);
	}

	@Test
	public void testConstructor() {
		
		// Casos de Prueba Validos
		try {
			seguro = new Seguro(1, cliente, Cobertura.TERCEROS);
			assertTrue(seguro.getPotencia() == 1);
			assertTrue(seguro.getCliente().equals(cliente));
			assertTrue(seguro.getCobertura().equals(Cobertura.TERCEROS));
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		
		
		try {
			seguro = new Seguro(100, cliente, Cobertura.TODORIESGO);
			assertTrue(seguro.getPotencia() == 100);
			assertTrue(seguro.getCliente().equals(cliente));
			assertTrue(seguro.getCobertura().equals(Cobertura.TODORIESGO));
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		
		try {
			seguro = new Seguro(10000, cliente, Cobertura.TERCEROSLUNAS);
			assertTrue(seguro.getPotencia() == 10000);
			assertTrue(seguro.getCliente().equals(cliente));
			assertTrue(seguro.getCobertura().equals(Cobertura.TERCEROSLUNAS));
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		
		// Casos de Prueba NO Validos
		try {
			seguro = new Seguro(-1, cliente, Cobertura.TERCEROS);
			fail("Deberia lanzarse la excepcion: Potencia no valida");
		} catch (DatoIncorrectoException e) {
			// Debe lanzarse la excepcion
		}
		
		try {
			seguro = new Seguro(100, null, Cobertura.TODORIESGO);
			fail("Deberia lanzarse la excepcion: Cliente no valido");
		} catch (DatoIncorrectoException e) {
			// Debe lanzarse la excepcion
		}
		
		try {
			fail("Deberia lanzarse la excepcion: Cobertura no valida");
			seguro = new Seguro(10000, cliente, null);
		} catch (DatoIncorrectoException e) {
			// Debe lanzarse la excepcion
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPrecio() {
		
		// Casos de Prueba Validos
		cliente = new Cliente("Alvaro", "Lopez", true);
		try {
			seguro = new Seguro(1, cliente, Cobertura.TERCEROS);
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		seguro.setFechaUltimoSiniestro(null);
	}
}
