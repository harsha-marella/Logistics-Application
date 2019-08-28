
package facilitynetwork;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import interfaces.Facility;

public class FacilityNetworkLoader {

	private static Facility currentfacility; 
	public static ArrayList<Facility> loadfacilitynetwork() {
				
		ArrayList<Facility> Facilities = new ArrayList<Facility>();
		
		try {
	        String fileName = "FacilityNetwork.xml";

	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();

	        File xml = new File(fileName);
	        if (!xml.exists()) 
	          {
	          System.err.println("**** XML File '" + fileName + "' cannot be found");
	          System.exit(-1);
	          }
	            
	           Document doc = db.parse(xml);
	           doc.getDocumentElement().normalize();

	           NodeList FacilityList = doc.getElementsByTagName("Facility");
	            
	           for (int i = 0; i < FacilityList.getLength(); i++) 
	           {
	                Node FacilityLocation = FacilityList.item(i);
	               
	                HashMap<String,Integer> LinksToFacilities = new HashMap<String,Integer>();
	                
	                if (FacilityLocation.getNodeType() == Node.ELEMENT_NODE) 
	                {

	                    Element element = (Element) FacilityLocation;

	                    String facilityLocation = element.getAttribute("Location");
	           
	                    String facilityRate = element.getElementsByTagName("Rate").item(0).getTextContent();
	                    int FacilityRate = Integer.parseInt(facilityRate);
	                    
	                    String facilityCostPerDay = element.getElementsByTagName("Cost").item(0).getTextContent();
	                    int FacilityCostPerDay = Integer.parseInt(facilityCostPerDay);
	                 
	                    NodeList facilityLinks = element.getElementsByTagName("Route_to");

	                    for (int k = 0; k < facilityLinks.getLength(); k++) 
	                    {
	                    	 Node LinksLocation = facilityLinks.item(k);
	               
	                    if (LinksLocation.getNodeType() == Node.ELEMENT_NODE) 
	                      {

	                        Element linkElement = (Element) facilityLinks.item(k);

	                         String RouteToLocation = linkElement.getAttribute("Location");
	                         String RouteDistance = linkElement.getElementsByTagName("distance").item(0).getTextContent();
	                         	// converting string distance to numeric type 
	                        int RouteDistanceTo = Integer.parseInt(RouteDistance);
                          
	                        LinksToFacilities.put(RouteToLocation,RouteDistanceTo);
	                        
	                      }
	                         	
	                     }
	             
	                    currentfacility=Facilityfactory.getinstance(facilityLocation,FacilityRate,FacilityCostPerDay,LinksToFacilities);;
	                 
	                    Facilities.add(currentfacility);
	                    
	                 } 
	                
	              }
	            
	            }
	         
      	         catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
	            e.printStackTrace();
	        }
	        return Facilities;
	    }
	}
			




