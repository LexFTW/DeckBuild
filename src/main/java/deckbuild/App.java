package deckbuild;

import java.util.ArrayList;

import deckbuild.implementations.CardImplExistDB;
import deckbuild.implementations.DeckImplMongo;
import deckbuild.models.Card;
import deckbuild.models.Deck;

public class App {
	public static void main(String[] args) {
		// Implementaciones:
		CardImplExistDB card = new CardImplExistDB();
		DeckImplMongo deck = new DeckImplMongo();

		// Baraja Random:
		Deck d = deck.generateRandomDeck(card.getCards());
		
		// Insertar una baraja:
		// deck.saveDeck(d);

		// Obtener una baraja a partir del nombre:
		System.out.println(deck.getDeck("Test"));

		// Actualización de la baraja:
		Deck nCard = deck.generateRandomDeck(card.getCards());
		d.setCards(nCard.getCards());
		d.setDeckName("Test");
		deck.updateDeck(d);
		System.out.println(deck.getDeck("Test"));
		
		
	}
}
