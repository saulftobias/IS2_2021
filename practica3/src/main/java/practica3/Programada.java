package practica3;

import java.util.Date;

public class Programada extends AlarmasEstado {
	
	@Override
	public void nuevaAlarma(Alarmas context, String id, Date hora) {

		// Acción de salida
		this.exitAction(context);

		// Almaceno el valor del próximo estado y le actualizo
		AlarmasEstado estadoDestino = getProgramada();
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
		AlarmasEstado estadoDestino = getProgramada();
		context.setState(estadoDestino);

		// Acciones asociadas a la transiccion
		context.elimimnaAlarma(id);

		// Ejecuto las acciones de entrada del próximo estado
		estadoDestino.entryAction(context);
		estadoDestino.doAction(context);
	}
	
	@Override
	public void alarmaOn(Alarmas context, String id) {

		// Acción de salida
		this.exitAction(context);

		// Almaceno el valor del próximo estado y le actualizo
		AlarmasEstado estadoDestino = getProgramada();
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
		AlarmasEstado estadoDestino = getProgramada();
		context.setState(estadoDestino);

		// Acciones asociadas a la transiccion
		context.desactivaAlarma(context.getAlarma(id));

		// Ejecuto las acciones de entrada del próximo estado
		estadoDestino.entryAction(context);
		estadoDestino.doAction(context);
	}
}
