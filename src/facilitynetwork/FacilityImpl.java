
package facilitynetwork;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import facilityinventory.FacilityInventory;
import interfaces.Facility;
import project.FacilitySchedule;

public class FacilityImpl implements Facility{
	
	private FacilityInventory facilityInventory=new FacilityInventory(); 
	private FacilitySchedule schedule=new FacilitySchedule();
	private String facilityName;
	private int ratePerDay;
	private int costPerDay;
	private ArrayList<String> linksList=new ArrayList<String>();
	private HashMap<String,Integer>linksInfo=new HashMap<String,Integer>();
	
		
	
	
	public FacilityImpl(String facilityLocation, int facilityRate, int facilityCostPerDay,	HashMap<String, Integer> linkstoFacilities) 
	{
		  
		this.ratePerDay=facilityRate;
		this.facilityName=facilityLocation;
		this.costPerDay=facilityCostPerDay;
		this.linksInfo=linkstoFacilities;
		for(Map.Entry<String,Integer> current:linksInfo.entrySet())
		   {
		    linksList.add(current.getKey());
		   }
		    schedule.setScheduleMap(ratePerDay);  
	}

	public ArrayList<String> getNeighbours()
	{
	return linksList;	
	}
	
    	
    	
    public int getLinkDistance(String name)
    	{
    		int distance=0;
    		
    		for(Map.Entry<String,Integer> current:linksInfo.entrySet())
    		{
    			if(current.getKey().equals(name))
    			{
    				distance=current.getValue();
    				break;
    			}
    		}
    		return distance;
    	}

	public String getFacilityName() {
	
			return facilityName;
		}
		
	public void printReport() {
			System.out.println("======================================================================================");
			System.out.println(facilityName);
			for(int i=0;i<facilityName.length();i++)
			{
				System.out.print("-");
			}
			System.out.println("\nRate per Day: "+ ratePerDay);
			System.out.println("Cost per Day: "+ costPerDay);
			System.out.println("\nDirect Links:");
			
			for(Map.Entry<String,Integer> current:linksInfo.entrySet())
			{
				double distance=current.getValue();
				distance = (double)distance/400;
				distance=Math.round(distance*100.0)/100.0;	
				System.out.print(" "+current.getKey()+" ("+distance+"d);");
			}
			

		    
	 }
	
	public void printSchedule()
	{
		System.out.println("facility: "+facilityName);
		System.out.println("rate: "+ratePerDay);
		schedule.printOutput();
	}

	
	public int getProcessEndDay(int itemQuant) 
	{
		
		return schedule.getProcessEndDay(itemQuant);
		 
	}


	public void setItemData(HashMap<String, Integer> itemDescription) {
		
		facilityInventory.setItemData(itemDescription);
	}

	public int getItemQuant(String itemId) 
	{
		return facilityInventory.getItemQuant(itemId);	
	}


	public void updateInventory(String itemId, int quantity) 
	{	
		facilityInventory.updateInventory(itemId, quantity);			
	}

	
	public boolean containsItemId(String itemId) {
		
		return facilityInventory.containsItemId(itemId);
	}

	public void updateSchedule(String processEndDay,String itemQuant) {
		schedule.updateSchedule(processEndDay,itemQuant);
	}

}
 

