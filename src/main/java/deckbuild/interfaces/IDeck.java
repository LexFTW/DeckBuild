package deckbuild.interfaces;

import java.util.ArrayList;

import deckbuild.models.Card;
import deckbuild.models.Deck;

public interface IDeck {

	public boolean addCardToDeck(Card card);
	public boolean removeCardToDeck(Card card);
	public Deck generateRandomDeck(ArrayList<Card> cards);
	public boolean checkDeckName(String name);
	public Deck getDeck(String name);
	public void updateDeck(Deck d);
	public void saveDeck(Deck d);
}
