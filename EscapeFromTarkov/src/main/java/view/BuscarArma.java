package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCursor;

import controller.TarkovController;
import io.IO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class BuscarArma extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtAtributo;
	private JTextField txtNombre;
	private JButton btnBuscarArma;
	private TarkovController controller;
	private JTextArea txtrError;
	private JScrollPane consola;
	private JTextArea textArea;

	/**
	 * Create the dialog.
	 */
	public BuscarArma(TarkovController controller) {
		this.controller = controller;

		getContentPane().setBackground(new Color(192, 192, 192));
		setBounds(100, 100, 1100, 580);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 542, 1086, 1);
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\lucas\\Desktop\\DAM\\Acceso a datos\\EscapeFromTarkov\\src\\main\\resources\\LOGO (1).png"));
		lblNewLabel.setBounds(292, 20, 499, 139);
		getContentPane().add(lblNewLabel);

		JLabel lblNombreDelAtributo = new JLabel("Nombre del atributo:\r\n");
		lblNombreDelAtributo.setFont(new Font("MV Boli", Font.BOLD, 19));
		lblNombreDelAtributo.setBounds(57, 228, 218, 26);
		getContentPane().add(lblNombreDelAtributo);

		JLabel lblNombreDelValor = new JLabel("Nombre del valor:\r\n");
		lblNombreDelValor.setFont(new Font("MV Boli", Font.BOLD, 19));
		lblNombreDelValor.setBounds(57, 335, 218, 26);
		getContentPane().add(lblNombreDelValor);

		txtAtributo = new JTextField();
		txtAtributo.setFont(new Font("Arial", Font.PLAIN, 18));
		txtAtributo.setColumns(10);
		txtAtributo.setBackground(Color.WHITE);
		txtAtributo.setBounds(271, 228, 146, 26);
		getContentPane().add(txtAtributo);
		txtAtributo.addKeyListener(this);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNombre.setColumns(10);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setBounds(271, 335, 146, 26);
		getContentPane().add(txtNombre);
		txtNombre.addKeyListener(this);

		btnBuscarArma = new JButton("Buscar arma");
		btnBuscarArma.setForeground(Color.RED);
		btnBuscarArma.setFont(new Font("MV Boli", Font.PLAIN, 32));
		btnBuscarArma.setEnabled(false);
		btnBuscarArma.setBounds(100, 415, 263, 60);
		getContentPane().add(btnBuscarArma);
		btnBuscarArma.addActionListener(this);

		consola = new JScrollPane();
		consola.setBounds(551, 202, 474, 273);
		getContentPane().add(consola);

		textArea = new JTextArea();
		consola.setViewportView(textArea);

		txtrError = new JTextArea();
		txtrError.setVisible(false);
		txtrError.setForeground(new Color(255, 0, 0));
		txtrError.setText("ERROR EN LA BUSQUEDA");
		txtrError.setBackground(new Color(192, 192, 192));
		txtrError.setBounds(100, 496, 401, 22);
		getContentPane().add(txtrError);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (btnBuscarArma == e.getSource()) {
			GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
			Gson gson = builder.create();

			MongoCursor<Document> doc = controller.findByAtributo(txtNombre.getText(), txtAtributo.getText());

			if (!doc.hasNext()) {
				txtrError.setVisible(true);
			} else {
				textArea.setText("");
				while (doc.hasNext()) {
					textArea.setText(textArea.getText() + gson.toJson(doc.next()));
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (txtAtributo == e.getSource()) {

			btnBuscarArma.setEnabled(!(txtAtributo.getText().isEmpty() || txtNombre.getText().isEmpty()));

		}

		if (txtNombre == e.getSource()) {

			btnBuscarArma.setEnabled(!(txtAtributo.getText().isEmpty() || txtNombre.getText().isEmpty()));

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}