package orders;
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

import interfaces.Order;


public class OrderLoader {
	
	public ArrayList<Order> getOrders (){	
	
		ArrayList<Order> orders = new ArrayList<Order>();
	try {
        String fileName = "OrdersSubmition.xml";

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
           
           NodeList nodeList = doc.getElementsByTagName("Order");
           
           	for (int i = 0; i < nodeList.getLength(); i++) {
               Node node = nodeList.item(i);

               if (node.getNodeType() == Node.ELEMENT_NODE) {

                   Element element = (Element) node;

                   String orderId = element.getAttribute("Id");
                   String destination = element.getElementsByTagName("Destination").item(0).getTextContent();
                   String orderTimeTo = element.getElementsByTagName("Order_Time").item(0).getTextContent();
                   Integer orderTime = Integer.parseInt(orderTimeTo);

                    HashMap<String,Integer>itemsList=new HashMap<String,Integer>();
                    
                    NodeList orderItems = element.getElementsByTagName("Item");

                    for (int j = 0; j < orderItems.getLength(); j++) {

                        Element itemElement = (Element) orderItems.item(j);

                        String itemId = itemElement.getAttribute("Id");
                        String itemQuantity = itemElement.getElementsByTagName("Quantity").item(0).getTextContent();
                        Integer itemQuant = Integer.parseInt(itemQuantity);
                         itemsList.put(itemId, itemQuant);
                    }
                Order presentOne=OrderFactory.getinstance(destination,orderTime,orderId,itemsList);  
                orders.add(presentOne);
               }
           	}
	}
            catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
	            e.printStackTrace();
            }
	
	return orders;
	}

           
           
           
           
           
           
           
}

