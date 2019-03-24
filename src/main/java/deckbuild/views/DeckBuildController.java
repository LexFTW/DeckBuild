package deckbuild.views;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import deckbuild.implementations.CardImplExistDB;
import deckbuild.implementations.DeckImplMongo;
import deckbuild.models.Card;
import deckbuild.models.Deck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DeckBuildController{

	@FXML
	private Button btnLoad;
	@FXML
	private Button btnRandom;
	@FXML
	private ListView<Card> listView;
	@FXML
	private ListView<Card> listViewDeck;
	@FXML
	private TextField title;
	
	private CardImplExistDB icard = new CardImplExistDB();
	private DeckImplMongo ideck = new DeckImplMongo();
	private ArrayList<Card> cards;
	private Deck deck;
	private ObservableList<Card> observableList, observableListDeck;
	
	@FXML
	public void loadCards() {
		this.cards = icard.getCards();
		this.observableList = FXCollections.observableArrayList(this.cards);
		listView.setItems(this.observableList);
	}

	@FXML
	public void loadRandomDeck() {
		if(this.cards == null) {
			this.loadCards();
		}
		this.deck = this.ideck.generateRandomDeck(this.cards);
		this.observableListDeck = FXCollections.observableArrayList(this.deck.getCards());
		this.listViewDeck.setItems(observableListDeck);
	}
	
	@FXML
	public void saveDeck() {
		if(this.ideck.checkDeckName(title.getText())) {
			this.ideck.saveDeck(this.deck);
		}
	}
}
