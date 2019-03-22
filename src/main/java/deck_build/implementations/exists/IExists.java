package deck_build.implementations.exists;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.xmldb.api.DatabaseManager; 
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.google.gson.Gson;

import deck_build.models.Card; 

public class IExists {

	private String driver;
	private String uri;
	private String resource;
	private Class c1;
	private Database database;
	private static final Logger logger = LogManager.getLogger(IExists.class);
	private static IExists exists;
	
	private IExists() {
		this.driver = "org.exist.xmldb.DatabaseImpl";
		this.uri = "xmldb:exist://localhost:8080/exist/xmlrpc/db/";
		this.resource = "cards.xml";
		try {
			this.c1 = Class.forName(driver);
			this.database = (Database) c1.newInstance();
			DatabaseManager.registerDatabase(this.database);
		} catch (ClassNotFoundException e) {
			System.err.println("[ERROR] - Clase no encontrada, más información del error: " + e);
		} catch (InstantiationException e) {
			System.err.println("[ERROR] - Error de instancia, más información del error: " + e);
		} catch (IllegalAccessException e) {
			System.err.println("[ERROR] - Error al acceso a datos, más información del error: " + e);
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JSONArray getCardsFromExist() {
		try {
			Collection collection = DatabaseManager.getCollection(this.uri);
			XMLResource xmlResource = (XMLResource) collection.getResource(this.resource);
			JSONObject jsonObject = XML.toJSONObject((String)xmlResource.getContent());
			JSONArray jsonArray = jsonObject.getJSONObject("cards").getJSONArray("card");
			return jsonArray;
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Card> JSONArrayToArrayList(JSONArray jsonArray){
		ArrayList<Card> cards = new ArrayList<Card>();
		for (Object object : jsonArray) {
			Card c = new Gson().fromJson(object.toString(), Card.class);
			cards.add(c);
		}
		return cards;
	}
	
	public static IExists getIExists() {
		if(exists == null) {
			exists = new IExists();
		}
		
		return exists;
	}
}
