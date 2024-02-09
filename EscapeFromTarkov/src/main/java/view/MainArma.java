package view;

import java.util.List;

import org.bson.Document;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

import controller.TarkovController;
import io.IO;
import repositories.ArmaRepository;

public class MainArma {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TarkovController controller = new TarkovController(new ArmaRepository());

		List<String> opciones = List.of("1.Añadir Arma", "2.Actualizar Arma", "3.Eliminar Arma", "4.Mostrar Armas",
				"5.Buscar por nombre", "0.Salir");

		while (true) {
			IO.println(opciones);
			switch (IO.readInt()) {
			case 1:
				anadirArma(controller);
				break;
			case 3:
				eliminarArma(controller);
				break;

			case 4:
				mostrarArmas(controller);
				break;
			case 5:
				buscarPorAtributo(controller);
				break;
			case 0:
				return;
			}
		}
	}

	private static void eliminarArma(TarkovController controller) {
		IO.print("Nombre del arma? ");
		String nombre = IO.readString();
		boolean eliminado = controller.deleteArma(nombre);
		IO.println(eliminado ? "Eliminado" : "No se ha podido eliminar");
	}

	private static void mostrarArmas(TarkovController controller) {
		GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
		Gson gson = builder.create();

		FindIterable<Document> doc = controller.showArmas();

		for (Document document : doc) {
			System.out.println(gson.toJson(document));
		}
	}

	private static void buscarPorAtributo(TarkovController controller) {
		GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
		Gson gson = builder.create();

		IO.print("Introduzca el nombre del atributo por el que desea filtrar");
		String atributo = IO.readString().toLowerCase();

		
		IO.print("Introduzca " + atributo);
		String nombre = IO.readString();

		MongoCursor<Document> doc = controller.findByAtributo(nombre, atributo);

		if (!doc.hasNext()) {
			IO.print("ERROR EN LA BUSQUEDA. ATRIBUTO O NOMBRE NO ENCONTRADO\n");
		} else {
			while (doc.hasNext()) {
				IO.print(gson.toJson(doc.next()));
			}
		}

	}
	
	private static void anadirArma(TarkovController controller) {
		IO.print("Introduce el arma con estructura JSON ");
		String nombre = IO.readString();
		boolean eliminado = controller.insertArma(nombre);
		IO.println(eliminado ? "Añadido" : "No se ha podido añadir");
	}

}
