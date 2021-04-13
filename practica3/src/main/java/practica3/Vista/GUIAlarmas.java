package practica3.Vista;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

/**
 * Clase que juega el rol de la vista (interfaz gráfica) en el patron
 * MVC (Modelo-Vista-Controlador).
 * 
 * @author 	Alvaro Lopez Garcia (alvaro.lopezgar@alumnos.unican.es)
 * 			Saul Fernandez Tobias (saul.fernandezt@alumnos.unican.es)
 * @version abr-2021
 */
@SuppressWarnings("serial")
public class GUIAlarmas extends JFrame implements PropertyChangeListener, IGUIAlarmas {

	// Atributos de los elementos de la interfaz grafica
	private JFrame frame;
	private JTextField textFieldId;
	private JLabel lblIdAlarma;
	private JLabel lblHoraAlarma;
	private JButton btnNuevaAlarma;
	private JButton btnEliminar;
	private JButton btnOn;
	private JButton btnOff;
	private JButton btnApagar;
	private JLabel lblAlarmasActivas;
	private JSpinner spinner;
	private JLabel lblAlarmasDesactivadas;
	private JList<Alarma>listListaNoActivas;
	private JList<Alarma> listListaActivas;
	private DefaultListModel<Alarma> listaActivas = new DefaultListModel<Alarma>();
	private DefaultListModel<Alarma> listaNoActivas = new DefaultListModel<Alarma>();
	
	// Atributo del sonido que se reproduce
	private Clip sonido;

	// Referencia al modelo del patron
	private IAlarmasDAO misAlarmas;

