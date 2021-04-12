package practica3;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.*;
import javax.swing.text.DateFormatter;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.*;


@SuppressWarnings("serial")
public class GUIAlarmas extends JFrame implements PropertyChangeListener {

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

	private Alarmas misAlarmas = new Alarmas();

	/**
	 * Create the application.
	 */
	public GUIAlarmas(Alarmas a) {
		misAlarmas = a;
		misAlarmas.addPropertyChangeListener(this);
		init();
		setBounds(0,0,700,400);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void init() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		textFieldId = new JTextField();
		textFieldId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldId.setBounds(205, 139, 101, 25);
		panel.add(textFieldId);
		textFieldId.setColumns(10);

		lblIdAlarma = new JLabel("Id Alarma");
		lblIdAlarma.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdAlarma.setBounds(33, 142, 66, 19);
		panel.add(lblIdAlarma);

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

		btnNuevaAlarma = new JButton();
		btnNuevaAlarma.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNuevaAlarma.setBounds(33, 237, 273, 40);
		panel.add(btnNuevaAlarma);

		btnApagar = new JButton();
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnApagar.setBounds(33, 276, 273, 77);
		panel.add(btnApagar);

		lblAlarmasActivas = new JLabel("Alarmas Activas");
		lblAlarmasActivas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlarmasActivas.setBounds(453, 27, 114, 30);
		panel.add(lblAlarmasActivas);

		lblAlarmasDesactivadas = new JLabel("Alarmas Desactivadas");
		lblAlarmasDesactivadas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlarmasDesactivadas.setBounds(436, 153, 157, 30);
		panel.add(lblAlarmasDesactivadas);

		btnOff = new JButton();
		btnOff.setBounds(525, 334, 56, 19);
		panel.add(btnOff);

		btnOn = new JButton();
		btnOn.setBounds(593, 334, 52, 19);
		panel.add(btnOn);

		btnEliminar = new JButton();
		btnEliminar.setBounds(525, 306, 120, 19);
		panel.add(btnEliminar);

		JLabel lblAlarmas = new JLabel("Alarmas");
		lblAlarmas.setFont(new Font("Arial Black", Font.PLAIN, 45));
		lblAlarmas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlarmas.setBounds(33, 27, 273, 100);
		panel.add(lblAlarmas);

		listListaActivas = new JList<Alarma>(listaActivas);
		listListaActivas.setBounds(372, 52, 273, 100);
		panel.add(listListaActivas);

		listListaNoActivas = new JList<Alarma>(listaNoActivas);
		listListaNoActivas.setBounds(372, 177, 273, 117);
		panel.add(listListaNoActivas);
	}

	public Date getDate () {
		Date fecha = (Date) spinner.getValue();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, fecha.getHours() - 12);
		cal.set(Calendar.MINUTE, fecha.getMinutes());
		return cal.getTime();
	}

	public String getId () {
		return textFieldId.getText();
	}

	public Alarma getActiva () {
		return listListaActivas.getSelectedValue();
	}

	public Alarma getDesactiva () {
		return listListaNoActivas.getSelectedValue();
	}

	public void setNuevaAlarmaAction(Action action) {
		btnNuevaAlarma.setAction(action);
	}

	public void setApagarAction(Action action) {
		btnApagar.setAction(action);
	}

	public void setOffAction(Action action) {
		btnOff.setAction(action);
	}

	public void setOnAction(Action action) {
		btnOn.setAction(action);
	}

	public void setEliminarAction(Action action) {
		btnEliminar.setAction(action);
	}

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
		}
	}
}
