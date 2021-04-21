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
		
		// Casos de Prueba Válidos
		demo.textBox("Ultimo Siniestro").enterText("21/04/2011");
		demo.comboBox("Cobertura").selectItem(2); // Seleccionamos "TERCEROS"
		demo.textBox("Potencia").enterText("1");
		demo.radioButton("Minusvalía").click();
		
		demo.button("CALCULAR").click();
		
		assertTrue(demo.textBox("PRECIO").text()=="300");
	}

}
