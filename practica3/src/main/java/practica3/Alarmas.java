package practica3;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * Clase que representa el conjunto de las alarmas de la aplicacion, sea
 * cual sea el estado de cada una de ellas, y define las operaciones que
 * se realizan sobre estas.
 * 
 * Jugaria el rol del modelo dentro del patrón MVC (Modelo-Vista-Controlador).
 * 
 * @author 	Alvaro Lopez Garcia (alvaro.lopezgar@alumnos.unican.es)
 * 			Saul Fernandez Tobias (saul.fernandezt@alumnos.unican.es)
 * @version abr-2021
 */
public class Alarmas {

	// Listas de alarmas en distintas situaciones dentro del contexto de
	// la aplicacion
	private List<Alarma> alarmasDesactivadas = new LinkedList<Alarma>();
	private Queue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	private List<Alarma> alarmas = new ArrayList<Alarma>();

	// Atributo state que representa el estado del conjunto de TODAS las
	// alarmas (Patron State)
	private AlarmasEstado state;
	
	// Clase utilizada por objetos “beans” para notificarles cambios en los atributos
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	/**
	 * Constructor de la clase. No precisa de ningun parametro. Unicamente
	 * inicializa el atributo state para poner en marcha el patron state.
	 */
	public Alarmas() { 
		state = AlarmasEstado.init(this);
	}

	/**
	 * Metodo que devuelve la alarma de entre las existentes con id igual 
	 * al que se introduce.
	 * @param id, String identificador de la alarma que se busca.
	 * @return Alarma, la alarma si se encuentra, null si no.
	 */
	public Alarma getAlarma(String id) {
		
		// TODO: Comprobar este metodo con Saul
		
		// Creacion de la variable
		Alarma buscada;
		
		for (int i = 0; i < alarmas.size(); i++) {
			buscada = alarmasDesactivadas.get(i);
			
			// Si la encuentro la devuelvo
			if (buscada.getId() == id) return buscada;  
		}
		
		// Devuelvo null si no se encuentra
		return null;
	}

	/**
	 * Metodo que anhade una alarma.
	 * @param a, Alarma a anhadir.
	 * @return boolean, true si se puede anhadir, false si no.
	 */
	public boolean anhadeAlarma(Alarma a) {
		
		// Guardo el valor de las alarmas antes de la inserccion
		List<Alarma> alarmasDesactivadasOld = alarmasDesactivadas;

		// Anhado la alarma al la lista de desactivadas y almaceno el resultado
		boolean ret = alarmasDesactivadas.add(a);

		// Notifico el cambio en el atributo alarmasDesactivadas y
		// devuelvo los valores antes y despues
		changeSupport.firePropertyChange("alarmasDesactivadas", alarmasDesactivadasOld, alarmasDesactivadas);

		// TODO: Quitar (depuracion)
		System.out.println("Alarma Añadida: " + a);
		System.out.println(changeSupport.toString());

		// Devuelvo el resulrado de la operacion
		return ret;
	}

