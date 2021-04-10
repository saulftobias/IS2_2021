package practica3;

import java.util.*;

public class Alarmas {

	private List<Alarma> alarmasDesactivadas = new LinkedList<Alarma>();
	private Queue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	private List<Alarma> alarmas = new ArrayList<Alarma>();

	private AlarmasEstado state;

	private ArrayList observadores = new ArrayList();

	public Alarmas() { 
		state = AlarmasEstado.init(this);
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

	public void activaMelodia() {
	}

	public void desactivaMelodia() {
	}

	public int alarmasActivasSize() {
		return alarmasActivas.size();
	}

	public void registraObservador (Observer o) {    
		observadores.add(o);
		o.update();
	}
	public void eliminaObservador (Observer o) { 
		observadores.remove(o); 
	}
	
	public void actualizaObservadores ()
	{     
		Iterator i = observadores.iterator();
		while(i.hasNext())
		{
			Observer o = (Observer) i.next();
			o.update();
		}
	}

	// Signals
	public void nuevaAlarma(String id, Date hora) {
		state.nuevaAlarma(this, id, hora);
	}

	public void borraAlarma(String id) {
		state.borraAlarma(this, id);
	}

	public void apagar () {
		state.apagar(this);
	}

	public void alarmaOff (String id) {
		state.alarmaOff(this, id);
	}

	public void alarmaOn (String id) {
		state.alarmaOn(this, id);
	}
}
