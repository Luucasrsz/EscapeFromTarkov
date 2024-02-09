package controller;

import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

import repositories.ArmaRepository;

public class TarkovController {

	private final Logger logger = Logger.getLogger(TarkovController.class.getName());

	private ArmaRepository armaRepository;

	public TarkovController(ArmaRepository armaRepository) {
		this.armaRepository = armaRepository;
	}

	public boolean deleteArma(String nombre) {
		logger.info("Borrando arma");
		return armaRepository.deleteArma(nombre);
	}

	public FindIterable<Document> showArmas() {
		logger.info("Mostrando armas");
		return armaRepository.showArmas();
	}
	
	public MongoCursor<Document> findByAtributo(String nombre, String columna) {
		logger.info("Buscando Arma por Nombre");
		return armaRepository.findByAtributo(nombre, columna);
	}
	
	public boolean insertArma(String arma) {
		logger.info("Añadiendo arma");
		return armaRepository.insertArma(arma);
	}
	
	public boolean updateArma(String arma, String atributo, String valorActualizado) {
		logger.info("Actualizando Arma arma");
		return armaRepository.updateArma(arma, atributo, valorActualizado);
	} 


}
