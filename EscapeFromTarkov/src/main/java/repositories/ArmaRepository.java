package repositories;

import java.util.logging.Logger;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import db.MongoDB;

public class ArmaRepository {

	private final Logger logger = Logger.getLogger(ArmaRepository.class.getName());

	MongoClient mongoClient = MongoDB.getClient();
	MongoDatabase database = mongoClient.getDatabase("Tarkov");
	MongoCollection<Document> collection = database.getCollection("ArmasTarkov");

	public boolean deleteArma(String nombre) {

		logger.info("deleteArma()");

		Bson filtro = Filters.eq("nombre", nombre);
		Document doc = collection.findOneAndDelete(filtro);

		return doc != null ? true : false;
	}

	public MongoCursor<Document> findByAtributo(String nombre, String columna) {
		logger.info("finding arma()");
		Bson filtro = Projections.fields(Projections.excludeId());
		MongoCursor<Document> doc = null;

		if (columna.equals("ergonomia") || columna.equals("capacidad") || columna.equals("cadencia")) {
			int numero = Integer.valueOf(nombre);
			doc = collection.find(Filters.eq(columna, numero)).projection(filtro).iterator();
		} else {
			doc = collection.find(Filters.eq(columna, nombre)).projection(filtro).iterator();
		}
		return doc;
	}

	public FindIterable<Document> showArmas() {
		logger.info("showArmas()");
		Bson filtro = Projections.fields(Projections.excludeId());
		FindIterable<Document> doc = collection.find().projection(filtro);

		return doc;
	}

	public boolean insertArma(String armaNueva) {

		try {
			Document document = Document.parse(armaNueva);
			collection.insertOne(document);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean updateArma(String arma, String atributo, String valorActualizado) {

		Bson filtro = Filters.eq("nombre", arma);
		Bson actualizacion = Updates.set(atributo, valorActualizado);

		UpdateResult result = collection.updateOne(filtro, actualizacion);

		return result.getModifiedCount() > 0 ? true : false;

	}

}
