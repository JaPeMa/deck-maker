package models;

import lombok.Data;

@Data
public class Card {

	private int id;
	private String name;
	private int summonCost;
	private int attack;
	private int defense;
	private int value;
	
	@Override
	public String toString() {
		return id + "/" + name + "/" + summonCost + "/" + attack + "/" + defense + "/" + value;
	}
	
}
