package practica3.Controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

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
public class ApagarAction extends AbstractAction {

	// Referencias al modelo y la vista del patron
	private IAlarmasDAO modelo;

	/**
	 * Constructor de la clase. Inicializa los atributos de la clase y anhade
	 * nombbre y descripccion corta a la accion
	 * @param modelo, Alarmas modelo del patron MVC.
	 * @param vista, GUIAlarmas vista del patron MVC (no se utiliza en este caso).
	 */
	public ApagarAction(IAlarmasDAO modelo, IGUIAlarmas vista) {
		this.modelo = modelo;
		putValue(Action.NAME, "Apagar");
		putValue(Action.SHORT_DESCRIPTION, "Apagamos una alarma cuando esta sonando");
	}

	/**
	 * Metodo que modela las acciones concretas que desencadena la "ejecucion"
	 * de esta accion.
	 */
	public void actionPerformed(ActionEvent e) {
		modelo.apagar();
	}
}