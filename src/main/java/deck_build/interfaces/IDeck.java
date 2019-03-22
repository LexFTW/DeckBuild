package deck_build.interfaces;

import java.util.ArrayList;

import deck_build.models.Card;
import deck_build.models.Deck;

public interface IDeck {

	public void saveDeck();
	public Deck randomDeck(ArrayList<Card> cards);
	public void checkDeckName(String name);
	
}
