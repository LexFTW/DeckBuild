package deck_build.implementations.exists;

import org.xmldb.api.base.Database; 

public class IExists {

	private String driver;
	private String uri;
	private String resource;
	private Class c1;
	private Database database;
	private static IExists exists;
	
	private IExists() {
		this.driver = "org.exist.xmldb.DatabaseImpl";
		this.uri = "xmldb:exist://localhost:8585/exist/xmlrpc/db/";
		this.resource = "cards.xml";
		try {
			this.c1 = Class.forName(driver);
			this.database = (Database) c1.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
}
