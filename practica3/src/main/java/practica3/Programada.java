package practica3;

import java.util.Date;
import java.util.TimerTask;

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
public class Programada extends AlarmasEstado {
	
	// TimerTask que expira cuando termina el tiempo INTERVALO_SONAR
	protected SuenaAlarmaTask suenaAlarmaTask;
	
	// Redefinicion de los metodos que provocan una accion en respuesta a una signal
	
	@Override
	public void nuevaAlarma(Alarmas context, String id, Date hora) {

		// Acción de salida
		this.exitAction(context);

		// Almaceno el valor del próximo estado y le actualizo
		AlarmasEstado estadoDestino;
		if (context.alarmasActivasSize() != 0) {
			estadoDestino = getProgramada();
		} else {
			estadoDestino = getDesprogramada();
		}
		context.setState(estadoDestino);

		// Acciones asociadas a la transiccion
		context.anhadeAlarma(new Alarma(id, hora));

		// Ejecuto las acciones de entrada del próximo estado
		estadoDestino.entryAction(context);
		estadoDestino.doAction(context);
	}

	@Override
	public void borraAlarma(Alarmas context, String id) {
		
		// Acción de salida
		this.exitAction(context);

		// Almaceno el valor del próximo estado y le actualizo
		AlarmasEstado estadoDestino;
		if (context.alarmasActivasSize() != 0) {
			estadoDestino = getProgramada();
		} else {
			estadoDestino = getDesprogramada();
		}
		context.setState(estadoDestino);

		// Acciones asociadas a la transiccion
		context.eliminaAlarma(context.getAlarma(id));

		// Ejecuto las acciones de entrada del próximo estado
		estadoDestino.entryAction(context);
		estadoDestino.doAction(context);
	}
	
	@Override
	public void alarmaOn(Alarmas context, String id) {

		// Acción de salida
		this.exitAction(context);

		// Almaceno el valor del próximo estado y le actualizo
		AlarmasEstado estadoDestino;
		if (context.alarmasActivasSize() != 0) {
			estadoDestino = getProgramada();
		} else {
			estadoDestino = getDesprogramada();
		}
		context.setState(estadoDestino);

		// Acciones asociadas a la transiccion
		context.activaAlarma(context.getAlarma(id));

		// Ejecuto las acciones de entrada del próximo estado
		estadoDestino.entryAction(context);
		estadoDestino.doAction(context);
	}

	@Override
	public void alarmaOff(Alarmas context, String id) {
		
		// Acción de salida
		this.exitAction(context);

		// Almaceno el valor del próximo estado y le actualizo
		AlarmasEstado estadoDestino;
		if (context.alarmasActivasSize() != 0) {
			estadoDestino = getProgramada();
		} else {
			estadoDestino = getDesprogramada();
		}
		context.setState(estadoDestino);

		// Acciones asociadas a la transiccion
		context.desactivaAlarma(context.getAlarma(id));

		// Ejecuto las acciones de entrada del próximo estado
		estadoDestino.entryAction(context);
		estadoDestino.doAction(context);
	}
	
	@Override
	public void entryAction(Alarmas context) {
		// Programa el evento temporizado 
		suenaAlarmaTask = new SuenaAlarmaTask(context, this);
		timedStateController.schedule(suenaAlarmaTask, context.alarmaMasProxima().getHora());
	}
	
	private class SuenaAlarmaTask extends TimerTask {

		// Atributos utilizados para tener visibilidad sobre el contexto de la aplicacion
		private Alarmas context;
		private AlarmasEstado state;

		public SuenaAlarmaTask(Alarmas context, AlarmasEstado state){
			this.context = context;
			this.state = state;
		}

		public void run() { 
			
			// Acción de salida
			state.exitAction(context);

			// Almaceno el valor del próximo estado y le actualizo
			AlarmasEstado estadoDestino = getSonando();
			context.setState(estadoDestino);

			// No hay acciones asociadas a la transiccion

			// Ejecuto las acciones de entrada del próximo estado
			estadoDestino.entryAction(context);
			estadoDestino.doAction(context);
		}
	}
}
