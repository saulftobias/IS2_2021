package practica3.Vista;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.*;
import javax.swing.text.DateFormatter;

import practica3.Controlador.AlarmaOffAction;
import practica3.Controlador.AlarmaOnAction;
import practica3.Controlador.BorraAlarmaAction;
import practica3.Controlador.NuevaAlarmaAction;
import practica3.Modelo.Alarma;
import practica3.Modelo.Alarmas;
import practica3.Modelo.IAlarmasDAO;
import practica3.PatronState.Sonando;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Clase que juega el rol de la vista (interfaz gráfica) en el patron
 * MVC (Modelo-Vista-Controlador).
 * 
 * @author 	Alvaro Lopez Garcia (alvaro.lopezgar@alumnos.unican.es)
 * 			Saul Fernandez Tobias (saul.fernandezt@alumnos.unican.es)
 * @version abr-2021
 */
@SuppressWarnings("serial")
public class Error extends JFrame {

	// Atributos de los elementos de la interfaz grafica
	private JFrame frame;
	private JLabel lblError;

	/**
	 * Metodo constructor de la clase. Invoca el metodo que inicializa
	 * algunos atributos.
	 * @param a, Alarmas referencia al modelo del patron MVC.
	 */
	public Error(Alarmas a) {
		init();
		setBounds(0,0,700,400);
	}

	/**
	 * Metodo que inicializa los metodos de la intefaz grafica.
	 * Sirve para externalizar esta funcionalidad a un metodo 
	 * independiente del constructor.
	 */
	private void init() {

		// Creacion del panel
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		// Texto que señala donde ha de introducirse el id
		lblError = new JLabel("Ninguna alarma seleccionada");
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblError.setBounds(33, 142, 66, 19);
		panel.add(lblError);
	}

}
