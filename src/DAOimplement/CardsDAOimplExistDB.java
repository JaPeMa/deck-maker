package DAOimplement;

import java.util.ArrayList;
import java.util.List;

import org.exist.xmldb.XQueryService;

import connections.ExistDBConnection;
import iDAO.CardDAO;
import models.Card;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;

public class CardsDAOimplExistDB implements CardDAO {

	@Override
	public ArrayList<Card> getCards() {

		ArrayList<Card> cards = new ArrayList<Card>();
		XQueryService service;
		
		try {
			service = ExistDBConnection.connectExistDb();
		
	        String xQuery = "for $x in doc('" + ExistDBConnection.getResourceName() + "')//card " 
	                + "return data(("
	                + "$x/[@id],"
	                + "$x/name,"
	                + "$x/summonCost,"
	                + "$x/attack,"
	                + "$x/defense,"
	                + "$x/value))"; 
	        
	     // Execute the query, print the result 
	        ResourceSet result = service.query(xQuery); 
	        ResourceIterator i = result.getIterator(); 
	        while (i.hasMoreResources()) {
	        	String card = "";
	        	for (int j = 0; j < 6; j++) {
	        		 Resource r = i.nextResource();
	        		 card = card + r.getContent().toString() + "/";
				}
	
	        	String[] cardString = card.split("/");
	        	
	        	Card cardToAdd = new Card();
	        	cardToAdd.setId(Integer.parseInt(cardString[0]));
	        	cardToAdd.setName(cardString[1]);
	        	cardToAdd.setSummonCost(Integer.parseInt(cardString[2]));
	        	cardToAdd.setAttack(Integer.parseInt(cardString[3]));
	        	cardToAdd.setDefense(Integer.parseInt(cardString[4]));
	        	cardToAdd.setValue(Integer.parseInt(cardString[5]));
	        	cards.add(cardToAdd);
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cards;
	}

}
