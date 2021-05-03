package practica4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.containers.ListaOrdenada;

public class IListaOrdenadaTest {

	private ListaOrdenada<Integer> lista;
	private ListaOrdenada<Integer> listaAux;
	private int aux;

	@Before
	public void setUp() throws Exception {
		lista = new ListaOrdenada<Integer>();
		listaAux = new ListaOrdenada<Integer>();
	}

	@Test
	public void testGet() {

		// Metodo get()

		// Casos de prueba validos

		lista.add(1);
		aux = lista.get(0);
		assertTrue(aux == 1);

		lista.clear();

		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		aux = lista.get(1);
		assertTrue(aux == 2);

		lista.clear();

		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		aux = lista.get(3);
		assertTrue(aux == 4);

		lista.clear();

		// Casos de prueba NO validos

		try {
			lista.get(0);
			fail("No se deberia poder acceder al elemento");
		} catch (IndexOutOfBoundsException e) {
			// Se deberia de lanzar la excepcion
		}

		lista.add(1);

		try {
			lista.get(-1);
			fail("No se deberia poder acceder al elemento");
		} catch (IndexOutOfBoundsException e) {
			// Se deberia de lanzar la excepcion
		}

		try {
			lista.get(1);
			fail("No se deberia poder acceder al elemento");
		} catch (IndexOutOfBoundsException e) {
			// Se deberia de lanzar la excepcion
		}
	}

	@Test
	public void testAdd() {

		// Metodo add()

		// Casos de prueba validos

		listaAux.add(5);
		lista.add(5);
		for (int i = 0; i < lista.size(); i++) {
			assertTrue(lista.get(i)== listaAux.get(i));
		}

		lista.clear();

		listaAux.add(1);
		lista.add(1);
		listaAux.add(5);
		lista.add(5);
		for (int i = 0; i < lista.size(); i++) {
			assertTrue(lista.get(i)== listaAux.get(i));
		}

		lista.clear();

		listaAux.add(1);
		lista.add(1);
		listaAux.add(3);
		lista.add(3);
		listaAux.add(4);
		lista.add(4);
		listaAux.add(2);
		lista.add(2);
		for (int i = 0; i < lista.size(); i++) {
			assertTrue(lista.get(i)== listaAux.get(i));
		}

		lista.clear();

		listaAux.add(2);
		lista.add(2);
		listaAux.add(3);
		lista.add(3);
		listaAux.add(4);
		lista.add(4);
		listaAux.add(1);
		lista.add(1);
		for (int i = 0; i < lista.size(); i++) {
			assertTrue(lista.get(i)== listaAux.get(i));
		}

		lista.clear();

		listaAux.add(1);
		lista.add(1);
		listaAux.add(2);
		lista.add(2);
		listaAux.add(3);
		lista.add(3);
		listaAux.add(4);
		lista.add(4);
		for (int i = 0; i < lista.size(); i++) {
			assertTrue(lista.get(i)== listaAux.get(i));
		}

		lista.clear();

		// Casos de prueba NO validos

		try {
			lista.get((Integer) null);
			fail("No se deberia poder acceder al elemento");
		} catch (NullPointerException e) {
			// Se deberia de lanzar la excepcion
		}
	}

	@Test
	public void testRemove() {

		// Metodo remove()

		// Casos de prueba validos

		listaAux.clear();
		lista.add(1);
		lista.remove(0);
		assertTrue(lista.equals(listaAux));

		lista.clear();

		listaAux.add(1);
		lista.add(1);
		lista.add(2);
		listaAux.add(3);
		lista.add(3);
		listaAux.add(4);
		lista.add(4);
		lista.remove(1);
		assertTrue(lista.equals(listaAux));

		lista.clear();
		listaAux.clear();

		listaAux.add(1);
		lista.add(1);
		listaAux.add(2);
		lista.add(2);
		listaAux.add(3);
		lista.add(3);
		lista.add(4);
		lista.remove(3);
		assertTrue(lista.equals(listaAux));

		lista.clear();

		// Casos de prueba NO validos

		try {
			lista.get(0);
			fail("No se deberia poder acceder al elemento");
		} catch (IndexOutOfBoundsException e) {
			// Se deberia de lanzar la excepcion
		}

		lista.add(1);

		try {
			lista.get(-1);
			fail("No se deberia poder acceder al elemento");
		} catch (IndexOutOfBoundsException e) {
			// Se deberia de lanzar la excepcion
		}

		try {
			lista.get(1);
			fail("No se deberia poder acceder al elemento");
		} catch (IndexOutOfBoundsException e) {
			// Se deberia de lanzar la excepcion
		}
	}
	@Test
	public void testSize() {

		// Metodo size()

		// Casos de prueba validos

		aux = lista.size();
		assertTrue(aux == 0);

		lista.add(1);
		aux = lista.size();
		assertTrue(aux == 1);

		lista.add(2);
		lista.add(3);
		lista.add(4);
		aux = lista.size();
		assertTrue(aux == 4);
	}
	@Test
	public void testClear() {

		// Metodo clear()

		// Casos de prueba validos

		lista.clear();
		assertTrue(listaAux.size() == 0);

		lista.add(1);
		lista.clear();
		assertTrue(listaAux.size() == 0);

		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.clear();
		assertTrue(listaAux.size() == 0);
	}
}