package practica3;

import java.util.Date;

public class Sonando extends AlarmasEstado {
	
	@Override
	public void apagar(Alarmas context) {
		
		// Acción de salida
		this.exitAction(context);

		// Almaceno el valor del próximo estado y le actualizo
		AlarmasEstado estadoDestino = getProgramada();
		context.setState(estadoDestino);

		// Acciones asociadas a la transiccion
		context.desactivaAlarma(context.alarmaMasProxima()); // TODO: Comprobar si está bien

		// Ejecuto las acciones de entrada del próximo estado
		estadoDestino.entryAction(context);
		estadoDestino.doAction(context);
	}
	
	@Override
	public void entryAction(Alarmas context) {
		context.activaMelodia();
	}
	
	@Override
	public void exitAction(Alarmas context) {
		context.desactivaMelodia();
	}


}
