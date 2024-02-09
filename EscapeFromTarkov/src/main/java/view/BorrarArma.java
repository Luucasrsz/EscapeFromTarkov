package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TarkovController;
import io.IO;

import javax.swing.JTextField;
import javax.swing.JLabel;

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
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		okButton = new JButton("OK");
		okButton.setBounds(88, 190, 90, 38);
		contentPanel.add(okButton);
		okButton.addActionListener(this);
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(262, 190, 95, 38);
		contentPanel.add(cancelButton);
		cancelButton.setActionCommand("Cancel");

		textField = new JTextField();
		textField.setBounds(147, 84, 136, 38);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("              Nombre del arma?");
		lblNewLabel.setBounds(133, 46, 164, 28);
		contentPanel.add(lblNewLabel);
		
		lblEliminado = new JLabel("");
		lblEliminado.setBounds(147, 149, 175, 26);
		contentPanel.add(lblEliminado);

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
