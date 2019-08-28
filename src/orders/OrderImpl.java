package orders;
import java.util.HashMap;
import java.util.Map;

import interfaces.Order;

public class OrderImpl implements Order {
private String location;
private HashMap<String,Integer> itemsList;
private int orderTime;
private String orderId;

  public OrderImpl(String location,int orderTime,String orderId,HashMap<String,Integer> itemsList)
   {
	this.orderId=orderId;
	this.orderTime=orderTime;
	this.location=location;
	this.itemsList=itemsList; 
   }

  public HashMap<String,Integer> getItemslist() 
   {
	return itemsList;

   }
  
  public String getDestination()
  {
	return location;  
  }
  
  public void print()
   {
	System.out.println("Location :"+location);
	System.out.println("orderTime :"+orderTime);
	System.out.println("orderId :"+orderId);
	for(Map.Entry<String,Integer> current:itemsList.entrySet())
	{
		System.out.println("item :"+current.getKey());
		System.out.println("quantity :"+ current.getValue());
		System.out.println("                   ");
		
	}
	System.out.println("=======================");
   }

public String getId() {
	return orderId;
}

public int getOrderTime() {
	return orderTime;
}
 
}
