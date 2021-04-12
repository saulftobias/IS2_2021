package practica3;

import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;

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
class NuevaAlarmaAction extends AbstractAction {

	// Referencias al modelo y la vista del patron
	private Alarmas modelo;
	private GUIAlarmas vista;

	/**
	 * Constructor de la clase. Inicializa los atributos de la clase y anhade
	 * nombbre y descripccion corta a la accion
	 * @param modelo, Alarmas modelo del patron MVC.
	 * @param vista, GUIAlarmas vista del patron MVC.
	 */
	public NuevaAlarmaAction(Alarmas modelo, GUIAlarmas vista) {
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

		modelo.nuevaAlarma(newId, newDate);
	}
}

@SuppressWarnings("serial")
class BorraAlarmaAction extends AbstractAction {

	// Referencias al modelo y la vista del patron
	private Alarmas modelo;
	private GUIAlarmas vista;

	/**
	 * Constructor de la clase. Inicializa los atributos de la clase y anhade
	 * nombbre y descripccion corta a la accion
	 * @param modelo, Alarmas modelo del patron MVC.
	 * @param vista, GUIAlarmas vista del patron MVC.
	 */
	public BorraAlarmaAction(Alarmas modelo, GUIAlarmas vista) {
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

@SuppressWarnings("serial")
class AlarmaOnAction extends AbstractAction {

	// Referencias al modelo y la vista del patron
	private Alarmas modelo;
	private GUIAlarmas vista;

	/**
	 * Constructor de la clase. Inicializa los atributos de la clase y anhade
	 * nombbre y descripccion corta a la accion
	 * @param modelo, Alarmas modelo del patron MVC.
	 * @param vista, GUIAlarmas vista del patron MVC.
	 */
	public AlarmaOnAction(Alarmas modelo, GUIAlarmas vista) {
		this.modelo = modelo;
		this.vista = vista;
		putValue(Action.NAME, "On");
		putValue(Action.SHORT_DESCRIPTION, "Activamos una alarma de las que teniamos desactivas");
	}

	/**
	 * Metodo que modela las acciones concretas que desencadena la "ejecucion"
	 * de esta accion.
	 */
	public void actionPerformed(ActionEvent e) {
		Alarma a = vista.getDesactiva();

		// Gestion de errores de que no haya ninguna alarma seleccionada
		try {
			modelo.alarmaOn(a.getId());
		} catch (NullPointerException ex) {
			System.out.println("Ninguna alarma seleccionada");
		}
	}
}

@SuppressWarnings("serial")
class AlarmaOffAction extends AbstractAction {

	// Referencias al modelo y la vista del patron
	private Alarmas modelo;
	private GUIAlarmas vista;

	/**
	 * Constructor de la clase. Inicializa los atributos de la clase y anhade
	 * nombbre y descripccion corta a la accion
	 * @param modelo, Alarmas modelo del patron MVC.
	 * @param vista, GUIAlarmas vista del patron MVC.
	 */
	public AlarmaOffAction(Alarmas modelo, GUIAlarmas vista) {
		this.modelo = modelo;
		this.vista = vista;
		putValue(Action.NAME, "Off");
		putValue(Action.SHORT_DESCRIPTION, "Desactivamos una alarma de las que teniamos activas");
	}

	/**
	 * Metodo que modela las acciones concretas que desencadena la "ejecucion"
	 * de esta accion.
	 */
	public void actionPerformed(ActionEvent e) {
		Alarma a = vista.getActiva();

		// Gestion de errores de que no haya ninguna alarma seleccionada
		try {
			modelo.alarmaOff(a.getId());
		} catch (NullPointerException ex) {
			System.out.println("Ninguna alarma seleccionada");
		}
	}
}