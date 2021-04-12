package practica3;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.text.DateFormatter;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
		setBounds(0,0,600,600);
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
		textFieldId.setBounds(205, 207, 96, 19);
		panel.add(textFieldId);
		textFieldId.setColumns(10);

		lblIdAlarma = new JLabel("Id Alarma");
		lblIdAlarma.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdAlarma.setBounds(66, 205, 66, 19);
		panel.add(lblIdAlarma);

		lblHoraAlarma = new JLabel("Hora Alarma");
		lblHoraAlarma.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHoraAlarma.setBounds(66, 237, 114, 25);
		panel.add(lblHoraAlarma);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
		calendar.set(Calendar.MINUTE, 0);

		SpinnerDateModel model = new SpinnerDateModel();
		model.setValue(calendar.getTime());

		spinner = new JSpinner(model);
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner.setSize(96, 25);
		spinner.setLocation(205, 236);

		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
		DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
		formatter.setAllowsInvalid(false); // this makes what you want
		formatter.setOverwriteMode(true);

		spinner.setEditor(editor);
		panel.add(spinner);

		btnNuevaAlarma = new JButton();
		btnNuevaAlarma.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNuevaAlarma.setBounds(66, 283, 235, 30);
		panel.add(btnNuevaAlarma);

		btnApagar = new JButton();
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnApagar.setBounds(66, 335, 235, 30);
		panel.add(btnApagar);

		lblAlarmasActivas = new JLabel("Alarmas Activas");
		lblAlarmasActivas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlarmasActivas.setBounds(446, 35, 114, 30);
		panel.add(lblAlarmasActivas);

		lblAlarmasDesactivadas = new JLabel("Alarmas Desactivadas");
		lblAlarmasDesactivadas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlarmasDesactivadas.setBounds(435, 207, 157, 30);
		panel.add(lblAlarmasDesactivadas);

		btnOff = new JButton();
		btnOff.setBounds(446, 377, 52, 19);
		panel.add(btnOff);

		btnOn = new JButton();
		btnOn.setBounds(514, 377, 52, 19);
		panel.add(btnOn);

		btnEliminar = new JButton();
		btnEliminar.setBounds(446, 407, 120, 19);
		panel.add(btnEliminar);

		JLabel lblAlarmas = new JLabel("Alarmas");
		lblAlarmas.setFont(new Font("Arial Black", Font.PLAIN, 45));
		lblAlarmas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlarmas.setBounds(66, 46, 235, 100);
		panel.add(lblAlarmas);

		listListaActivas = new JList<Alarma>(listaActivas);
		listListaActivas.setBounds(446, 75, 114, 122);
		panel.add(listListaActivas);

		listListaNoActivas = new JList<Alarma>(listaNoActivas);
		listListaNoActivas.setBounds(446, 237, 114, 122);
		panel.add(listListaNoActivas);
	}

	public Date getDate () {
		return (Date) spinner.getValue();
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Prueba");
		if (evt.getPropertyName().equals("alarmasDesactivadas")) {
			LinkedList<Alarma> alarmasDesactivadas =  (LinkedList<Alarma>) evt.getNewValue();
			listaNoActivas.removeAllElements();
			listaNoActivas.addElement(alarmasDesactivadas.getLast());
		} else if (evt.getPropertyName().equals("alarmasActivas")) {
			LinkedList<Alarma> alarmasActivadas =  (LinkedList<Alarma>) evt.getNewValue();
			listaActivas.removeAllElements();
			//listaActivas.addAll(alarmasActivadas);
		}
	}
}
