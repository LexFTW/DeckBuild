package deckbuild.interfaces;

import java.util.ArrayList;
import deckbuild.models.Card;

public interface ICard {

	public ArrayList<Card> cards = new ArrayList<Card>();
	
	public ArrayList<Card> getCards();
	
}
