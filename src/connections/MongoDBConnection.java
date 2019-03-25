package connections;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

	public static MongoCollection<Document> connectMongoDb() {
		MongoClient connection = new MongoClient("localhost", 27017); // Sin parametros accede a localhost con el puerto 27017
		MongoDatabase dataBase = connection.getDatabase("Prueba2");
		MongoCollection<Document> collection = dataBase.getCollection("prueba");
		
		return collection;
	}
	
}
