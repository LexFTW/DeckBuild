package deck_build.implementations.mongo;

import java.util.ArrayList;
import java.util.Random;

import deck_build.interfaces.IDeck;
import deck_build.models.Card;
import deck_build.models.Deck;

public class IMongoDeck implements IDeck {

	private int max_value;
	private Deck current_deck;
	
	public Deck randomDeck(ArrayList<Card> cards) {
		ArrayList<Card> cardToDeck = new ArrayList<Card>();
		Random random = new Random();
		int deck_value = this.max_value;
		
		while(deck_value > 0) {
			Card c = cards.get(random.nextInt(cards.size()));
			if((deck_value - c.getValue()) >= 0) {
				cardToDeck.add(c);
				deck_value -= c.getValue();
			}
		}
		
		this.current_deck = new Deck();
		this.current_deck.setCards(cardToDeck);
		return this.current_deck;
	}

	public void saveDeck() {

	}

	public void checkDeckName(String name) {
		
	}
	
	public IMongoDeck() {
//		this.mongo = IMongo.getIMongoConnection();
//		this.mongo.setCollection("decks");
		this.max_value = 20;
	}

	IMongo mongo;

}
