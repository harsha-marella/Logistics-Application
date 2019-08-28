package item;

import java.util.ArrayList;
import java.util.HashSet;

import interfaces.Item;

public class ItemManager {
    private HashSet<String> itemsIdList=new HashSet<String>();
    private ArrayList<Item> itemsList;
	private static ItemManager manager;
	 
	public boolean itemValidity(String itemId)
	 {
		// write a exception
		 if(itemsIdList.contains(itemId))
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	 };
    
	 public static ItemManager getInstance()
    {
		if(manager==null)
		{
			manager=new ItemManager();
		
		}
		return manager;
	}
    
    public ItemManager()
    {
    	itemsList=ItemCatalogReader.loadItems();
    	for(Item presentOne:itemsList)
    	{
    		String name=presentOne.getId();
    		itemsIdList.add(name);
    	}
    	
    }
    public void printItemsList()
    {
		
    	for(Item current:itemsList)
    	{
    		current.printItemsList();
    	}
    }

	public int getitemsCost(String itemId) {
	    
	
		Item item=null;
		for(Item i:itemsList)
		{
			if(i.getId().equals(itemId))
			{
				item=i;
				break;
			}
		}
		return item.getitemPrice();
	}
}
