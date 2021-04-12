package practica3;

import java.util.Date;

/**
 * Clase que hereda de la clase abstracta AlarmasEstado y que modela
 * el comportamiento de las alarmas en respuesta a las distintas señales.
 * 
 * Crucial en el desarrollo del patron state.
 * 
 * @author 	Alvaro Lopez Garcia (alvaro.lopezgar@alumnos.unican.es)
 * 			Saul Fernandez Tobias (saul.fernandezt@alumnos.unican.es)
 * @version abr-2021
 */
public class Desprogramada extends AlarmasEstado {
	
	// Redefinicion de los metodos que provocan una accion en respuesta a una signal

	@Override
	public void nuevaAlarma(Alarmas context, String id, Date hora) {

		// Acción de salida
		this.exitAction(context);
		
		// Acciones asociadas a la transiccion
		context.anhadeAlarma(new Alarma(id, hora));

		// Almaceno el valor del próximo estado y le actualizo
		AlarmasEstado estadoDestino;
		if (context.alarmasActivasSize() != 0) {
			estadoDestino = getProgramada();
		} else {
			estadoDestino = getDesprogramada();
		}
		context.setState(estadoDestino);

		// Ejecuto las acciones de entrada del próximo estado
		estadoDestino.entryAction(context);
		estadoDestino.doAction(context);
	}

	@Override
	public void alarmaOn(Alarmas context, String id) {

		// Acción de salida
		this.exitAction(context);
		
		// Acciones asociadas a la transiccion
		context.activaAlarma(context.getAlarma(id));

		// Almaceno el valor del próximo estado y le actualizo
		AlarmasEstado estadoDestino;
		if (context.alarmasActivasSize() != 0) {
			estadoDestino = getProgramada();
		} else {
			estadoDestino = getDesprogramada();
		}
		context.setState(estadoDestino);

		// Ejecuto las acciones de entrada del próximo estado
		estadoDestino.entryAction(context);
		estadoDestino.doAction(context);
	}

}
