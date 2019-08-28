package facilityinventory;


import java.util.HashMap;
import java.util.Map;



public class FacilityInventory 
{
	private HashMap<String,Integer> itemInfo;
	
	
	public void printReport()
	{
		System.out.println("\n\nActive Inventory:");
		System.out.println("   Item Id           Quantity");
	  for(Map.Entry<String,Integer> current:itemInfo.entrySet())
	    {
	    	int length=current.getKey().length();
	    	String s="                  ";
	    	String d=s.substring(0,s.length()-length);
	        System.out.print("   "+current.getKey());
	    	System.out.println(d+" "+current.getValue());
	    }
	    System.out.println("Depleted (Used-up) Inventory: None");
	    
	    
	}

	public boolean containsItemId(String itemId) 
	{
		boolean state=false;
		for(Map.Entry<String,Integer> current:itemInfo.entrySet())
		{
		if(itemId.equals(current.getKey()))
		{
			state= true;
			break;
		}
		}
		return state;
	}

	public int getItemQuant(String itemId) {
		return itemInfo.get(itemId);
	}


	
	public void updateInventory(String itemId, int quantity) 
	{
		int oldQuant=itemInfo.get(itemId);
		
		
		int a=oldQuant-quantity;
	    itemInfo.put(itemId,a);
	}

	public void setItemData(HashMap<String, Integer> itemDescription) {
		
		this.itemInfo=itemDescription;
	}
	

}
