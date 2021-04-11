package practica3;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class Alarmas {

	private List<Alarma> alarmasDesactivadas = new LinkedList<Alarma>();
	private Queue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	private List<Alarma> alarmas = new ArrayList<Alarma>();

	private AlarmasEstado state;
<<<<<<< HEAD
	private PropertyChangeSupport changeSupportActivadas = new PropertyChangeSupport(this);
	private PropertyChangeSupport changeSupportDesactivadas = new PropertyChangeSupport(this);
	private PropertyChangeSupport changeSupportSonando = new PropertyChangeSupport(this);
=======
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
>>>>>>> 420dc2ceb27d9619a6769d4311abc561bc4a76a8
	
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
		
<<<<<<< HEAD
		changeSupportDesactivadas.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
=======
		changeSupport.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
>>>>>>> 420dc2ceb27d9619a6769d4311abc561bc4a76a8
		
		return ret;
	}

	public boolean eliminaAlarma(Alarma a) {
		List<Alarma> alarmasDesactivadasOld = alarmasDesactivadas;
		Queue<Alarma> alarmasActivasOld = alarmasActivas;
		
		alarmasActivas.remove(a);
		alarmasDesactivadas.remove(a);
		
<<<<<<< HEAD
		changeSupportDesactivadas.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupportActivadas.firePropertyChange("activas", alarmasActivasOld, alarmasActivas);
=======
		changeSupport.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupport.firePropertyChange("activas", alarmasActivasOld, alarmasActivas);
>>>>>>> 420dc2ceb27d9619a6769d4311abc561bc4a76a8
		
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
		
<<<<<<< HEAD
		changeSupportDesactivadas.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupportActivadas.firePropertyChange("activas", alarmasActivasOld, alarmasActivas);
=======
		changeSupport.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupport.firePropertyChange("activas", alarmasActivasOld, alarmasActivas);
>>>>>>> 420dc2ceb27d9619a6769d4311abc561bc4a76a8
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
		
<<<<<<< HEAD
		changeSupportDesactivadas.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupportActivadas.firePropertyChange("activas", alarmasActivasOld, alarmasActivas);
=======
		changeSupport.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupport.firePropertyChange("activas", alarmasActivasOld, alarmasActivas);
>>>>>>> 420dc2ceb27d9619a6769d4311abc561bc4a76a8
	}

	public void activaAlarma(Alarma alarma) {
		List<Alarma> alarmasDesactivadasOld = alarmasDesactivadas;
		Queue<Alarma> alarmasActivasOld = alarmasActivas;
		
		alarmasDesactivadas.remove(alarma);
		alarmasActivas.add(alarma);

<<<<<<< HEAD
		changeSupportDesactivadas.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupportActivadas.firePropertyChange("activas", alarmasActivasOld, alarmasActivas);
=======
		changeSupport.firePropertyChange("desactivas", alarmasDesactivadasOld, alarmasDesactivadas);
		changeSupport.firePropertyChange("activas", alarmasActivasOld, alarmasActivas);
>>>>>>> 420dc2ceb27d9619a6769d4311abc561bc4a76a8
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
	
<<<<<<< HEAD
	public void addPropertyChangeActivasListener (PropertyChangeListener listener) {
		changeSupportActivadas.addPropertyChangeListener(listener);
	}
	
	public void addPropertyChangeDesactivasListener (PropertyChangeListener listener) {
		changeSupportDesactivadas.addPropertyChangeListener(listener);
	}
	
	public void addPropertyChangeSonandoListener (PropertyChangeListener listener) {
		changeSupportSonando.addPropertyChangeListener(listener);
	}
=======
	public void addPropertyChangeListener (PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}
	

>>>>>>> 420dc2ceb27d9619a6769d4311abc561bc4a76a8
}
