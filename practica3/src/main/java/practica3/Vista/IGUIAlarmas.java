package practica3.Vista;

import java.beans.PropertyChangeEvent;
import java.util.Date;
import javax.swing.Action;

import practica3.Modelo.Alarma;

/**
 * Interfaz que representa las opciones que se pueden realizar sobre
 * el modelo de la aplicacion.
 * 
 * Utilizada para que el controlador del patron MVC no conozca la clase
 * especifica sobre la que opera.
 * 
 * @author 	Alvaro Lopez Garcia (alvaro.lopezgar@alumnos.unican.es)
 * 			Saul Fernandez Tobias (saul.fernandezt@alumnos.unican.es)
 * @version abr-2021
 */
public interface IGUIAlarmas {

	/**
	 * Metodo que devuelve la fecha (fecha y hora) obtenida por
	 * el selector de hora.
	 * @return Date, hora seleccionada.
	 */
	public Date getDate();

	/**
	 * Metodo que devuelve el String introducido por el usuario
	 * como id de la alarma en el cuadro de texto de la interfaz.
	 * @return String, String del id.
	 */
	public String getId();

	/**
	 * Metodo que devuelve la Alarma seleccionada por el usuario
	 * de entre la lista de alarmas activas.
	 * @return Alarma, objeto de la clase alarma seleccionada por
	 * el usuario.
	 */
	public Alarma getActiva();

	/**
	 * Metodo que devuelve la Alarma seleccionada por el usuario
	 * de entre la lista de alarmas desactivadas.
	 * @return Alarma, objeto de la clase alarma seleccionada por
	 * el usuario.
	 */
	public Alarma getDesactiva();

	/**
	 * Metodo que asigna la accion pasada como parametro de entrada
	 * al boton del que se ejecuta.
	 * @param action, Action que ejecuta el boton.
	 */
	public void setNuevaAlarmaAction(Action action);

	/**
	 * Metodo que asigna la accion pasada como parametro de entrada
	 * al boton del que se ejecuta.
	 * @param action, Action que ejecuta el boton.
	 */
	public void setApagarAction(Action action);
	
	/**
	 * Metodo que asigna la accion pasada como parametro de entrada
	 * al boton del que se ejecuta.
	 * @param action, Action que ejecuta el boton.
	 */
	public void setOffAction(Action action);

	/**
	 * Metodo que asigna la accion pasada como parametro de entrada
	 * al boton del que se ejecuta.
	 * @param action, Action que ejecuta el boton.
	 */
	public void setOnAction(Action action);

	/**
	 * Metodo que asigna la accion pasada como parametro de entrada
	 * al boton del que se ejecuta.
	 * @param action, Action que ejecuta el boton.
	 */
	public void setEliminarAction(Action action);

	/**
	 * Metodo que se ejectua cada vez que la clase del modelo notifique un
	 * cambio a los objetos que esten suscritos a los distintos eventos.
	 */
	public void propertyChange(PropertyChangeEvent evt);
}
