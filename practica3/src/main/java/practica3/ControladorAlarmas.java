package practica3;

public class ControladorAlarmas {

	Alarmas modelo;
	GUIAlarmas vista;
	
	public ControladorAlarmas(Alarmas modelo, GUIAlarmas vista) {
		
		this.modelo = modelo;
		this.vista = vista;
		
		this.modelo.registraObservador((Observer) this.vista);
	
	
	}
}
