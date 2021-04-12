package practica3.Modelo;

import java.beans.PropertyChangeListener;
import java.util.Date;

import practica3.PatronState.AlarmasEstado;

/**
 * Interfaz que representa las opciones que se pueden realizar sobre
 * la vista de la aplicacion.
 * 
 * Utilizada para que el controlador del patron MVC no conozca la clase
 * especifica sobre la que opera.
 * 
 * @author 	Alvaro Lopez Garcia (alvaro.lopezgar@alumnos.unican.es)
 * 			Saul Fernandez Tobias (saul.fernandezt@alumnos.unican.es)
 * @version abr-2021
 */
public interface IAlarmasDAO {
	
	/**
	 * Metodo que devuelve la alarma de entre las existentes con id igual 
	 * al que se introduce.
	 * @param id, String identificador de la alarma que se busca.
	 * @return Alarma, la alarma si se encuentra, null si no.
	 */
	public Alarma getAlarma(String id);

	/**
	 * Metodo que anhade una alarma.
	 * @param a, Alarma a anhadir.
	 * @return boolean, true si se puede anhadir, false si no.
	 */
	public boolean anhadeAlarma(Alarma a);

	/**
	 * Metodo que elimina una alarma.
	 * @param a, Alarma a eliminar.
	 * @return boolean, true si se puede eliminar, false si no.
	 */
	public boolean eliminaAlarma(Alarma a);

	/**
	 * Metodo que activa una alarma.
	 * @param alarma, Alarma a activar.
	 */
	public void activaAlarma(Alarma alarma);

	/**
	 * Metodo que desactiva una alarma.
	 * @param alarma, Alarma a desactivar.
	 */
	public void desactivaAlarma(Alarma alarma);

	/**
	 * Metodo setter del atributo state. Utilizado por las clases
	 * del patron state.
	 * @param state, AlarmasEstado estado al que se va a cambiar.
	 */
	public void setState(AlarmasEstado state);

	/**
	 * Metodo que devuelve la alarma mas proxima en el tiempo.
	 * @return Alarma, alarma mas proxima.
	 */
	public Alarma alarmaMasProxima();

	/**
	 * Metodo que activa la melodia de la alarma mas proxima y lo notifica
	 * a los observadores.
	 */
	public void activaMelodia();

	/**
	 * Metodo que desactiva la melodia de la alarma mas proxima y lo notifica
	 * a los observadores.
	 */
	public void desactivaMelodia();

	/**
	 * Metodo que devuelve el numero de alarmas alarmasActivas.
	 * @return int, numero de alarmas alarmasActivas.
	 */
	public int alarmasActivasSize();

	/**
	 * Metodo que anhade un "Listener" al changeSupport, para ser
	 * notificado cuando cambia algun atributo de la clase.
	 * @param listener, PropertyChangeListener que se va a suscribir.
	 */
	public void addPropertyChangeListener (PropertyChangeListener listener);

	// Metodos correspondientes a las signals

	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * nuevaAlarma.
	 * @param id, String del indentificador de la alarma que se va a anhadir.
	 * @param hora, Date a la quen sonara la alarma.
	 */
	public void nuevaAlarma(String id, Date hora);

	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * borraAlarma.
	 * @param id, String del indentificador de la alarma que se va a borrar.
	 */
	public void borraAlarma(String id);

	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * apagar.
	 */
	public void apagar();

	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * alarmaOff.
	 * @param id, String del indentificador de la alarma que se va a apagar.
	 */
	public void alarmaOff (String id);

	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * alarmaOn.
	 * @param id, String del indentificador de la alarma que se va a encender.
	 */
	public void alarmaOn (String id);
}
