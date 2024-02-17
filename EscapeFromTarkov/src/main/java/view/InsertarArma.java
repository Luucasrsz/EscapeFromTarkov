package view;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.client.MongoCursor;

import controller.TarkovController;

import javax.swing.JComboBox;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

public class InsertarArma extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nombre;
	private TarkovController controller;
	String[] options = { "Fusil de Asalto", "Subfusil", "Pistola", "Escopeta", "Lanzacohetes", "Cuchillos" };
	Integer[] numMod = { 1, 2, 3 };
	private JTextField txtNombre;
	private JTextField txtAtributo;
	private JComboBox<String> comboBoxAtributo;
	private JLabel lblAtributo;
	private JLabel lblNombre;
	private JButton btnAnadir;
	private JSONObject jsonArma;
	private JSONArray modificaciones;
	private JSONArray modo_disparo;
	private JSONArray municiones;
	private JButton btnInsertarArma;
	private JComboBox<String> tipo;
	private JTextArea txtrErrorAlInsertar;
	private JTextArea txtrAtributoAnadido;

	/**
	 * Create the dialog.
	 */
	public InsertarArma(TarkovController controller) {
		this.controller = controller;
		

		setBounds(100, 100, 960, 540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(new Color(0, 0, 0));
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(223, 25, 499, 139);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/images/LOGO.png")));
		contentPanel.add(lblNewLabel);

		nombre = new JTextField();
		nombre.setBounds(231, 261, 196, 26);
		nombre.setBackground(new Color(255, 255, 255));
		nombre.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		contentPanel.add(nombre);
		nombre.setColumns(10);
		nombre.addKeyListener(this);

		JLabel lblNombreArma = new JLabel("Nombre del arma:\r\n");
		lblNombreArma.setBounds(28, 260, 219, 26);
		lblNombreArma.setFont(new Font("OCR A Extended", Font.BOLD, 19));
		contentPanel.add(lblNombreArma);

		JLabel lblTipo = new JLabel("Tipo del arma:\r\n");
		lblTipo.setBounds(28, 191, 181, 26);
		lblTipo.setFont(new Font("OCR A Extended", Font.BOLD, 19));
		contentPanel.add(lblTipo);

		tipo = new JComboBox<>(options);
		tipo.setBounds(231, 192, 196, 26);
		tipo.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		contentPanel.add(tipo);

		btnInsertarArma = new JButton("Insertar arma");
		btnInsertarArma.setForeground(new Color(0, 0, 0));
		btnInsertarArma.setEnabled(false);
		btnInsertarArma.setBounds(329, 408, 263, 60);
		btnInsertarArma.setFont(new Font("OCR A Extended", Font.PLAIN, 29));
		contentPanel.add(btnInsertarArma);
		btnInsertarArma.addActionListener(this);

		JLabel lblOtroAtributo = new JLabel("Otro Atributo:");
		lblOtroAtributo.setFont(new Font("OCR A Extended", Font.BOLD, 19));
		lblOtroAtributo.setBounds(28, 337, 196, 26);
		contentPanel.add(lblOtroAtributo);

		comboBoxAtributo = new JComboBox();
		comboBoxAtributo.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		comboBoxAtributo.setModel(new DefaultComboBoxModel(new String[] { "Ninguno", "Ergonomía", "Calibre",
				"Precisión", "Capacidad", "Municiones", "Modificaciones", "Modo_Disparo", "Otro" }));
		comboBoxAtributo.setBounds(231, 338, 196, 27);
		contentPanel.add(comboBoxAtributo);
		comboBoxAtributo.addActionListener(this);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtNombre.setVisible(false);
		txtNombre.setBounds(739, 264, 197, 26);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.addKeyListener(this);

		txtAtributo = new JTextField();
		txtAtributo.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtAtributo.setVisible(false);
		txtAtributo.setEditable(false);
		txtAtributo.setBackground(new Color(192, 192, 192));
		txtAtributo.setBounds(750, 192, 186, 26);
		contentPanel.add(txtAtributo);
		txtAtributo.setColumns(10);
		txtAtributo.addKeyListener(this);

		lblAtributo = new JLabel("Nombre del atributo:");
		lblAtributo.setVisible(false);
		lblAtributo.setFont(new Font("OCR A Extended", Font.BOLD, 19));
		lblAtributo.setBounds(496, 191, 364, 26);
		contentPanel.add(lblAtributo);

		lblNombre = new JLabel("Valor del atributo\r\n");
		lblNombre.setVisible(false);
		lblNombre.setFont(new Font("OCR A Extended", Font.BOLD, 19));
		lblNombre.setBounds(496, 260, 364, 26);
		contentPanel.add(lblNombre);

		jsonArma = new JSONObject();
		modificaciones = new JSONArray();
		modo_disparo = new JSONArray();
		municiones = new JSONArray();

		btnAnadir = new JButton("Añadir Atributo\r\n");
		btnAnadir.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		btnAnadir.setEnabled(false);
		btnAnadir.setVisible(false);
		btnAnadir.setBounds(641, 329, 167, 49);
		contentPanel.add(btnAnadir);
		
		txtrErrorAlInsertar = new JTextArea();
		txtrErrorAlInsertar.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		txtrErrorAlInsertar.setVisible(false);
		txtrErrorAlInsertar.setForeground(new Color(255, 0, 0));
		txtrErrorAlInsertar.setText("ERROR AL INSERTAR EL ARMA");
		txtrErrorAlInsertar.setBackground(new Color(192, 192, 192));
		txtrErrorAlInsertar.setBounds(358, 471, 268, 22);
		contentPanel.add(txtrErrorAlInsertar);
		
		txtrAtributoAnadido = new JTextArea();
		txtrAtributoAnadido.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		txtrAtributoAnadido.setVisible(false);
		txtrAtributoAnadido.setForeground(new Color(0, 0, 0));
		txtrAtributoAnadido.setText("ATRIBUTO AÑADIDO CON ÉXITO");
		txtrAtributoAnadido.setBackground(new Color(192, 192, 192));
		txtrAtributoAnadido.setBounds(625, 391, 268, 22);
		contentPanel.add(txtrAtributoAnadido);
		btnAnadir.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (comboBoxAtributo == e.getSource()) {
			if (!comboBoxAtributo.getSelectedItem().equals("Ninguno")) {

				lblAtributo.setVisible(true);
				txtAtributo.setVisible(true);
				txtAtributo.setText((String) comboBoxAtributo.getSelectedItem());

				lblNombre.setVisible(true);
				txtNombre.setVisible(true);
				txtNombre.setEditable(true);

				btnAnadir.setVisible(true);
				txtrAtributoAnadido.setVisible(false);

				if (comboBoxAtributo.getSelectedItem().equals("Otro")) {
					txtAtributo.setEditable(true);
					txtAtributo.setText("");
					txtAtributo.setBackground(new Color(255, 255, 255));
				}
			} else {
				lblAtributo.setVisible(false);
				txtAtributo.setVisible(false);
				txtAtributo.setText((String) comboBoxAtributo.getSelectedItem());

				lblNombre.setVisible(false);
				txtNombre.setVisible(false);
				txtNombre.setEditable(false);

				btnAnadir.setVisible(false);
				txtrAtributoAnadido.setVisible(false);
			}
		}

		if (btnAnadir == e.getSource()) {
			// CREAR JSON
			try {
			if (txtAtributo.getText().equals("Modificaciones")) {
				modificaciones.put(txtNombre.getText());
			} else if (txtAtributo.getText().equals("Modo_Disparo")) {
				modo_disparo.put(txtNombre.getText());
			} else if (txtAtributo.getText().equals("Municiones")) {
				municiones.put(txtNombre.getText());
			} else if (txtAtributo.getText().toLowerCase().equals("ergonomia")
					|| txtAtributo.getText().toLowerCase().equals("capacidad")
					|| txtAtributo.getText().toLowerCase().equals("cadencia")) {
				
				int numero = Integer.valueOf(txtNombre.getText());
				jsonArma.put(txtAtributo.getText().toLowerCase(), numero);
			} else {
				jsonArma.put(txtAtributo.getText(), txtNombre.getText());
			}
			
			txtAtributo.setText("");
			txtNombre.setText("");
			txtrAtributoAnadido.setText("ATRIBUTO INTRODUCIDO CON ÉXITO");
			btnAnadir.setEnabled(false);
			
			
			}catch (Exception e1) {
				txtrAtributoAnadido.setText("ERROR AL INTRODUCIR EL ATRIBUTO");
			}
			txtrAtributoAnadido.setVisible(true);
		}

		if (btnInsertarArma == e.getSource()) {

			boolean insertado;

			MongoCursor<Document> doc = controller.findByAtributo(nombre.getText(), "nombre");

			if (doc.hasNext()) {
				insertado = false;
			} else {
				jsonArma.put("nombre", nombre.getText());
				jsonArma.put("tipo", tipo.getSelectedItem());

				if (!modificaciones.isEmpty()) {
					jsonArma.put("modificaciones", modificaciones);
				}

				if (!municiones.isEmpty()) {
					jsonArma.put("municiones", municiones);
				}

				if (!modo_disparo.isEmpty()) {
					jsonArma.put("modo_disparo", modo_disparo);
				}

				String jsonString = jsonArma.toString(4);

				insertado = controller.insertArma(jsonString);
			}

			if (insertado) {
				this.dispose();
			} else {
				txtrErrorAlInsertar.setVisible(true);
			}

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (txtAtributo == e.getSource()) {

			btnAnadir.setEnabled(!(txtAtributo.getText().isEmpty() || txtNombre.getText().isEmpty()));

		}

		if (txtNombre == e.getSource()) {

			btnAnadir.setEnabled(!(txtAtributo.getText().isEmpty() || txtNombre.getText().isEmpty()));

		}

		if (nombre == e.getSource()) {

			btnInsertarArma.setEnabled(!(nombre.getText().isEmpty()));

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
