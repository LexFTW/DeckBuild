package deck_build.implementations.exists;

import java.util.ArrayList;

import org.json.JSONArray;

import deck_build.interfaces.ICard;
import deck_build.models.Card;

public class IExistsCard implements ICard{

	private IExists exists;
	private ArrayList<Card> cards;
	
	public ArrayList<Card> getCards() {
		JSONArray jsonArray = this.exists.getCardsFromExist();
		return this.cards = this.exists.JSONArrayToArrayList(jsonArray);
	}

	public IExistsCard() {
		this.exists = IExists.getIExists();
	}
}
