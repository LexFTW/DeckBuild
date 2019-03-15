package deck_build.models;

import java.util.ArrayList;

public class Deck {

	private int deckValue;
	private String deckName;
	private ArrayList<Card> cards;
	
	public Deck(int deckValue, String deckName, ArrayList<Card> cards) {
		super();
		this.deckValue = deckValue;
		this.deckName = deckName;
		this.cards = cards;
	}

	public Deck() {
		super();
	}

	public int getDeckValue() {
		return deckValue;
	}

	public String getDeckName() {
		return deckName;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setDeckValue(int deckValue) {
		this.deckValue = deckValue;
	}

	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return "Deck [deckValue=" + deckValue + ", deckName=" + deckName + ", cards=" + cards + "]";
	}
}
