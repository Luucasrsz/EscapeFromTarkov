package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TarkovController;
import repositories.ArmaRepository;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame ventanaMain;
	private JButton btnInsertarArma;
	private JButton btnBorrarArma;
	private JButton btnBuscarArma;
	private JButton btnMostrarArmas;
	private JButton btnActualizarArma;
	private TarkovController controller = new TarkovController(new ArmaRepository());
	
	

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960,540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/images/LOGO.png")));
		lblNewLabel.setBounds(223, 10, 488, 199);
		contentPane.add(lblNewLabel);

		ventanaMain = this;
        ventanaMain.setLocationRelativeTo(null);


		// Insertar arma

		btnInsertarArma = new JButton("Insertar arma");
		btnInsertarArma.setFocusPainted(false);
		btnInsertarArma.setBackground(new Color(255, 255, 255));
		btnInsertarArma.setFont(new Font("OCR A Extended", Font.PLAIN, 32));
		btnInsertarArma.setBounds(20, 249, 284, 60);
		contentPane.add(btnInsertarArma);
		btnInsertarArma.addActionListener(this);

		// Borrar arma

		btnBorrarArma = new JButton("Borrar arma");
		btnBorrarArma.setBackground(new Color(255, 255, 255));
		btnBorrarArma.setFont(new Font("OCR A Extended", Font.PLAIN, 32));
		btnBorrarArma.setBounds(328, 249, 284, 60);
		contentPane.add(btnBorrarArma);
		btnBorrarArma.addActionListener(this);

		// Buscar objeto

		btnBuscarArma = new JButton("Buscar objeto");
		btnBuscarArma.setBackground(new Color(255, 255, 255));
		btnBuscarArma.setFont(new Font("OCR A Extended", Font.PLAIN, 32));
		btnBuscarArma.setBounds(167, 328, 284, 60);
		contentPane.add(btnBuscarArma);
		btnBuscarArma.addActionListener(this);

		// Mostrar armas

		btnMostrarArmas = new JButton("Mostrar armas");
		btnMostrarArmas.setFocusTraversalKeysEnabled(false);
		btnMostrarArmas.setFocusPainted(false);
		btnMostrarArmas.setBackground(new Color(255, 255, 255));
		btnMostrarArmas.setFont(new Font("OCR A Extended", Font.PLAIN, 32));
		btnMostrarArmas.setBounds(636, 249, 284, 60);
		contentPane.add(btnMostrarArmas);
		btnMostrarArmas.addActionListener(this);
		
		btnActualizarArma = new JButton("Actualizar Arma");
		btnActualizarArma.setBackground(new Color(255, 255, 255));
		btnActualizarArma.setFont(new Font("OCR A Extended", Font.PLAIN, 32));
		btnActualizarArma.setBounds(477, 328, 328, 60);
		contentPane.add(btnActualizarArma);
		btnActualizarArma.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(btnInsertarArma == e.getSource()) {
			InsertarArma insertar = new InsertarArma(controller);
			insertar.setVisible(true);
		}else if(btnBorrarArma == e.getSource()) {
			BorrarArma borrar = new BorrarArma(controller);
			borrar.setVisible(true);
		}else if(btnBuscarArma == e.getSource()) {
			BuscarArma buscar = new BuscarArma(controller);
			buscar.setVisible(true);
		}else if(btnMostrarArmas == e.getSource()) {
			MostrarArmas mostrar = new MostrarArmas(controller);
			mostrar.setVisible(true);
		}else if(btnActualizarArma == e.getSource()){
			ActualizarArma actualizar = new ActualizarArma(controller);
			actualizar.setVisible(true);
		}
		
	}
}
