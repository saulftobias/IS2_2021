package practica3;

import java.util.*;

public class Alarmas {
	
	private List<Alarma> alarmasDesactivadas = new LinkedList<Alarma>();
	private Queue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	
	public Alarma alarma(String id) {
		return new Alarma(id);
	}
	
	public boolean anhadeAlarma(Alarma a) {
		return alarmasDesactivadas.add(a);
	}
	
	public boolean eliminaAlarma(Alarma a) {
		return alarmasDesactivadas.remove(a);
	}
	
	public Alarma alarmaMasProxima() {
		return alarmasActivas.peek();
	}
	
	public desactivaAlarma(Alarma a) {
		alarmasActivas.remove(a);
	}
}
