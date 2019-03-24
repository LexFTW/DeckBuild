package deckbuild.views;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import deckbuild.implementations.CardImplExistDB;
import deckbuild.implementations.DeckImplMongo;
import deckbuild.models.Card;
import deckbuild.models.Deck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DeckBuildController {

	@FXML
	private Button btnLoad;
	@FXML
	private Button btnRandom;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnSearch;
	@FXML
	private ListView<Card> listView;
	@FXML
	private ListView<Card> listViewDeck;
	@FXML
	private TextField title;
	@FXML
	private TextField titleSearch;

	private CardImplExistDB icard = new CardImplExistDB();
	private DeckImplMongo ideck = new DeckImplMongo();
	private ArrayList<Card> cards;
	private Deck deck = new Deck();
	private ObservableList<Card> observableList, observableListDeck;

	@FXML
	public void loadCards() {
		this.cards = this.icard.getCards();
		this.observableList = FXCollections.observableArrayList(this.cards);
		this.listView.setItems(this.observableList);
	}

	@FXML
	public void loadRandomDeck() {
		if(this.listView.getItems().size() != 0) {
			this.cards = this.icard.getCards();
			this.deck = this.ideck.generateRandomDeck(this.cards);
			for (Card card : this.deck.getCards()) {
				this.listViewDeck.getItems().add(card);
				this.removeToCards(card);
			}
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("No se puede randomizar la baraja");
			alert.setHeaderText("No se puede randomizar la baraja");
			alert.setContentText("El motivo es porque no se han cargado las cartas previamente, pulsa el botón 'Cargar Cartas'");
			alert.showAndWait();
		}
	}

	@FXML
	public void saveDeck() {
		if(this.title.getText().length() > 0) {
			if(this.ideck.checkDeckName(this.title.getText())) {
				this.deck.setDeckName(this.title.getText());
				this.ideck.saveDeck(this.deck);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Baraja guardada correctamente");
				alert.setHeaderText("Baraja guardada correctamente");
				alert.setContentText("La baraja se ha guardado correctamente en la base de datos");
				alert.showAndWait();
			}else {
				if(this.deck.getDeckName() == null) {
					this.deck.setDeckName(this.title.getText());
				}
				this.ideck.updateDeck(this.deck);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Baraja actualizada correctamente");
				alert.setHeaderText("Baraja actualizada correctamente");
				alert.setContentText("La baraja se ha actualizado correctamente en la base de datos");
				alert.showAndWait();
			}
		}
	}

	@FXML
	public void searchDeck() {
		this.deck = this.ideck.getDeck(this.titleSearch.getText());
		System.out.println(this.deck);
		if(this.deck != null) {
			this.observableListDeck = FXCollections.observableArrayList(this.deck.getCards());
			this.listViewDeck.setItems(this.observableListDeck);
			this.title.setText(this.deck.getDeckName());
			this.titleSearch.setText("");
			
			if(this.listView.getItems().size() == 0) {
				this.loadCards();
			}
			
			for (Card card : this.deck.getCards()) {
				this.removeToCards(card);
			}
			
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("La baraja no se ha encontrado");
			alert.setHeaderText("La baraja no se ha encontrado");
			alert.setContentText("No se pudo cargar la baraja, revisa que el nombre introducido sea correcto");
			alert.showAndWait();
		}
	}


	@FXML
	public void addToDeck() {
		Card c = this.listView.getSelectionModel().getSelectedItem();
		if (this.ideck.addCardToDeck(c)) { // Si no supera el valor limite...
			this.listView.getItems().remove(this.listView.getSelectionModel().getSelectedIndex());
			this.deck.getCards().add(c);
			this.deck.setDeckValue(c.getValue() + this.deck.getDeckValue());
			this.listViewDeck.getItems().add(c);
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("La carta no se pudo añadir");
			alert.setHeaderText("La carta no se pudo añadir");
			alert.setContentText("No se pudo añadir la carta porque supera el número de valor máximo");
			alert.showAndWait();
		}
	}

	@FXML
	public void removeToDeck() {
		Card c = this.listViewDeck.getSelectionModel().getSelectedItem();
		if(this.ideck.removeCardToDeck(c)) {
			this.listViewDeck.getItems().remove(this.listViewDeck.getSelectionModel().getSelectedIndex());
			this.deck.getCards().remove(c);
			this.deck.setDeckValue(this.deck.getDeckValue() - c.getValue());
			this.listView.getItems().add(c);
		}
	}
	
	public void removeToCards(Card c) {
		for (int i = 0; i < this.cards.size(); i++) {
			if(this.cards.get(i).getName().equals(c.getName())) {
				this.cards.remove(i);
			}
		}
		this.observableList = FXCollections.observableArrayList(this.cards);
		this.listView.setItems(this.observableList);
	}
}
