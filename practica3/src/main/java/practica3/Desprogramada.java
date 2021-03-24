package practica3;

import java.util.Date;

public class Desprogramada extends AlarmasEstado {

	@Override
	public void nuevaAlarma(Alarmas context, String id, Date hora) {

		// Acción de salida
		this.exitAction(context);

		// Almaceno el valor del próximo estado y le actualizo
		AlarmasEstado estadoDestino = getDesprogramada();
		context.setState(estadoDestino);

		// Acciones asociadas a la transiccion
		context.anhadeAlarma(new Alarma(id, hora));

		// Ejecuto las acciones de entrada del próximo estado
		estadoDestino.entryAction(context);
		estadoDestino.doAction(context);
	}

	@Override
	public void alarmaOn(Alarmas context, String id) {

		// Acción de salida
		this.exitAction(context);

		// Almaceno el valor del próximo estado y le actualizo
		AlarmasEstado estadoDestino = getDesprogramada();
		context.setState(estadoDestino);

		// Acciones asociadas a la transiccion
		context.alarma(id);

		// Ejecuto las acciones de entrada del próximo estado
		estadoDestino.entryAction(context);
		estadoDestino.doAction(context);
	}

}
