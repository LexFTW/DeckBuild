package deckbuild.implementations;

import java.util.ArrayList;
import java.util.Random;
import deckbuild.implementations.connection.CExistDB;
import deckbuild.implementations.connection.CMongo;
import deckbuild.interfaces.IDeck;
import deckbuild.models.Card;
import deckbuild.models.Deck;

public class DeckImplMongo implements IDeck{

	public boolean addCardToDeck(Card card) {
		if(this.value - card.getValue() >= 0) {
			this.deck.getCards().add(card);
			this.deck.setDeckValue(this.deck.getDeckValue() + card.getValue());
			this.value -= card.getValue();
			return true;
		}
		
		return false;
	}
	
	public boolean removeCardToDeck(Card card) {
		this.deck.getCards().remove(card);
		this.deck.setDeckValue(this.deck.getDeckValue() - card.getValue());
		this.value += card.getValue();
		return true;
	}

	public Deck generateRandomDeck(ArrayList<Card> cards) {
		ArrayList<Card> cardToDeck = new ArrayList<Card>();
		Random random = new Random();
		int deck_value = this.value;
		
		while(deck_value > 0) {
			Card c = cards.get(random.nextInt(cards.size()));
			if((deck_value - c.getValue()) >= 0) {
				cards.remove(c);
				cardToDeck.add(c);
				deck_value -= c.getValue();
			}
		}
		
		this.deck.setCards(cardToDeck);
		this.deck.setDeckValue(this.value);
		return this.deck;		
	}

	public boolean checkDeckName(String name) {
		Deck d = mongo.getAlredyDeck(name);
		if(d == null) {
			return true;
		}
		return false;
	}
	
	public Deck getDeck(String name) {
		return mongo.getAlredyDeck(name);
	}
	
	public void updateDeck(Deck d) {
		this.mongo.updateDeck(d);
	}
	
	public void saveDeck(Deck d) {
		this.mongo.saveDeck(d);
	}

	public DeckImplMongo() {
		this.mongo = CMongo.getMongoConnection("decks");
		this.deck = new Deck();
		this.value = 20;
	}

	private CMongo mongo;
	private Deck deck;
	private int value;
}