	/**
	 * Metodo que elimina una alarma.
	 * @param a, Alarma a eliminar.
	 * @return boolean, true si se puede eliminar, false si no.
	 */
	public boolean eliminaAlarma(Alarma a) {
		
		// TODO: Comprobar este metodo con Saul
		
		// Guardo el valor de las listas de alarmas antes de la operacion
		List<Alarma> alarmasDesactivadasOld = alarmasDesactivadas;
		Queue<Alarma> alarmasActivasOld = alarmasActivas;

		// La elimino
		alarmasActivas.remove(a);
		alarmasDesactivadas.remove(a);

		// Notifico el cambio en los atributos y devuelvo los valores antes y despues
		changeSupport.firePropertyChange("alarmasDesactivadas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupport.firePropertyChange("alarmasActivas", alarmasActivasOld, alarmasActivas);

		// Devuelvo el resulrado de la operacion
		return alarmas.remove(a);
	}
	
	/**
	 * Metodo que activa una alarma.
	 * @param alarma, Alarma a activar.
	 */
	public void activaAlarma(Alarma alarma) {
		
		// Guardo el valor de las listas de alarmas antes de la operacion
		List<Alarma> alarmasDesactivadasOld = alarmasDesactivadas;
		Queue<Alarma> alarmasActivasOld = alarmasActivas;

		// La activo
		alarmasDesactivadas.remove(alarma);
		alarmasActivas.add(alarma);

		// Notifico el cambio en los atributos y devuelvo los valores antes y despues
		changeSupport.firePropertyChange("alarmasDesactivadas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupport.firePropertyChange("alarmasActivas", alarmasActivasOld, alarmasActivas);
	}

	/**
	 * Metodo que desactiva una alarma.
	 * @param alarma, Alarma a desactivar.
	 */
	public void desactivaAlarma(Alarma alarma) {
		
		// Guardo el valor de las listas de alarmas antes de la operacion
		List<Alarma> alarmasDesactivadasOld = alarmasDesactivadas;
		Queue<Alarma> alarmasActivasOld = alarmasActivas;

		// La desactivo
		alarmasActivas.remove(alarma);
		alarmasDesactivadas.add(alarma);

		// Notifico el cambio en los atributos y devuelvo los valores antes y despues
		changeSupport.firePropertyChange("alarmasDesactivadas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupport.firePropertyChange("alarmasActivas", alarmasActivasOld, alarmasActivas);
	}

	/**
	 * Metodo setter del atributo state. Utilizado por las clases
	 * del patron state.
	 * @param state, AlarmasEstado estado al que se va a cambiar.
	 */
	public void setState(AlarmasEstado state) {
		this.state = state;
	}
	
	/**
	 * Metodo que devuelve la alarma mas proxima en el tiempo.
	 * @return Alarma, alarma mas proxima.
	 */
	public Alarma alarmaMasProxima() {
		return alarmasActivas.peek();
	}

	/**
	 * Metodo que activa la melodia de la alarma mas proxima y lo notifica
	 * a los observadores.
	 */
	public void activaMelodia() {
		changeSupport.firePropertyChange("sonando", null, alarmaMasProxima());
	}

	/**
	 * Metodo que desactiva la melodia de la alarma mas proxima y lo notifica
	 * a los observadores.
	 */
	public void desactivaMelodia() {
		changeSupport.firePropertyChange("sonando", alarmaMasProxima(), null);
	}

	/**
	 * Metodo que devuelve el numero de alarmas alarmasActivas.
	 * @return int, numero de alarmas alarmasActivas.
	 */
	public int alarmasActivasSize() {
		return alarmasActivas.size();
	}

	/**
	 * Metodo que anhade un "Listener" al changeSupport, para ser
	 * notificado cuando cambia algun atributo de la clase.
	 * @param listener, PropertyChangeListener que se va a suscribir.
	 */
	public void addPropertyChangeListener (PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	// Metodos correspondientes a las signals
	
	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * nuevaAlarma.
	 * @param id, String del indentificador de la alarma que se va a anhadir.
	 * @param hora, Date a la quen sonara la alarma.
	 */
	public void nuevaAlarma(String id, Date hora) {
		state.nuevaAlarma(this, id, hora);
	}

	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * borraAlarma.
	 * @param id, String del indentificador de la alarma que se va a borrar.
	 */
	public void borraAlarma(String id) {
		state.borraAlarma(this, id);
	}

	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * apagar.
	 */
	public void apagar() {
		state.apagar(this);
	}

	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * alarmaOff.
	 * @param id, String del indentificador de la alarma que se va a apagar.
	 */
	public void alarmaOff (String id) {
		state.alarmaOff(this, id);
	}

	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * alarmaOn.
	 * @param id, String del indentificador de la alarma que se va a encender.
	 */
	public void alarmaOn (String id) {
		state.alarmaOn(this, id);
	}
}
