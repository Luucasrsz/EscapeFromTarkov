package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TarkovController;
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

public class ActualizarArma extends JDialog implements KeyListener, ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreArma;
	private JTextField txtAtributo;
	private JTextField txtValorActualizado;
	private JButton btnActualizarArma;
	TarkovController controller;
	private JTextArea txtrErrorAlActualizar;

	/**
	 * Create the dialog.
	 */
	public ActualizarArma(TarkovController controller) {
		this.controller = controller;
		
		setBounds(100, 100, 1024,605);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/images/LOGO.png")));
		lblNewLabel.setBounds(237, 28, 499, 139);
		contentPanel.add(lblNewLabel);

		JLabel lblNombreDelArma = new JLabel("Nombre del arma:\r\n");
		lblNombreDelArma.setFont(new Font("OCR A Extended", Font.BOLD, 28));
		lblNombreDelArma.setBounds(322, 216, 331, 26);
		contentPanel.add(lblNombreDelArma);

		txtNombreArma = new JTextField();
		txtNombreArma.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNombreArma.setColumns(10);
		txtNombreArma.setBackground(Color.WHITE);
		txtNombreArma.setBounds(339, 252, 247, 40);
		contentPanel.add(txtNombreArma);
		txtNombreArma.addKeyListener(this);

		JLabel lblNombreDelAtributo = new JLabel("Nombre del atributo:\r\n");
		lblNombreDelAtributo.setFont(new Font("OCR A Extended", Font.BOLD, 28));
		lblNombreDelAtributo.setBounds(125, 341, 360, 26);
		contentPanel.add(lblNombreDelAtributo);

		JLabel lblNuevoValor = new JLabel("Nuevo valor:");
		lblNuevoValor.setFont(new Font("OCR A Extended", Font.BOLD, 28));
		lblNuevoValor.setBounds(556, 341, 241, 26);
		contentPanel.add(lblNuevoValor);

		txtAtributo = new JTextField();
		txtAtributo.setFont(new Font("Arial", Font.PLAIN, 18));
		txtAtributo.setColumns(10);
		txtAtributo.setBackground(Color.WHITE);
		txtAtributo.setBounds(173, 378, 247, 40);
		contentPanel.add(txtAtributo);
		txtAtributo.addKeyListener(this);

		txtValorActualizado = new JTextField();
		txtValorActualizado.setFont(new Font("Arial", Font.PLAIN, 18));
		txtValorActualizado.setColumns(10);
		txtValorActualizado.setBackground(Color.WHITE);
		txtValorActualizado.setBounds(550, 378, 247, 40);
		contentPanel.add(txtValorActualizado);
		txtValorActualizado.addKeyListener(this);

		btnActualizarArma = new JButton("Actualizar Arma");
		btnActualizarArma.setEnabled(false);
		btnActualizarArma.setForeground(new Color(0, 0, 0));
		btnActualizarArma.setFont(new Font("OCR A Extended", Font.PLAIN, 32));
		btnActualizarArma.setBounds(321, 449, 343, 60);
		contentPanel.add(btnActualizarArma);
		
		txtrErrorAlActualizar = new JTextArea();
		txtrErrorAlActualizar.setVisible(false);
		txtrErrorAlActualizar.setForeground(new Color(255, 0, 0));
		txtrErrorAlActualizar.setText("ERROR AL ACTUALIZAR ARMA");
		txtrErrorAlActualizar.setBackground(new Color(192, 192, 192));
		txtrErrorAlActualizar.setBounds(391, 524, 216, 22);
		contentPanel.add(txtrErrorAlActualizar);
		btnActualizarArma.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(btnActualizarArma == e.getSource()) {
			boolean actualizado = controller.updateArma(txtNombreArma.getText(), txtAtributo.getText(), txtValorActualizado.getText());
			
			if (actualizado) {
				this.dispose();
			}else {
				txtrErrorAlActualizar.setVisible(true);
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if (txtAtributo == e.getSource()) {

			btnActualizarArma.setEnabled(!(txtAtributo.getText().isEmpty() || txtNombreArma.getText().isEmpty() || txtValorActualizado.getText().isEmpty()));

		}

		if (txtNombreArma == e.getSource()) {

			btnActualizarArma.setEnabled(!(txtAtributo.getText().isEmpty() || txtNombreArma.getText().isEmpty() || txtValorActualizado.getText().isEmpty()));

		}
		
		if (txtValorActualizado == e.getSource()) {

			btnActualizarArma.setEnabled(!(txtAtributo.getText().isEmpty() || txtNombreArma.getText().isEmpty() || txtValorActualizado.getText().isEmpty()));

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
