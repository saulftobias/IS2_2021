package practica4;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.text.DateFormatter;

import org.fest.swing.fixture.FrameFixture;
import org.junit.Before;
import org.junit.Test;

public class SegurosGUITest {
	
	private FrameFixture demo;
	private LocalDate fechaAux;

	@Before
	public void setUp() throws Exception {
		SegurosGUI gui = new SegurosGUI();
		demo = new FrameFixture(gui);
		gui.setVisible(true);
	}

	@Test
	public void test() {
		
		// Casos de Prueba Validos
		fechaAux = LocalDate.now().minusYears(10);
		
		demo.textBox("txtFechaUltimoSiniestro").deleteText();
		demo.textBox("txtFechaUltimoSiniestro").enterText(fechaAux.format(DateTimeFormatter.ofPattern("dd&MM&yyyy")));
		demo.comboBox("comboCobertura").selectItem(2); // Seleccionamos "TERCEROS"
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("1");
		demo.radioButton("btnMinusvalia").click();
		
		demo.button("btnCalcular").click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		demo.textBox("txtPrecio").requireText("300.0");
		
		// Casos de Prueba NO Validos
		demo.comboBox("comboCobertura").selectItem(2); // Seleccionamos "TERCEROS"
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("/1");
		
		demo.button("btnCalcular").click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//demo.textBox("txtPrecio").requireText("¡Dato de entrada erróneo!");
		
		fechaAux = LocalDate.now().plusDays(1);
		
		demo.textBox("txtFechaUltimoSiniestro").enterText(fechaAux.format(DateTimeFormatter.ofPattern("dd&MM&yyyy")));
		demo.comboBox("comboCobertura").selectItem(1); // Seleccionamos "TERCEROS"
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("89");
		
		demo.button("btnCalcular").click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//demo.textBox("txtPrecio").requireText("¡Dato de entrada erróneo!");
	}
}
