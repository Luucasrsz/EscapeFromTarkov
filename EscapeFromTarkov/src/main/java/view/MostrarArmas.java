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
import com.mongodb.client.FindIterable;

import controller.TarkovController;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;

public class MostrarArmas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private TarkovController controller;

	/**
	 * Create the dialog.
	 */
	public MostrarArmas(TarkovController controller) {
		this.controller = controller;
		setBounds(100, 100, 960,540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(155, 46, 634, 415);
			contentPanel.add(scrollPane);
			
			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
			scrollPane.setViewportView(textArea);
			
			GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
			Gson gson = builder.create();
			FindIterable<Document> doc = controller.showArmas();
			for (Document document : doc) {
				textArea.setText(textArea.getText() + gson.toJson(document));
			}
		
	}
}
