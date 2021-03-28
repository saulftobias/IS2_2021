package practica3;

import java.util.*;

public class Alarmas {

	private List<Alarma> alarmasDesactivadas = new LinkedList<Alarma>();
	private Queue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	private List<Alarma> alarmas = new ArrayList<Alarma>();

	private AlarmasEstado state;
	
	public Alarmas() { 
		state = state.init(this);
	}

	public Alarma getAlarma(String id) {
		Alarma buscada;
		for (int i = 0; i < alarmas.size(); i++) {
			buscada = alarmasDesactivadas.get(i);
			if (buscada.getId() == id) return buscada;  
		}
		return null;
	}

	public boolean anhadeAlarma(Alarma a) {
		alarmas.add(a);
		return alarmasDesactivadas.add(a);
	}

	public boolean eliminaAlarma(Alarma a) {
		alarmasActivas.remove(a);
		alarmasDesactivadas.remove(a);
		return alarmas.remove(a);
	}

	public Alarma alarmaMasProxima() {
		return alarmasActivas.peek();
	}

	public void desactivaAlarma(Alarma a) {
		alarmasActivas.remove(a);
		alarmasDesactivadas.add(a);
	}

	public void setState(AlarmasEstado state) {
		this.state = state;
	}

	public void elimimnaAlarma(String id) {
		Alarma alarma = getAlarma(id);
		alarmasDesactivadas.remove(alarma);
		alarmasActivas.remove(alarma);
		alarmas.remove(alarma);
	}

	public void activaAlarma(Alarma alarma) {
		alarmasDesactivadas.remove(alarma);
		alarmasActivas.add(alarma);
	}

	public void activaMelodia(Alarma alarma) {
		alarma.activa();
	}

	public void desactivaMelodia(Alarma alarma) {
		alarma.desactiva();
	}
	
	// Signals
	public void nuevaAlarma(Alarma alarma) {
		state.nuevaAlarma(this, alarma.getId(), alarma.getHora());
	}
	
	public void borraAlarma(Alarma alarma) {
		state.borraAlarma(this, alarma.getId());
	}
	
	public void apagar () {
		state.apagar(this);
	}
	
	public void alarmaOff (Alarma alarma) {
		state.alarmaOff(this, alarma.getId());
	}
	
	public void alarmaOn (Alarma alarma) {
		state.alarmaOn(this, alarma.getId());
	}
 }
