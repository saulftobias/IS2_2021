package practica3.Controlador;

import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import practica3.Modelo.IAlarmasDAO;
import practica3.Vista.IGUIAlarmas;

/**
 * Clases que sirven para modelar las acciones que implican la interaccion
 * del usuario con la vista.
 * 
 * Juegan el rol del controlador en el patron MVC (Modelo-Vista-Controlador).
 * 
 * @author 	Alvaro Lopez Garcia (alvaro.lopezgar@alumnos.unican.es)
 * 			Saul Fernandez Tobias (saul.fernandezt@alumnos.unican.es)
 * @version abr-2021
 */
@SuppressWarnings("serial")
public class NuevaAlarmaAction extends AbstractAction {

	// Referencias al modelo y la vista del patron
	private IAlarmasDAO modelo;
	private IGUIAlarmas vista;

	/**
	 * Constructor de la clase. Inicializa los atributos de la clase y anhade
	 * nombbre y descripccion corta a la accion
	 * @param modelo, Alarmas modelo del patron MVC.
	 * @param vista, GUIAlarmas vista del patron MVC.
	 */
	public NuevaAlarmaAction(IAlarmasDAO modelo, IGUIAlarmas vista) {
		this.modelo = modelo;
		this.vista = vista;
		putValue(Action.NAME, "Añade Alarma");
		putValue(Action.SHORT_DESCRIPTION, "Anhadimos una nueva alarma a las que teniamos ya creadas");
	}

	/**
	 * Metodo que modela las acciones concretas que desencadena la "ejecucion"
	 * de esta accion.
	 */
	public void actionPerformed(ActionEvent e) {
		String newId = vista.getId();
		Date newDate = vista.getDate();

		if (modelo.alarma(newId) == null) {
			modelo.nuevaAlarma(newId, newDate);
		} else  {
			// Mensaje de error si ya existe una alarma con ese id
			JOptionPane.showMessageDialog(null, "ERROR: Ya existe una alarma con ese id");
		}
	}
}