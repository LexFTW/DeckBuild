package deckbuild.implementations.connection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

public class CExistDB {

	private CExistDB(String resource) {
		this.driver = "org.exist.xmldb.DatabaseImpl";
		this.uri = "xmldb:exist://localhost:8080/exist/xmlrpc/db/";
		this.resource = resource;
		try {
			this.c1 = Class.forName(driver);
			this.database = (Database) c1.newInstance();
			DatabaseManager.registerDatabase(this.database);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}
	
	public JSONArray getArrayFromExistDB(String keyJsonObject, String keyJsonArray) {
		try {
			Collection collection = DatabaseManager.getCollection(this.uri);
			XMLResource xmlResource = (XMLResource) collection.getResource(this.resource);
			JSONObject jsonObject = XML.toJSONObject((String)xmlResource.getContent());
			JSONArray jsonArray = jsonObject.getJSONObject(keyJsonObject).getJSONArray(keyJsonArray);
			return jsonArray;
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static CExistDB getExistDBConnection(String resource) {
		if(connection == null) {
			connection = new CExistDB(resource);
		}
		
		return connection;
	}
	
	private static CExistDB connection;
	private String driver;
	private String uri;
	private String resource;
	private Class c1;
	private Database database;
}
