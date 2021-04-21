package practica4;

import static org.junit.Assert.*;

import org.fest.swing.fixture.FrameFixture;
import org.junit.Before;
import org.junit.Test;

public class SegurosGUITest {
	
	private FrameFixture demo;

	@Before
	public void setUp() throws Exception {
		SegurosGUI gui = new SegurosGUI();
		demo = new FrameFixture(gui);
		gui.setVisible(true);
	}

	@Test
	public void test() {
		
		// Casos de Prueba Validos
		demo.textBox("txtFechaUltimoSiniestro").enterText("21/04/2011");
		demo.comboBox("comboCobertura").selectItem(2); // Seleccionamos "TERCEROS"
		demo.textBox("txtPotencia").enterText("1");
		demo.radioButton("btnMinusvalia").click();
		
		demo.button("btnCalcular").click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//assertTrue(demo.textBox("txtPrecio").text()=="300");
		
		// Casos de Prueba NO Validos
		demo.textBox("txtFechaUltimoSiniestro").deleteText();
		demo.comboBox("comboCobertura").selectItem(2); // Seleccionamos "TERCEROS"
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("-1");
		demo.radioButton("btnMinusvalia").click();
		
		demo.button("btnCalcular").click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(demo.textBox("txtPrecio").text()=="¡Dato de entrada erróneo!");
	}

}
