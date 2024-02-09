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

import org.json.JSONArray;
import org.json.JSONObject;

import controller.TarkovController;

import javax.swing.JComboBox;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

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
	private JTextField txtErrorAlInsertar;
	private JComboBox<String> tipo ;

	/**
	 * Create the dialog.
	 */
	public InsertarArma(TarkovController controller) {
		this.controller = controller;

		setBounds(100, 100, 1100, 580);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(new Color(0, 0, 0));
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(276, 10, 499, 139);
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\lucas\\Desktop\\DAM\\Acceso a datos\\EscapeFromTarkov\\src\\main\\resources\\LOGO (1).png"));
		contentPanel.add(lblNewLabel);

		nombre = new JTextField();
		nombre.setBounds(223, 300, 146, 26);
		nombre.setBackground(new Color(255, 255, 255));
		nombre.setFont(new Font("Arial", Font.PLAIN, 18));
		contentPanel.add(nombre);
		nombre.setColumns(10);
		nombre.addKeyListener(this);

		JLabel lblNombreArma = new JLabel("Nombre del arma:\r\n");
		lblNombreArma.setBounds(43, 299, 196, 26);
		lblNombreArma.setFont(new Font("MV Boli", Font.BOLD, 19));
		contentPanel.add(lblNombreArma);

		JLabel lblTipo = new JLabel("Tipo:\r\n");
		lblTipo.setBounds(43, 230, 67, 26);
		lblTipo.setFont(new Font("MV Boli", Font.BOLD, 19));
		contentPanel.add(lblTipo);

		tipo = new JComboBox<>(options);
		tipo.setBounds(223, 231, 146, 26);
		tipo.setFont(new Font("Arial", Font.PLAIN, 18));
		contentPanel.add(tipo);

		btnInsertarArma = new JButton("Insertar arma");
		btnInsertarArma.setForeground(new Color(255, 0, 0));
		btnInsertarArma.setEnabled(false);
		btnInsertarArma.setBounds(379, 448, 263, 60);
		btnInsertarArma.setFont(new Font("MV Boli", Font.PLAIN, 32));
		contentPanel.add(btnInsertarArma);
		btnInsertarArma.addActionListener(this);

		JLabel lblOtroAtributo = new JLabel("Otro Atributo");
		lblOtroAtributo.setFont(new Font("MV Boli", Font.BOLD, 19));
		lblOtroAtributo.setBounds(43, 376, 196, 26);
		contentPanel.add(lblOtroAtributo);

		comboBoxAtributo = new JComboBox();
		comboBoxAtributo.setFont(new Font("Arial", Font.PLAIN, 18));
		comboBoxAtributo.setModel(new DefaultComboBoxModel(new String[] { "Ninguno", "Ergonomía", "Calibre",
				"Precisión", "Capacidad", "Municiones", "Modificaciones", "Modo_Disparo", "Otro" }));
		comboBoxAtributo.setBounds(223, 377, 146, 27);
		contentPanel.add(comboBoxAtributo);
		comboBoxAtributo.addActionListener(this);

		txtNombre = new JTextField();
		txtNombre.setVisible(false);
		txtNombre.setBounds(739, 303, 146, 26);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.addKeyListener(this);

		txtAtributo = new JTextField();
		txtAtributo.setVisible(false);
		txtAtributo.setEditable(false);
		txtAtributo.setBackground(new Color(192, 192, 192));
		txtAtributo.setBounds(739, 234, 146, 26);
		contentPanel.add(txtAtributo);
		txtAtributo.setColumns(10);
		txtAtributo.addKeyListener(this);

		lblAtributo = new JLabel("Nombre del atributo:");
		lblAtributo.setVisible(false);
		lblAtributo.setFont(new Font("MV Boli", Font.BOLD, 19));
		lblAtributo.setBounds(496, 230, 364, 26);
		contentPanel.add(lblAtributo);

		lblNombre = new JLabel("Valor del atributo\r\n");
		lblNombre.setVisible(false);
		lblNombre.setFont(new Font("MV Boli", Font.BOLD, 19));
		lblNombre.setBounds(496, 299, 364, 26);
		contentPanel.add(lblNombre);

		jsonArma = new JSONObject();
		modificaciones = new JSONArray();
		modo_disparo = new JSONArray();
		municiones = new JSONArray();

		btnAnadir = new JButton("Añadir Atributo\r\n");
		btnAnadir.setEnabled(false);
		btnAnadir.setVisible(false);
		btnAnadir.setBounds(633, 368, 167, 49);
		contentPanel.add(btnAnadir);
		btnAnadir.addActionListener(this);
		
		txtErrorAlInsertar = new JTextField();
		txtErrorAlInsertar.setText("Error al insertar el arma");
		txtErrorAlInsertar.setBackground(new Color(192, 192, 192));
		txtErrorAlInsertar.setEnabled(false);
		txtErrorAlInsertar.setEditable(false);
		txtErrorAlInsertar.setBounds(448, 514, 122, 19);
		contentPanel.add(txtErrorAlInsertar);
		txtErrorAlInsertar.setColumns(10);

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
			}
		}

		if (btnAnadir == e.getSource()) {
			// CREAR JSON
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
		}

		if (btnInsertarArma == e.getSource()) {
			
			jsonArma.put("nombre", nombre.getText());
			jsonArma.put("tipo", tipo.getSelectedItem());
			
			if(!modificaciones.isEmpty()) {
				jsonArma.put("modificaciones", modificaciones);
			}
			
			if(!municiones.isEmpty()) {
				jsonArma.put("municiones", municiones);
			}
			
			if(!modo_disparo.isEmpty()) {
				jsonArma.put("modo_disparo", modo_disparo);
			}
			

			String jsonString = jsonArma.toString(4);

			boolean insertado = controller.insertArma(jsonString);

			if (insertado) {
				this.dispose();
			}else {
				
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
