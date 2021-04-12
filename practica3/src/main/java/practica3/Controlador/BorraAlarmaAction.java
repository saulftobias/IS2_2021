package practica3.Controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import practica3.Modelo.Alarma;
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
public class BorraAlarmaAction extends AbstractAction {

	// Referencias al modelo y la vista del patron
	private IAlarmasDAO modelo;
	private IGUIAlarmas vista;

	/**
	 * Constructor de la clase. Inicializa los atributos de la clase y anhade
	 * nombbre y descripccion corta a la accion
	 * @param modelo, Alarmas modelo del patron MVC.
	 * @param vista, GUIAlarmas vista del patron MVC.
	 */
	public BorraAlarmaAction(IAlarmasDAO modelo, IGUIAlarmas vista) {
		this.modelo = modelo;
		this.vista = vista;
		putValue(Action.NAME, "Borra Alarma");
		putValue(Action.SHORT_DESCRIPTION, "Borramos una alarma a las que teniamos ya creadas");
	}

	/**
	 * Metodo que modela las acciones concretas que desencadena la "ejecucion"
	 * de esta accion.
	 */
	public void actionPerformed(ActionEvent e) {

		// Si no hay una alarma seleccionada de la lista de desactivas miro en las activas
		Alarma a = vista.getDesactiva();

		if (a == null) {
			a = vista.getActiva();
		}

		// Gestion de errores de que no haya ninguna alarma seleccionada
		try {
			modelo.borraAlarma(a.getId());
		} catch (NullPointerException ex) {
			System.out.println("Ninguna alarma seleccionada");
		}
	}
}
