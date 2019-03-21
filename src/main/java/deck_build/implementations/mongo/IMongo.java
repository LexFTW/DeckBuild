package deck_build.implementations.mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class IMongo {

	private MongoClientURI connection;
	private MongoClient client;
	private MongoDatabase database;
	private MongoCollection<Document> collection;
	private static IMongo mongo;
	
	private IMongo() {
		this.connection = new MongoClientURI("");
		this.client = new MongoClient(this.connection);
		this.database = this.client.getDatabase("deckbuilds");
	}

	public void setCollection(String collection) {
		this.collection = this.database.getCollection(collection);
	}
	
	public static IMongo getIMongoConnection() {
		if(mongo == null) {
			mongo = new IMongo();
		}
		
		return mongo;
	}
}
