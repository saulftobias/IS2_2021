package practica3;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.text.DateFormatter;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

interface Observer {
	public void update();
}

public class GUIAlarmas implements Observer {

	private JFrame frame;
	private JTextField textFieldId;
	private Alarmas misAlarmas = new Alarmas();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAlarmas window = new GUIAlarmas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIAlarmas() {
		init();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void init() {
		frame = new JFrame();
		frame.setBounds(100, 100, 645, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(205, 207, 96, 19);
		panel.add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel lblIdAlarma = new JLabel("Id Alarma");
		lblIdAlarma.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdAlarma.setBounds(66, 205, 66, 19);
		panel.add(lblIdAlarma);
		
		JLabel lblHoraAlarma = new JLabel("Hora Alarma");
		lblHoraAlarma.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHoraAlarma.setBounds(66, 237, 114, 25);
		panel.add(lblHoraAlarma);
		
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
        calendar.set(Calendar.MINUTE, 0);
		
		SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(calendar.getTime());

        final JSpinner spinner = new JSpinner(model);
        spinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
        spinner.setSize(96, 25);
        spinner.setLocation(205, 236);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false); // this makes what you want
        formatter.setOverwriteMode(true);

        spinner.setEditor(editor);
		panel.add(spinner);
		
		JButton btnNuevaAlarma = new JButton("Nueva Alarma");

		btnNuevaAlarma.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNuevaAlarma.setBounds(66, 283, 235, 30);
		panel.add(btnNuevaAlarma);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnApagar.setBounds(66, 335, 235, 30);
		panel.add(btnApagar);
		
		JLabel lblAlarmasActivas = new JLabel("Alarmas Activas");
		lblAlarmasActivas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlarmasActivas.setBounds(446, 35, 114, 30);
		panel.add(lblAlarmasActivas);
		
		JLabel lblAlarmasActivas_1 = new JLabel("Alarmas Desactivadas");
		lblAlarmasActivas_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlarmasActivas_1.setBounds(435, 207, 157, 30);
		panel.add(lblAlarmasActivas_1);
		
		JButton btnOff = new JButton("Off");
		btnOff.setBounds(446, 377, 52, 19);
		panel.add(btnOff);
		
		JButton btnOn = new JButton("On");
		btnOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnOn.setBounds(514, 377, 52, 19);
		panel.add(btnOn);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(446, 407, 120, 19);
		panel.add(btnEliminar);
		
		JLabel lblAlarmas = new JLabel("Alarmas");
		lblAlarmas.setFont(new Font("Arial Black", Font.PLAIN, 45));
		lblAlarmas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlarmas.setBounds(66, 46, 235, 100);
		panel.add(lblAlarmas);
		
		final DefaultListModel<Alarma> listaActivas = new DefaultListModel<Alarma>();
		JList<Alarma> listListaActivas = new JList<Alarma>(listaActivas);
		listListaActivas.setBounds(446, 75, 175, 122);
		panel.add(listListaActivas);
		
		final JList<Alarma> listListaNoActivas = new JList<Alarma>();
		listListaNoActivas.setBounds(446, 237, 114, 122);
		panel.add(listListaNoActivas);
		
		
		
		btnNuevaAlarma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Date hora = (Date)spinner.getValue();	
				System.out.println(hora);
				String id = textFieldId.getText();
				misAlarmas.nuevaAlarma(id, hora);

				listaActivas.addElement(new Alarma(id, hora));
			}
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("UPDATE\n");
	}
}
