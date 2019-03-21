package deck_build.implementations.mongo;

import deck_build.interfaces.ICard;

public class IMongoCard implements ICard{

	public void getCards() {
		
	}
	
	public IMongoCard() {
		this.mongo = IMongo.getIMongoConnection();
		this.mongo.setCollection("cards");
	}

	IMongo mongo;
}
