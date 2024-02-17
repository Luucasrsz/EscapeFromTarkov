package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TarkovController;
import io.IO;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class BorrarArma extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JButton okButton;
	private TarkovController controller;
	private JLabel lblEliminado;

	/**
	 * Create the dialog.
	 */
	public BorrarArma(TarkovController controller) {
		
		this.controller = controller;
		
		setBounds(100, 100, 960,540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		okButton = new JButton("OK");
		okButton.setFont(new Font("OCR A Extended", Font.PLAIN, 29));
		okButton.setBounds(353, 343, 242, 54);
		contentPanel.add(okButton);
		okButton.addActionListener(this);
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);

		textField = new JTextField();
		textField.setFont(new Font("OCR A Extended", Font.PLAIN, 30));
		textField.setBounds(405, 249, 456, 28);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nombre del arma:");
		lblNewLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 30));
		lblNewLabel.setBounds(77, 249, 304, 28);
		contentPanel.add(lblNewLabel);
		
		lblEliminado = new JLabel("");
		lblEliminado.setForeground(new Color(255, 0, 0));
		lblEliminado.setBounds(391, 424, 175, 26);
		contentPanel.add(lblEliminado);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(213, 27, 499, 139);
		contentPanel.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/images/LOGO.png")));
		

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (okButton == e.getSource()) {
			boolean eliminado = controller.deleteArma(textField.getText());
			lblEliminado.setText(eliminado ? "ELIMINADO" : "NO SE HA PODIDO ELIMINAR");
		}
	}
}
