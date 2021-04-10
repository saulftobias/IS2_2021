package practica3;

import java.util.Date;

public class Alarma implements Comparable<Alarma> {

	@Override
	public String toString() {
		return id + "," +  hora;
	}

	private String id;
	private Date hora;
	private boolean sonando;

	public Alarma (String id, Date hora) {
		this.id = id;
		this.hora = hora;
	}

	public String getId() {
		return this.id;
	}
	
	public Date getHora() {
		return this.hora;
	}
	
	public boolean estaSonando() {
		return this.sonando;
	}
	
	public void desactiva() {
		this.sonando = false;
	}
	
	public void activa() {
		this.sonando = true;
	}

	public int compareTo(Alarma o) {
		return this.hora.compareTo(o.hora);
	}

}
