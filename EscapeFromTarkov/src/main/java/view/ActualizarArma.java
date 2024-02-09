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

public class ActualizarArma extends JDialog implements KeyListener, ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreArma;
	private JTextField txtAtributo;
	private JTextField txtValorActualizado;
	private JButton btnActualizarArma;
	TarkovController controller;

	/**
	 * Create the dialog.
	 */
	public ActualizarArma(TarkovController controller) {
		this.controller = controller;
		
		setBounds(100, 100, 1098, 684);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\lucas\\Desktop\\DAM\\Acceso a datos\\EscapeFromTarkov\\src\\main\\resources\\LOGO (1).png"));
		lblNewLabel.setBounds(287, 26, 499, 139);
		contentPanel.add(lblNewLabel);

		JLabel lblNombreDelArma = new JLabel("Nombre del arma:\r\n");
		lblNombreDelArma.setFont(new Font("MV Boli", Font.BOLD, 28));
		lblNombreDelArma.setBounds(396, 233, 264, 26);
		contentPanel.add(lblNombreDelArma);

		txtNombreArma = new JTextField();
		txtNombreArma.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNombreArma.setColumns(10);
		txtNombreArma.setBackground(Color.WHITE);
		txtNombreArma.setBounds(396, 284, 247, 40);
		contentPanel.add(txtNombreArma);
		txtNombreArma.addKeyListener(this);

		JLabel lblNombreDelAtributo = new JLabel("Nombre del atributo:\r\n");
		lblNombreDelAtributo.setFont(new Font("MV Boli", Font.BOLD, 28));
		lblNombreDelAtributo.setBounds(196, 391, 299, 26);
		contentPanel.add(lblNombreDelAtributo);

		JLabel lblNuevoValor = new JLabel("Nuevo valor:");
		lblNuevoValor.setFont(new Font("MV Boli", Font.BOLD, 28));
		lblNuevoValor.setBounds(550, 391, 264, 26);
		contentPanel.add(lblNuevoValor);

		txtAtributo = new JTextField();
		txtAtributo.setFont(new Font("Arial", Font.PLAIN, 18));
		txtAtributo.setColumns(10);
		txtAtributo.setBackground(Color.WHITE);
		txtAtributo.setBounds(218, 427, 247, 40);
		contentPanel.add(txtAtributo);
		txtAtributo.addKeyListener(this);

		txtValorActualizado = new JTextField();
		txtValorActualizado.setFont(new Font("Arial", Font.PLAIN, 18));
		txtValorActualizado.setColumns(10);
		txtValorActualizado.setBackground(Color.WHITE);
		txtValorActualizado.setBounds(550, 427, 247, 40);
		contentPanel.add(txtValorActualizado);
		txtValorActualizado.addKeyListener(this);

		btnActualizarArma = new JButton("Buscar arma");
		btnActualizarArma.setForeground(Color.RED);
		btnActualizarArma.setFont(new Font("MV Boli", Font.PLAIN, 32));
		btnActualizarArma.setEnabled(false);
		btnActualizarArma.setBounds(380, 534, 263, 60);
		contentPanel.add(btnActualizarArma);
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
