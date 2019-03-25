package main;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import DAOimplement.CardsDAOimplExistDB;
import iDAO.CardDAO;
import models.Card;
import models.Deck;
import pages.DeckPage;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DeckPage();
	
		/*MongoClient connection = new MongoClient("localhost", 27017); // Sin parametros accede a localhost con el puerto 27017
		MongoDatabase dataBase = connection.getDatabase("Prueba2");
		MongoCollection<Document> collection = dataBase.getCollection("prueba");
		Deck deck = new Deck();
		deck.setDeckName("test99");
		deck.setDeckValue(9);
		ArrayList<Card> cards = new ArrayList<>();
		Card card = new Card();
		card.setId(1);
		card.setAttack(10);
		card.setDefense(10);
		card.setSummonCost(10);
		card.setValue(10);
		card.setName("Jose Paco Sanchez 10");
		cards.add(card);
		deck.setDeck(cards);
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
		DBObject test;*/
	}

}
