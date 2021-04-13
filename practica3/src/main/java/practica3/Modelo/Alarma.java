package practica3.Modelo;

import java.util.Date;

/**
 * Clase que representa una alarma de las manejadas en la aplicacion.
 * 
 * @author 	Alvaro Lopez Garcia (alvaro.lopezgar@alumnos.unican.es)
 * 			Saul Fernandez Tobias (saul.fernandezt@alumnos.unican.es)
 * @version abr-2021
 */
public class Alarma implements Comparable<Alarma> {

	// Atributos de la clase
	private String id;
	private Date hora;

	/**
	 * Constructor de la clase. Inicializa los atributos que se pasan 
	 * como argumentos de entrada.
	 * 
	 * @param id, String identificador de la alarma.
	 * @param hora, Date que representa el momento al que sonara.
	 */
	public Alarma (String id, Date hora) {
		this.id = id;
		this.hora = hora;
	}
	// Metodos observadores

	/**
	 * Retorna el id de la alarma.
	 * @return String, el id de la alarma.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Retorna la hora a la que sonara la alarma.
	 * @return Date, la hora de la alarma.
	 */
	public Date getHora() {
		return this.hora;
	}

	// Otros metodos

	/**
	 * Metodo comparativo. Utililzado por la clase alarmas para ordenar
	 * en funcion de la hora las alarmas de la lista alarmasActivas.
	 * @return int, diferencia en tiempo entre las dos alarmas.
	 */
	public int compareTo(Alarma o) {
		return this.hora.compareTo(o.hora);
	}

	/**
	 * Metodo toString, que muestra el id y la hora de la alarna de la que
	 * se invoca el metodo. Utilizado principalmente para depuracion.
	 * @return String, con la informacion ya descrita.
	 */
	@Override
	public String toString() {
		return id + ", " +  hora;
	}
}
