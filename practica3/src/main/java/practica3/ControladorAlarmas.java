package practica3;

import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;

class NuevaAlarmaAction extends AbstractAction {
	
	private Alarmas modelo;
	private GUIAlarmas vista;
	
	public NuevaAlarmaAction(Alarmas modelo, GUIAlarmas vista) {
		this.modelo = modelo;
		this.vista = vista;
		putValue(Action.NAME, "Anhadimos una Alarma");
		putValue(Action.SHORT_DESCRIPTION, "Anhadimos una nueva alarma a las que teniamos ya creadas");
	}

	public void actionPerformed(ActionEvent e) {
		String newId = vista.getId();
		Date newDate = vista.getDate();
		
		modelo.nuevaAlarma(newId, newDate);
	}
}

class BorraAlarmaAction extends AbstractAction {
	
	private Alarmas modelo;
	private GUIAlarmas vista;
	
	public BorraAlarmaAction(Alarmas modelo, GUIAlarmas vista) {
		this.modelo = modelo;
		this.vista = vista;
		putValue(Action.NAME, "Borramos una Alarma");
		putValue(Action.SHORT_DESCRIPTION, "Borramos una alarma a las que teniamos ya creadas");
	}

	public void actionPerformed(ActionEvent e) {
		// TODO
		String id = vista.getDesactiva();
		
		modelo.borraAlarma(id);
	}
}






public class ControladorAlarmas {

	Alarmas modelo;
	GUIAlarmas vista;
	
	public ControladorAlarmas(Alarmas modelo, GUIAlarmas vista) {
		
		this.modelo = modelo;
		this.vista = vista;
		
		this.modelo.registraObservador((Observer) this.vista);
	
	
	}
}
