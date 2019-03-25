package DAOimplement;

import java.util.List;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import connections.MongoDBConnection;
import iDAO.DeckDAO;
import models.Card;
import models.Deck;

import static com.mongodb.client.model.Filters.*;

public class DeckDAOimplMongoDB implements DeckDAO {

	@Override
	public Deck getDeckByName(String deckName) {
		// TODO Auto-generated method stub
		MongoCollection<Document> collection = MongoDBConnection.connectMongoDb();
		
		Document doc = collection.find(eq("deckName", deckName)).first();
		Deck deck = new Gson().fromJson(doc.toJson(), Deck.class);
		return deck;
	}

	@Override
	public void insertDeck(Deck deck) {
		// TODO Auto-generated method stub
		MongoCollection<Document> collection = MongoDBConnection.connectMongoDb();
		
		Document document = new Document();
		document.put("deckName", deck.getDeckName());
		document.put("deckValue", deck.getDeckValue());
		List<Object> cardsOfDeck = new BasicDBList();
		for (Card cardToInsert : deck.getDeck()) {
			DBObject object = new BasicDBObject();
			
			object.put("id", cardToInsert.getId());
			object.put("name", cardToInsert.getName());
			object.put("summonCost", cardToInsert.getSummonCost());
			object.put("attack", cardToInsert.getAttack());
			object.put("defense", cardToInsert.getDefense());
			object.put("value", cardToInsert.getValue());
			
			cardsOfDeck.add(object);
		}
		document.put("deck", cardsOfDeck);
		collection.insertOne(document);
	}

	@Override
	public void updateDeck(Deck deck) {
		MongoCollection<Document> collection = MongoDBConnection.connectMongoDb();
		try {
			collection.replaceOne(Filters.eq("deckName", deck.getDeckName()),
									Document.parse(new ObjectMapper().writeValueAsString(deck)));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
