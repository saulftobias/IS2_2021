package practica3.PatronState;

import java.util.Timer;
import java.util.TimerTask;

import practica3.Modelo.Alarmas;

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
public class Sonando extends AlarmasEstado {

	// Constante que representa el tiempo que estará la alarma "sonando"
	private static final long INTERVALO_SONAR = 7000;

	// TimerTask que expira cuando termina el tiempo INTERVALO_SONAR
	protected ApagaAlarmaTask apagaAlarmaTask;

	// Redefinicion de los metodos que provocan una accion en respuesta a una signal

	@Override
	public void apagar(Alarmas context) {

		// Si esta signal se ha ejecutado es porque se ha pulsado el boton y hay que cancelar el timer
		timer.cancel();
		timer.purge();
		
		// Volvemos a crear el timer ya que el anterior ha sido cancelado
		timer = new Timer();

		// Acción de salida
		this.exitAction(context);

		// Acciones asociadas a la transiccion
		context.desactivaAlarma(context.alarmaMasProxima());

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
	public void entryAction(Alarmas context) {
		// Programa el evento temporizado 
		apagaAlarmaTask = new ApagaAlarmaTask(context, this); 
		timer.schedule(apagaAlarmaTask, INTERVALO_SONAR);
		context.activaMelodia();
	}

	@Override
	public void exitAction(Alarmas context) {
		context.desactivaMelodia();
	}

	private class ApagaAlarmaTask extends TimerTask {

		// Atributos utilizados para tener visibilidad sobre el contexto de la aplicacion
		private Alarmas context;
		private AlarmasEstado state;

		public ApagaAlarmaTask(Alarmas context, AlarmasEstado state){
			this.context = context;
			this.state = state;
		}

		public void run() { 

			// Acción de salida
			state.exitAction(context);

			// Acciones asociadas a la transiccion
			context.desactivaAlarma(context.alarmaMasProxima());

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
}
