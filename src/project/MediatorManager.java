package project;

import java.util.ArrayList;

import facilitynetwork.FacilityManager;
import item.ItemManager;
import orders.OrderManager;

public class MediatorManager {
	private static MediatorManager mediatorManager;
	private ItemManager itemMgr=ItemManager.getInstance();
	private FacilityManager facilityMgr=FacilityManager.getInstance();
	private OrderManager orderMgr;
	public static MediatorManager getInstance()
    {
		if(mediatorManager==null)
		{
			mediatorManager=new MediatorManager();
		
		}
		return mediatorManager;
	}
   
	public MediatorManager()
	{
		itemMgr=ItemManager.getInstance();
		facilityMgr=FacilityManager.getInstance();
	    orderMgr=OrderManager.getInstance();
	}

	public ArrayList<String> getitemFacilities(String itemId)
	{
		return facilityMgr.getitemFacilities(itemId);
	}
	
	public boolean checkvalidity(String itemId)
	{
		return itemMgr.itemValidity(itemId);
	}
	
	public int getItemQuant(String presentOne,String itemId)
	{
		return facilityMgr.getItemQuant(presentOne,itemId);
	}

	public double shortestPath(String start, String end) 
	{
		return facilityMgr.shortestPath(start,end);
	}
	
	public int getProcessEndDay(String location,int itemQuant)
	{
		return facilityMgr.getProcessEndDay(location, itemQuant);
	}

	public void updateInventory(String location, String itemId,int quantity) {
		
		facilityMgr.updateInventory(location,itemId,quantity);
		
		}
	

	public void updateSchedule(String location, String processEndDay,String itemQuant) {
		
		facilityMgr.updateSchedule(location,processEndDay,itemQuant);
	}

	public int getitemsCost(String itemId) {
		return itemMgr.getitemsCost(itemId);
	}
	
	public void processOrders() 
	{
		orderMgr.processOrders();
	}

}
