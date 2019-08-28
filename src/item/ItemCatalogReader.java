package item;

	import java.io.File;
	import java.io.IOException;
	import java.util.ArrayList;
	import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.ParserConfigurationException;

	import org.w3c.dom.DOMException;
	import org.w3c.dom.Document;
	import org.w3c.dom.Element;
	import org.w3c.dom.Node;
	import org.w3c.dom.NodeList;
	import org.xml.sax.SAXException;

import interfaces.Item;

	public class ItemCatalogReader {
		private static ArrayList<Item> itemsList=new ArrayList<Item>();
		public static ArrayList<Item> loadItems() {
			
		
			 try {
		            String fileName = "ItemCatalog.xml";

		            	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		            	DocumentBuilder db = dbf.newDocumentBuilder();

		            	File xml = new File(fileName);
		            		if (!xml.exists()) {
		            			System.err.println("**** XML File '" + fileName + "' cannot be found");
		            			System.exit(-1);
		            		}
		            
		            		Document doc = db.parse(xml);
		            		doc.getDocumentElement().normalize();
		            		NodeList ItemId = doc.getElementsByTagName("Item");
		            		 
		            			for (int i = 0; i < ItemId.getLength(); i++) {
		            				Node node = ItemId.item(i);
		            				if (node.getNodeType() == Node.ELEMENT_NODE) {

		                                Element element = (Element) node;

		                                String itemId = element.getAttribute("Id");
		                                String itemPrice = element.getElementsByTagName("price").item(0).getTextContent();
		                                int itemsPrice = Integer.parseInt(itemPrice);
		                                
		                                Item presentOne=ItemFactory.getItem(itemId, itemsPrice);
		                               // presentOne.setItemData(itemId, itemsPrice);
		                                itemsList.add(presentOne);
		                                
		            				}
		            				
		            				}
		            			return itemsList;
		            			}
		            			catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
		            	            e.printStackTrace();
		            		}
			 
			 return itemsList;

	}

	}


