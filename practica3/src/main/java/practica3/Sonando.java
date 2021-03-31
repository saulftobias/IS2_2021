package practica3;

public class Sonando extends AlarmasEstado {
	
	@Override
	public void apagar(Alarmas context) {
		
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
		context.desactivaAlarma(context.alarmaMasProxima());

		// Ejecuto las acciones de entrada del próximo estado
		estadoDestino.entryAction(context);
		estadoDestino.doAction(context);
	}
	
	@Override
	public void entryAction(Alarmas context) {
		context.activaMelodia(null);
	}
	
	@Override
	public void exitAction(Alarmas context) {
		context.desactivaMelodia(null);
	}


}
