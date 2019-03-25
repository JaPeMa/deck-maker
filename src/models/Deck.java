package models;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Data;

public class Deck implements Serializable{

	private String deckName;
	private int deckValue;
	private ArrayList<Card> deck = new ArrayList<Card>();
	@Override
	public String toString() {
		return "Deck [deckName=" + deckName + ", deckValue=" + deckValue + ", Deck=" + deck + "]";
	}
	public String getDeckName() {
		return deckName;
	}
	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}
	public int getDeckValue() {
		return deckValue;
	}
	public void setDeckValue(int deckValue) {
		this.deckValue = deckValue;
	}
	public ArrayList<Card> getDeck() {
		return deck;
	}
	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
	
	
	
}
