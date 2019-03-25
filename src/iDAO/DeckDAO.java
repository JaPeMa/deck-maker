package iDAO;

import models.Deck;

public interface DeckDAO {
	
	public Deck getDeckByName(String deckName);
	public void insertDeck(Deck deck);
	public void updateDeck(Deck deck);

}
