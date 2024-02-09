package view;

import java.awt.EventQueue;

import controller.TarkovController;
import repositories.ArmaRepository;

public class PruebaInterfaz {
	public static void main(String[] args) {
		
		TarkovController controller = new TarkovController(new ArmaRepository());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
