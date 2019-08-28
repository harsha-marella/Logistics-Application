package orders;

import java.util.ArrayList;

import interfaces.Order;
import project.MediatorManager;

public class OrderManager {
	
	private ArrayList<Order> ordersList= new ArrayList<Order>();
	public int x=5;
	private static OrderManager manager;
   
	
    
    public void processOrders()
    {
    	while(ordersList.size()>0)
    	{
    		Order o=ordersList.get(0);
    		ordersList.remove(o);
    	
			OrderProcessor.getInstance().orderProcessing(o);
	
    	}
    }
    
    public boolean checkvalidity(String itemId)
    {
    	return	MediatorManager.getInstance().checkvalidity(itemId);
    }
	
    public ArrayList<String> getitemFacilities(String itemId)
	{
		return MediatorManager.getInstance().getitemFacilities(itemId);
	} 
    
    public  void print()
    {
    	
    	for(Order present:ordersList)
    	{
    		present.print();
    	}
    }
    
    public static OrderManager getInstance()
    {
		if(manager == null)
		{
			manager = new OrderManager();
		
		}
		return manager;
	}
    
    public OrderManager()
    {
    	System.out.println("In the order Manager");
    	ordersList=new OrderLoader().getOrders();
    	
    }
    
    public int getItemQuant(String presentOne,String itemId)
    {
    	return MediatorManager.getInstance().getItemQuant(presentOne,itemId);
    }
    
    public int getProcessEndDay(String location,int itemQuant)
	{
		return MediatorManager.getInstance().getProcessEndDay(location, itemQuant);
	}
    
    public double shortestPath(String start,String end)
    {
     return MediatorManager.getInstance().shortestPath(start,end);     	
    }
    
    public void updateInventory(String location,String itemId,int quantity)
    {
    	MediatorManager.getInstance().updateInventory(location,itemId,quantity);
    }

	public void updateSchedule(String location, String processEndDay,String itemQuant) {
		
		MediatorManager.getInstance().updateSchedule(location,processEndDay,itemQuant);
	    	
	}

	public int getitemsCost(String itemId) {
		return MediatorManager.getInstance().getitemsCost(itemId);
	}

	
}
