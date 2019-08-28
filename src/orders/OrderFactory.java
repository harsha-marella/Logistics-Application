package orders;
import java.util.HashMap;

import interfaces.Order;

public class OrderFactory {
	public static Order getinstance(String destination,int orderTime,String orderId,HashMap<String,Integer>itemsList)
	  {
		return new OrderImpl(destination,orderTime,orderId,itemsList);
	   }
}
