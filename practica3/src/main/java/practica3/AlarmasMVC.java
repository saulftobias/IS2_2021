package practica3;

/**
 * Clase que sirve para poner en marcha la aplicacion bajo el patron
 * MVC (Modelo-Vista-Controlador).
 * 
 * @author 	Alvaro Lopez Garcia (alvaro.lopezgar@alumnos.unican.es)
 * 			Saul Fernandez Tobias (saul.fernandezt@alumnos.unican.es)
 * @version abr-2021
 */
public class AlarmasMVC {

	public static void main(String[] args) {
		
		// Guardamos referencias al modelo y la vista del patron
		Alarmas modelo = new Alarmas();
		GUIAlarmas vista = new GUIAlarmas(modelo);

		// Inicializamos las acciones asociadas a la vista
		vista.setNuevaAlarmaAction(new NuevaAlarmaAction(modelo, vista));
		vista.setEliminarAction(new BorraAlarmaAction(modelo, vista));
		vista.setOnAction(new AlarmaOnAction(modelo, vista));
		vista.setOffAction(new AlarmaOffAction(modelo, vista));
		vista.setVisible(true);
	}
}
