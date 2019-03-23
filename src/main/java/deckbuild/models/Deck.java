package deckbuild.models;

import java.util.ArrayList;

public class Deck {

	private String deckName;
	private int deckValue;
	private ArrayList<Card> cards;
	
	public Deck(int deckValue, String deckName, ArrayList<Card> cards) {
		super();
		this.deckName = deckName;
		this.cards = cards;
		this.deckValue = deckValue;
	}

	public Deck() {
		this.cards = new ArrayList<Card>();
	}

	public String getDeckName() {
		return deckName;
	}

	public int getDeckValue() {
		return deckValue;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public void setDeckValue(int deckValue) {
		this.deckValue = deckValue;
	}

	@Override
	public String toString() {
		return "Deck [deckName=" + deckName + ", deckValue=" + deckValue + ", cards=" + cards + "]";
	}
}
