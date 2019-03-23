package deckbuild.implementations;

import java.util.ArrayList;
import org.json.JSONArray;
import com.google.gson.Gson;
import deckbuild.implementations.connection.CExistDB;
import deckbuild.implementations.connection.CMongo;
import deckbuild.interfaces.ICard;
import deckbuild.models.Card;

public class CardImplExistDB implements ICard{

	public ArrayList<Card> getCards() {
		JSONArray array = this.exist.getArrayFromExistDB("cards", "card");
		for (Object object : array) {
			Card c = new Gson().fromJson(object.toString(), Card.class);
			cards.add(c);
		}
		return cards;
	}

	public CardImplExistDB() {
		this.exist = CExistDB.getExistDBConnection("cards.xml");
	}

	private CExistDB exist;
	private ArrayList<Card> cards = new ArrayList<Card>();
}
