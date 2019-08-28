package facilityinventory;

import java.io.File;

import java.io.IOException;
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

import exception.InvalidFacilityException;
import facilitynetwork.Facilityfactory;
import interfaces.Facility;

public class FacilityInventoryReader {

	//private static HashMap<String,Integer> itemsList=new ArrayList<FacilityInventory>();
		
	public static void InventoryReader() {
		     
		try {
            String fileName = "FacilityInventory.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }
            
            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();

            NodeList FacilityList = doc.getElementsByTagName("Facility");
            for (int i = 0; i < FacilityList.getLength(); i++)
            {
                Node node = FacilityList.item(i);
              

                if (node.getNodeType() == Node.ELEMENT_NODE)
                {

                    Element element = (Element) node;

                    String facilityLocation = element.getAttribute("location");
       
                    NodeList facilityItems = element.getElementsByTagName("Item");
                    
                	HashMap<String,Integer> itemDescription=new HashMap<String,Integer>();
                    
                	for (int j = 0; j < facilityItems.getLength(); j++) {
                    	
                       	Element itemElement = (Element) facilityItems.item(j);

                        String itemId = itemElement.getElementsByTagName("ItemId").item(0).getTextContent();
                        String itemQuantity = itemElement.getElementsByTagName("Item_Quantity").item(0).getTextContent();
                        Integer ItemQuantity = Integer.parseInt(itemQuantity);
                    
                        itemDescription.put(itemId,ItemQuantity);
                        }
                	Facility presentOne=Facilityfactory.getinstance(facilityLocation);
                	try{
                    if(presentOne==null)
                    	{
                    		throw new InvalidFacilityException("Invalid Found in the Inventory loader");
                    		
                    	}
                    else
                    	{                	
                    	presentOne.setItemData(itemDescription);
                    	}
                	}
                		catch(InvalidFacilityException e)
    	   				{
                			e.printStackTrace();
    	   			    }
                }
            }
       } 
    	catch (ParserConfigurationException | SAXException | IOException | DOMException  e) {
            e.printStackTrace();
	}
	}
}
