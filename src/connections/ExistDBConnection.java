package connections;

import org.exist.xmldb.XQueryService;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;

public class ExistDBConnection {
	
	protected static String DRIVER = "org.exist.xmldb.DatabaseImpl"; 
    protected static String URI = "xmldb:exist://localhost:8127/exist/xmlrpc"; 
    protected static String collectionPath = "/db/db/Cards"; 
    protected static String resourceName = "Cards.xml";
    
    public static XQueryService connectExistDb() throws Exception {
		
		// initialize database driver 
        Class cl = Class.forName(DRIVER); 
        Database database = (Database) cl.newInstance(); 
        DatabaseManager.registerDatabase(database); 
        // get the collection 
        Collection col = DatabaseManager.getCollection(URI + collectionPath);
        // Instantiate a XQuery service 
        XQueryService service = (XQueryService) col.getService("XQueryService", 
                        "1.0"); 
        service.setProperty("indent", "yes"); 
        
        return service;		
	}

	public static String getResourceName() {
		return resourceName;
	}
}
