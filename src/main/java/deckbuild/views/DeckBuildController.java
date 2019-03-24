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
		if (this.cards == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("[ERROR] - DB001");
			alert.setHeaderText("No se pudo randomizar la baraja");
			alert.setContentText(
					"No se pudo randomizar la baraja, es posible que aún no se hayan cargado las cartas, asegurate pulsando el botón 'Cargar Cartas'");

			alert.showAndWait();
		} else {
			this.deck = this.ideck.generateRandomDeck(this.cards);
			this.observableListDeck = FXCollections.observableArrayList(this.deck.getCards());
			this.listViewDeck.setItems(observableListDeck);
		}
	}

	@FXML
	public void saveDeck() {
		if(this.title.getLength() > 0) {
			if(this.ideck.checkDeckName(title.getText())) {
				this.deck.setDeckName(this.title.getText());
				this.ideck.saveDeck(this.deck);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Baraja Creada Correctamente");
				alert.setHeaderText("La baraja se ha guardado correctamente en la Base de Datos");

				alert.showAndWait();
			}else {
				this.deck.setDeckName(this.title.getText());
				this.ideck.updateDeck(this.deck);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Baraja Actualizada Correctamente");
				alert.setHeaderText("La baraja se ha actualizado correctamente en la Base de Datos");

				alert.showAndWait();
			}
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("[ERROR] - DB002");
			alert.setHeaderText("El nombre de la baraja es incorrecto");
			alert.setContentText(
					"No se pudo guardar la baraja porque el nombre de la baraja ya está en uso o está vacio, prueba de cambiar el nombre o introducirlo.");

			alert.showAndWait();
		}
	}

	@FXML
	public void searchDeck() {
		if (this.cards == null) {
			this.loadCards();
		}

		if (!this.ideck.checkDeckName(this.titleSearch.getText())) {
			this.deck = this.ideck.getDeck(this.titleSearch.getText());
			if (this.deck != null) {
				this.title.setText(this.deck.getDeckName());
				this.observableListDeck = FXCollections.observableArrayList(this.deck.getCards());
				this.listViewDeck.setItems(this.observableListDeck);
				this.titleSearch.setText("");
			}
		}
	}
}
