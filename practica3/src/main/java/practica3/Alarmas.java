package practica3;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class Alarmas {

	private List<Alarma> alarmasDesactivadas = new LinkedList<Alarma>();
	private Queue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	private List<Alarma> alarmas = new ArrayList<Alarma>();

	private AlarmasEstado state;
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	
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
		List<Alarma> alarmasDesactivadasOld = alarmasDesactivadas;
		
		alarmas.add(a);
		boolean ret = alarmasDesactivadas.add(a);
		
		changeSupport.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
		
		return ret;
	}

	public boolean eliminaAlarma(Alarma a) {
		List<Alarma> alarmasDesactivadasOld = alarmasDesactivadas;
		Queue<Alarma> alarmasActivasOld = alarmasActivas;
		
		alarmasActivas.remove(a);
		alarmasDesactivadas.remove(a);
		
		changeSupport.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupport.firePropertyChange("activas", alarmasActivasOld, alarmasActivas);
		
		return alarmas.remove(a);
	}

	public Alarma alarmaMasProxima() {
		return alarmasActivas.peek();
	}

	public void desactivaAlarma(Alarma a) {
		List<Alarma> alarmasDesactivadasOld = alarmasDesactivadas;
		Queue<Alarma> alarmasActivasOld = alarmasActivas;
		
		alarmasActivas.remove(a);
		alarmasDesactivadas.add(a);
		
		changeSupport.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupport.firePropertyChange("activas", alarmasActivasOld, alarmasActivas);
	}

	public void setState(AlarmasEstado state) {
		this.state = state;
	}

	public void elimimnaAlarma(String id) {
		List<Alarma> alarmasDesactivadasOld = alarmasDesactivadas;
		Queue<Alarma> alarmasActivasOld = alarmasActivas;
		
		Alarma alarma = getAlarma(id);
		alarmasDesactivadas.remove(alarma);
		alarmasActivas.remove(alarma);
		alarmas.remove(alarma);
		
		changeSupport.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupport.firePropertyChange("activas", alarmasActivasOld, alarmasActivas);
	}

	public void activaAlarma(Alarma alarma) {
		List<Alarma> alarmasDesactivadasOld = alarmasDesactivadas;
		Queue<Alarma> alarmasActivasOld = alarmasActivas;
		
		alarmasDesactivadas.remove(alarma);
		alarmasActivas.add(alarma);

		changeSupport.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupport.firePropertyChange("activas", alarmasActivasOld, alarmasActivas);
	}

	public void activaMelodia(Alarma a) {
		changeSupport.firePropertyChange("sonando", null, a);
	}

	public void desactivaMelodia(Alarma a) {
		changeSupport.firePropertyChange("sonando", a, null);
	}

	public int alarmasActivasSize() {
		return alarmasActivas.size();
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
	
	public void addPropertyChangeListener (PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}
}