	/**
	 * Metodo constructor de la clase. Invoca el metodo que inicializa
	 * algunos atributos.
	 * @param a, Alarmas referencia al modelo del patron MVC.
	 */
	public GUIAlarmas(Alarmas a) {
		setResizable(false);
		misAlarmas = a;
		misAlarmas.addPropertyChangeListener(this);

		init();
		setBounds(0,0,700,400);

		// Centramos la ventana
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		// Obtenemos el sonido
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("alarm.wav").getAbsoluteFile());
			sonido = AudioSystem.getClip();
			sonido.open(audioInputStream);
		} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("Error al abrir el sonido.");
		}
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

		// Creacion del cuadro de texto del id
		textFieldId = new JTextField();
		textFieldId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldId.setBounds(205, 139, 101, 25);
		panel.add(textFieldId);
		textFieldId.setColumns(10);

		// Texto que señala donde ha de introducirse el id
		lblIdAlarma = new JLabel("Id Alarma");
		lblIdAlarma.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdAlarma.setBounds(33, 142, 66, 19);
		panel.add(lblIdAlarma);

		// Creacion del selector de la hora
		lblHoraAlarma = new JLabel("Hora Alarma");
		lblHoraAlarma.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHoraAlarma.setBounds(33, 172, 114, 25);
		panel.add(lblHoraAlarma);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.getInstance().get(Calendar.YEAR), 
				Calendar.getInstance().get(Calendar.MONTH), 
				Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

		SpinnerDateModel model = new SpinnerDateModel();
		model.setValue(calendar.getTime());

		spinner = new JSpinner(model);
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner.setSize(101, 25);
		spinner.setLocation(205, 172);

		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
		DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
		formatter.setAllowsInvalid(false); // this makes what you want
		formatter.setOverwriteMode(true);

		spinner.setEditor(editor);
		panel.add(spinner);

		// Creacion del boton de añadir alarma
		btnNuevaAlarma = new JButton();
		btnNuevaAlarma.setText("Añade Alarma");
		btnNuevaAlarma.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNuevaAlarma.setBounds(33, 237, 273, 40);
		panel.add(btnNuevaAlarma);

		// Creacion del boton de apagar
		btnApagar = new JButton();
		btnApagar.setForeground(new Color(178, 34, 34));
		btnApagar.setText("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnApagar.setBounds(33, 276, 273, 77);
		panel.add(btnApagar);

		// Texto que indica la lista de alarmas activas
		lblAlarmasActivas = new JLabel("Alarmas Activas");
		lblAlarmasActivas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlarmasActivas.setBounds(455, 6, 114, 40);
		panel.add(lblAlarmasActivas);

		// Texto que indica la lista de alarmas desactivas
		lblAlarmasDesactivadas = new JLabel("Alarmas Desactivadas");
		lblAlarmasDesactivadas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlarmasDesactivadas.setBounds(436, 153, 157, 30);
		panel.add(lblAlarmasDesactivadas);

		// Creacion del boton que desactiva la alarma
		btnOff = new JButton();
		btnOff.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOff.setText("Off");
		btnOff.setBounds(449, 334, 56, 19);
		panel.add(btnOff);

		// Creacion del boton que activa la alarma
		btnOn = new JButton();
		btnOn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOn.setText("On");
		btnOn.setBounds(517, 334, 52, 19);
		panel.add(btnOn);

		// Creacion del boton que elimina alarmas
		btnEliminar = new JButton();
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEliminar.setText("Borra Alarma");
		btnEliminar.setBounds(449, 307, 120, 19);
		panel.add(btnEliminar);

		// Creacion del texto que es el "titulo" de la aplicacion
		JLabel lblAlarmas = new JLabel("Alarmas");
		lblAlarmas.setFont(new Font("Arial Black", Font.PLAIN, 45));
		lblAlarmas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlarmas.setBounds(33, 27, 273, 100);
		panel.add(lblAlarmas);

		// Paneles donde se muestran las alarmas activas y desactivadas
		listListaActivas = new JList<Alarma>(listaActivas);
		listListaActivas.setBounds(372, 35, 273, 117);
		panel.add(listListaActivas);

		listListaNoActivas = new JList<Alarma>(listaNoActivas);
		listListaNoActivas.setBounds(372, 177, 273, 117);
		panel.add(listListaNoActivas);
	}

	/**
	 * Metodo que devuelve la fecha (fecha y hora) obtenida por
	 * el selector de hora.
	 * @return Date, hora seleccionada.
	 */
	@SuppressWarnings("deprecation")
	public Date getDate() {
		// Para obtener la hora
		Date fecha = (Date) spinner.getValue();
		Calendar cal = Calendar.getInstance();
		
		// Hemos añadido el "-12" ya que por motivos que desconocemos, la 
		// hora que coge por defecto es dentro de 12 horas
		cal.set(Calendar.HOUR, fecha.getHours() - 12);
		cal.set(Calendar.MINUTE, fecha.getMinutes());
		return cal.getTime();
	}

	/**
	 * Metodo que devuelve el String introducido por el usuario
	 * como id de la alarma en el cuadro de texto de la interfaz.
	 * @return String, String del id.
	 */
	public String getId() {
		return textFieldId.getText();
	}

	/**
	 * Metodo que devuelve la Alarma seleccionada por el usuario
	 * de entre la lista de alarmas activas.
	 * @return Alarma, objeto de la clase alarma seleccionada por
	 * el usuario.
	 */
	public Alarma getActiva() {
		return listListaActivas.getSelectedValue();
	}

	/**
	 * Metodo que devuelve la Alarma seleccionada por el usuario
	 * de entre la lista de alarmas desactivadas.
	 * @return Alarma, objeto de la clase alarma seleccionada por
	 * el usuario.
	 */
	public Alarma getDesactiva() {
		return listListaNoActivas.getSelectedValue();
	}

	/**
	 * Metodo que asigna la accion pasada como parametro de entrada
	 * al boton del que se ejecuta.
	 * @param action, Action que ejecuta el boton.
	 */
	public void setNuevaAlarmaAction(Action action) {
		btnNuevaAlarma.setAction(action);
	}

	/**
	 * Metodo que asigna la accion pasada como parametro de entrada
	 * al boton del que se ejecuta.
	 * @param action, Action que ejecuta el boton.
	 */
	public void setApagarAction(Action action) {
		btnApagar.setAction(action);
	}

	/**
	 * Metodo que asigna la accion pasada como parametro de entrada
	 * al boton del que se ejecuta.
	 * @param action, Action que ejecuta el boton.
	 */
	public void setOffAction(Action action) {
		btnOff.setAction(action);
	}

	/**
	 * Metodo que asigna la accion pasada como parametro de entrada
	 * al boton del que se ejecuta.
	 * @param action, Action que ejecuta el boton.
	 */
	public void setOnAction(Action action) {
		btnOn.setAction(action);
	}

	/**
	 * Metodo que asigna la accion pasada como parametro de entrada
	 * al boton del que se ejecuta.
	 * @param action, Action que ejecuta el boton.
	 */
	public void setEliminarAction(Action action) {
		btnEliminar.setAction(action);
	}

	/**
	 * Metodo que se ejectua cada vez que la clase del modelo notifique un
	 * cambio a los objetos que esten suscritos a los distintos eventos.
	 */
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equals("alarmasDesactivadas")) { // Caso de que el atributo que se cambie sean las alarmas Desactivadas

			// Obtenemos el valor de la nueva lista de alarmas desactivadas
			@SuppressWarnings("unchecked")
			LinkedList<Alarma> alarmasDesactivadas =  (LinkedList<Alarma>) evt.getNewValue();

			// Dado que no hay ningun metodo que los añada todos, borramos la lista y la recorremos añadiendolos
			listaNoActivas.removeAllElements();

			for (Alarma a: alarmasDesactivadas) {
				listaNoActivas.addElement(a);
			}
		} else if (evt.getPropertyName().equals("alarmasActivas")) { // Caso de que el atributo que se cambie sean las alarmas Activadas

			// Obtenemos el valor de la nueva lista de alarmas activas
			@SuppressWarnings("unchecked")
			Queue<Alarma> alarmasActivadas =  (PriorityQueue<Alarma>) evt.getNewValue();

			// Dado que no hay ningun metodo que los añada todos, borramos la lista y la recorremos añadiendolos
			listaActivas.removeAllElements();

			for (Alarma a: alarmasActivadas) {
				listaActivas.addElement(a);
			}
		} else if (evt.getPropertyName().equals("state")) {

			if (evt.getNewValue() instanceof Sonando) { // Caso de que el estado sea Sonando
				setNuevaAlarmaAction(null);
				setEliminarAction(null);
				setOnAction(null);
				setOffAction(null);
			} else { // Caso de que estemos en otro estado
				setNuevaAlarmaAction(new NuevaAlarmaAction(misAlarmas, this));
				setEliminarAction(new BorraAlarmaAction(misAlarmas, this));
				setOnAction(new AlarmaOnAction(misAlarmas, this));
				setOffAction(new AlarmaOffAction(misAlarmas, this));
			}
		} else if (evt.getPropertyName().equals("sonido")) { // Caso de que haya que encender / apagar el sonido
			
			if (!sonido.isActive()) { // Caso de que vaya a sonar la alarma
				sonido.start(); // Inicio el sonido
				sonido.loop(Clip.LOOP_CONTINUOUSLY); // Lo pongo en bucle por si el intervalo es mas largo que el sonido
				JOptionPane.showMessageDialog(null, misAlarmas.alarmaMasProxima().toString());
			} else { // Caso de que vaya a dejar de sonar la alarma
				sonido.stop(); // Paro el sonido
				sonido.setMicrosecondPosition(0); // Pongo el sonido al principio
			}
		}
	}
	
	/**
	 * Metodo que se dispara cuando cerramos la ventana para que,
	 * ademas de cerrar la ventana, se "mate" el proceso java de la
	 * aplicacion.
	 */
	@Override
	protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
           System.exit(ABORT);;
        }
     }
}
