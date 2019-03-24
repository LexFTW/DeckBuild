package deckbuild.implementations.connection;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import deckbuild.models.Deck;
import net.sf.saxon.tree.iter.MappingJavaIterator.Mapper;

public class CMongo {

	private CMongo(String collection) {
		this.mongoClient = new MongoClient("localhost" , 27017);
		this.mongoDatabase = this.mongoClient.getDatabase("deckbuilds");
		this.mongoCollection = this.mongoDatabase.getCollection(collection);
	}
	
	public static CMongo getMongoConnection(String collection) {
		if(connection == null) {
			connection = new CMongo(collection);
		}
		
		return connection;
	}
	
	public Deck getAlredyDeck(String name) {
		Document doc = this.mongoCollection.find(Filters.eq("deckName", name)).first();
		Gson gson = new Gson();
		Deck d = gson.fromJson(doc.toJson(), Deck.class);
		return d;
	}
	
	public void updateDeck(Deck d) {
		ObjectMapper map = new ObjectMapper();
		try {
			String jsonUpdateMongo = map.writeValueAsString(d);
			Document doc = Document.parse(jsonUpdateMongo);
			this.mongoCollection.findOneAndReplace(Filters.eq("deckName", d.getDeckName()), doc);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public void saveDeck(Deck d) {
		ObjectMapper map = new ObjectMapper();
		try {
			String jsonMongo = map.writeValueAsString(d);
			Document doc = Document.parse(jsonMongo);
			this.mongoCollection.insertOne(doc);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	private static CMongo connection;
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> mongoCollection;
}
