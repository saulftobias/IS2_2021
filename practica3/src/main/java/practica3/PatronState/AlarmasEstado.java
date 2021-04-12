package practica3.PatronState;

import java.util.Date;
import java.util.Timer;

import practica3.Modelo.Alarmas;

/**
 * Clase abstracta que es heredada por cada clase que vaya a representar un 
 * estado en el que puede estar el conjunto de las alarmas de la aplicacon.
 * 
 * Crucial en el desarrollo del patron state.
 * 
 * @author 	Alvaro Lopez Garcia (alvaro.lopezgar@alumnos.unican.es)
 * 			Saul Fernandez Tobias (saul.fernandezt@alumnos.unican.es)
 * @version abr-2021
 */
public abstract class AlarmasEstado {

	// Atributos de los estados que puede representar esta clase abstracta (patron singleton)
	private static AlarmasEstado programada = new Programadas();
	private static AlarmasEstado desprogramada = new Desprogramadas();
	private static AlarmasEstado sonando = new Sonando();
	
	// Añadido para gestión del evento temporizado
	protected Timer timer= new Timer();
	
	// Getters estáticos de los atributos probados de los objetos de cada estado
	
	/**
	 * Metodo estatuco que devuelve siempre el mismo objeto de
	 * la clase hija Programada.
	 * @return Programada, el objeto de la clase.
	 */
	public static AlarmasEstado getProgramada() {
		return programada;
	};

	/**
	 * Metodo estatuco que devuelve siempre el mismo objeto de
	 * la clase hija Desprogramada.
	 * @return Desprogramada, el objeto de la clase.
	 */
	public static AlarmasEstado getDesprogramada() {
		return desprogramada;
	};
	
	/**
	 * Metodo estatuco que devuelve siempre el mismo objeto de
	 * la clase hija Sonando.
	 * @return Sonando, el objeto de la clase.
	 */
	public static AlarmasEstado getSonando() {
		return sonando;
	};
	
	// Metodo de inicializacion
	
	/**
	 * Metodo que inicializa el estado sin que la clase que lo invoca
	 * tenga que saber cual es.
	 * @param context, objeto de la clase Alarmas.
	 * @return AlarmasEstado, estado inicial.
	 */
	public static AlarmasEstado init(Alarmas context) {
		
		// Obtengo el que es el estado inicial
		AlarmasEstado estadoInit = getDesprogramada();
		
		// Pongo el estado inicial a desprogramada y lo devuelvo
		context.setState(estadoInit);
		return estadoInit;
	}

	// Metodos de las señales
	
	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * nuevaAlarma.
	 * @param context, Alarmas contexto del que se modelan los estados.
	 * @param id, String del indentificador de la alarma que se va a anhadir.
	 * @param hora, Date a la quen sonara la alarma.
	 */
	public void nuevaAlarma(Alarmas context, String id, Date hora) {}

	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * borraAlarma.
	 * @param context, Alarmas contexto del que se modelan los estados.
	 * @param id, String del indentificador de la alarma que se va a borrar.
	 */
	public void borraAlarma(Alarmas conntext, String id) {}

	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * apagar.
	 * @param context, Alarmas contexto del que se modelan los estados.
	 */
	public void apagar(Alarmas context) {}

	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * alarmaOff.
	 * @param context, Alarmas contexto del que se modelan los estados.
	 * @param id, String del indentificador de la alarma que se va a apagar.
	 */
	public void alarmaOff(Alarmas context, String id) {}

	/**
	 * Metodo que desencadena las acciones correspondientes a la signal
	 * alarmaOn.
	 * @param context, Alarmas contexto del que se modelan los estados.
	 * @param id, String del indentificador de la alarma que se va a encender.
	 */
	public void alarmaOn(Alarmas context, String id) {}

	/**
	 * Metodo que desencadena las acciones de entrada del estado al que
	 * se llega.
	 * @param context, Alarmas contexto del que se modelan los estados.
	 */
	public void entryAction(Alarmas context) {}

	/**
	 * Metodo que desencadena las acciones del estado en el que "estamos".
	 * @param context, Alarmas contexto del que se modelan los estados.
	 */
	public void doAction(Alarmas context) {}

	/**
	 * Metodo que desencadena las acciones de salida del estado al que
	 * se sale.
	 * @param context, Alarmas contexto del que se modelan los estados.
	 */
	public void exitAction(Alarmas context) {}
}
