package practica3;

public class AlarmasMVC {
	
	public static void main(String[] args) {
		Alarmas modelo = new Alarmas();
		GUIAlarmas vista = new GUIAlarmas(modelo);
		
		vista.setApagarAction(new AlarmaOffAction(modelo, vista));
		vista.setNuevaAlarmaAction(new NuevaAlarmaAction(modelo, vista));
		vista.setEliminarAction(new BorraAlarmaAction(modelo, vista));
		vista.setOnAction(new AlarmaOnAction(modelo, vista));
		vista.setOffAction(new AlarmaOffAction(modelo, vista));
		
		vista.setVisible(true);
		
	}
}
