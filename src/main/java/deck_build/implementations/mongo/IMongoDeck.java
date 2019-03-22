package deck_build.implementations.mongo;

import deck_build.interfaces.IDeck;

public class IMongoDeck implements IDeck {

	public void randomDeck() {

	}

	public void saveDeck() {

	}

	public void checkDeckName(String name) {
		
	}
	
	public IMongoDeck() {
		this.mongo = IMongo.getIMongoConnection();
		this.mongo.setCollection("decks");
	}

	IMongo mongo;

}
