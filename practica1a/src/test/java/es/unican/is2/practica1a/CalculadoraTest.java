package es.unican.is2.practica1a;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculadoraTest {


	@Test
	public void testSuma() {
		assertTrue("La suma de dos n�meros positivos no es correcta", Calculadora.suma(1, 2) == 3);
		
		assertTrue("La suma de 0s no es correcta", Calculadora.suma(0, 0) == 0);
		
		assertTrue("La suma de dos n�meros negativos no es correcta", Calculadora.suma(-1, -2) == -3);
		
		assertTrue("La suma de un n�mero positivo y uno negativo no es correcta", Calculadora.suma(1, -3) == -2);
	}

}
