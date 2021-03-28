package practica3;

import java.util.Date;

public abstract class AlarmasEstado {

	// Atributos de los estados que puede representar esta clase abstracta (patron singleton)
	private static AlarmasEstado programada = new Programada();
	private static AlarmasEstado desprogramada = new Desprogramada();
	private static AlarmasEstado sonando = new Sonando();
	
	// Getters estáticos de los atributos probados de los objetos de cada estado
	public static AlarmasEstado getProgramada() {
		return programada;
	};

	public static AlarmasEstado getDesprogramada() {
		return desprogramada;
	};
	
	public static AlarmasEstado getSonando() {
		return sonando;
	};
	
	public AlarmasEstado init(Alarmas context) {
		
		// Obtengo el que es el estado inicial
		AlarmasEstado estadoInit = getDesprogramada();
		
		// Pongo el estado inicial a desprogramada y lo devuelvo
		context.setState(estadoInit);
		return estadoInit;
	}

	// Métodos de las señales
	public void nuevaAlarma(Alarmas context, String id, Date hora) {}

	public void borraAlarma(Alarmas conntext, String id) {}

	public void apagar(Alarmas context) {}

	public void alarmaOff(Alarmas context, String id) {}

	public void alarmaOn(Alarmas context, String id) {}

	public void entryAction(Alarmas context) {}

	public void doAction(Alarmas context) {}

	public void exitAction(Alarmas context) {}
}
