package Modelo;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class SeguroTest {
	
	// Atributos de la clase
	private Seguro seguro;
	private Cliente clienteSinMinusvalia;
	private Cliente clienteConMinusvalia;
	
	@Before
	public void setUp() throws Exception {
		clienteSinMinusvalia = new Cliente("Enrique", "Vallejo", false);
		clienteConMinusvalia = new Cliente("Alvaro", "Lopez", true);
	}

	@Test
	public void testConstructor() {
		
		// Casos de Prueba Validos
		try {
			seguro = new Seguro(1, clienteSinMinusvalia, Cobertura.TERCEROS);
			assertTrue(seguro.getPotencia() == 1);
			assertTrue(seguro.getCliente().equals(clienteSinMinusvalia));
			assertTrue(seguro.getCobertura().equals(Cobertura.TERCEROS));
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		
		
		try {
			seguro = new Seguro(100, clienteSinMinusvalia, Cobertura.TODO_RIESGO);
			assertTrue(seguro.getPotencia() == 100);
			assertTrue(seguro.getCliente().equals(clienteSinMinusvalia));
			assertTrue(seguro.getCobertura().equals(Cobertura.TODO_RIESGO));
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		
		try {
			seguro = new Seguro(10000, clienteSinMinusvalia, Cobertura.TERCEROS_LUNAS);
			assertTrue(seguro.getPotencia() == 10000);
			assertTrue(seguro.getCliente().equals(clienteSinMinusvalia));
			assertTrue(seguro.getCobertura().equals(Cobertura.TERCEROS_LUNAS));
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		
		// Casos de Prueba NO Validos
		try {
			seguro = new Seguro(-1, clienteSinMinusvalia, Cobertura.TERCEROS);
			fail("Deberia lanzarse la excepcion: Potencia no valida");
		} catch (DatoIncorrectoException e) {
			// Debe lanzarse la excepcion
		}
		
		try {
			seguro = new Seguro(100, null, Cobertura.TODO_RIESGO);
			fail("Deberia lanzarse la excepcion: Cliente no valido");
		} catch (DatoIncorrectoException e) {
			// Debe lanzarse la excepcion
		}
		
	}
	
	@Test
	public void testPrecio() {
		
		// Casos de Prueba Validos
		try {
			seguro = new Seguro(1, clienteConMinusvalia, Cobertura.TERCEROS);
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(10));
		assertTrue(seguro.precio()==300);
		
		try {
			seguro = new Seguro(50, clienteSinMinusvalia, Cobertura.TODO_RIESGO);
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(5));
		assertTrue(seguro.precio()==1000);
		
		try {
			seguro = new Seguro(89, clienteConMinusvalia, Cobertura.TERCEROS_LUNAS);
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(3).minusDays(1));
		assertTrue(seguro.precio()==450);
		try {
			seguro = new Seguro(90, clienteSinMinusvalia, Cobertura.TERCEROS);
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(3));
		assertTrue(seguro.precio()==470);
		
		try {
			seguro = new Seguro(100, clienteConMinusvalia, Cobertura.TODO_RIESGO);
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(2));
		assertTrue(seguro.precio()==825);
		
		try {
			seguro = new Seguro(110, clienteSinMinusvalia, Cobertura.TERCEROS_LUNAS);
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(1).minusDays(1));
		assertTrue(seguro.precio()==680);
		
		try {
			seguro = new Seguro(111, clienteConMinusvalia, Cobertura.TERCEROS);
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(1));
		assertTrue(seguro.precio()==510);
		
		try {
			seguro = new Seguro(10000, clienteSinMinusvalia, Cobertura.TODO_RIESGO);
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		seguro.setFechaUltimoSiniestro(LocalDate.now().minusMonths(6));
		assertTrue(seguro.precio()==1400);
		
		try {
			seguro = new Seguro(1, clienteSinMinusvalia, Cobertura.TERCEROS_LUNAS);
		} catch (DatoIncorrectoException e) {
			fail("No deberia lanza la excepcion");
			e.printStackTrace();
		}
		seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(1));
		System.out.println(seguro.precio());
		assertTrue(seguro.precio()==800);
	}
}
