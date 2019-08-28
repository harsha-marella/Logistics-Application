package facilitynetwork;
import java.util.ArrayList;

import exception.InvalidFacilityException;
import facilityinventory.FacilityInventoryReader;
import interfaces.Facility;
import shortestpath.ShortestPathAlgorithm;

public class FacilityManager {
	
	
	private static FacilityManager facilityMgr;
	private ArrayList<Facility> facilityList=new ArrayList<Facility>();
	private ArrayList<String> facilityNames=new ArrayList<String>();
	
	public void getFacilityNames()
	{
		  for(Facility current:facilityList)
		    {
			facilityNames.add(current.getFacilityName());
		    }	
	}
	
	public void updateInventory(String location,String itemId,int quantity)
	{
        try{
        	if (!(facilityNames.contains(location)))
        		{
        			throw new InvalidFacilityException("Invalid Facility Name: "+location);
        		}
        	}
        catch(InvalidFacilityException e)
        	{
        		e.printStackTrace();
        	}
	    
        Facility fclty = null;
		for(Facility facility:facilityList)
		{
			if(facility.getFacilityName().equals(location))//throw new InvalidFacilityException(location);
			{
		        fclty=facility;
		        break;
			}
			
		}
		fclty.updateInventory(itemId, quantity);
	}
	
	public FacilityManager()
	{
		
		facilityList=FacilityNetworkLoader.loadfacilitynetwork();
		getFacilityNames();
		
		FacilityInventoryReader.InventoryReader();
		
	}
	
	public boolean contains(String start,String end)
	{
		if(facilityNames.contains(start)&&facilityNames.contains(end))
		{
			return true;
		}
		else
		{
			return false;
		}
	} 
	
	public static FacilityManager getInstance()
	{
		if(facilityMgr==null)
		{
		facilityMgr=new FacilityManager();
		
		}
		return facilityMgr;
	}
   
	
	public ArrayList<String> getNeighbours(String name)
    {
		try{
		if(!(facilityNames.contains(name)))
		{
			throw new InvalidFacilityException("Invalid Facility Name "+name);
		}
		else{
	
	   ArrayList<String>linksList = new ArrayList<String>();
	   for(Facility current:facilityList)
	   {
		   if(current.getFacilityName().equals(name))
		   {
			   linksList=current.getNeighbours();
			   
		   }
	   }
	  return linksList;
		}
		}
		catch (InvalidFacilityException e)
			{
				e.printStackTrace();
				return null;
				
			}
	}
  
	 public int calculateLinkDistance(String start, String end) 
	   {
		   try{
				if(!(facilityNames.contains(start) && facilityNames.contains(end)))
				{
					throw new InvalidFacilityException("Invalid Facility Name : "+ start +" "+ end);
				}
				else{
						int distance=0;
						for(Facility current:facilityList)
						{
			 
							if(current.getFacilityName().equals(start))
							{
								distance=current.getLinkDistance(end);
								break;
							}
						}
						return  distance;
				}
		   }
		   catch (InvalidFacilityException e)
			{
				e.printStackTrace();
				return 0;
			}
	   }
   public int getProcessEndDay(String location,int itemQuant)
   {
	    try{
        	if (!(facilityNames.contains(location)))
        		{
        			throw new InvalidFacilityException("Invalid Facility Name: "+location);
        		}
        	}
	    catch(InvalidFacilityException e)
	    	{
	    		e.printStackTrace();
	    	}
	
	   Facility facility=null;
	   for(Facility current:facilityList)
	   {
		 
		   if(current.getFacilityName().equals(location))
		   {
			   facility=current;
			   break;
		   }
	   
	
        }   
	   
	   return facility.getProcessEndDay(itemQuant);
   }

   public double shortestPath(String start, String end) 
    {
	    try{
        	if (!(facilityNames.contains(start))&&(facilityNames.contains(end)))
        		{
        			throw new InvalidFacilityException("Invalid Facility Name: "+start+end);
        		}
        	}
	    catch(InvalidFacilityException e)
	    	{
	    		e.printStackTrace();
	    	}
	
		return ShortestPathAlgorithm.getInstance().findBestPath(start,end);
	}


   public int getItemQuant(String location, String itemId) 
   {
	   Facility facility=null;
	   for(Facility current:facilityList)
	   {
		 
		   if(current.getFacilityName().equals(location))
		   {
			   facility=current;
			   break;
		   }
	
        }   
	   return facility.getItemQuant(itemId);   
   }


	public ArrayList<String> getitemFacilities(String itemId)
	{
		ArrayList<String>facilities=new ArrayList<String>();
		for(Facility current:facilityList)
		{
			if((current.containsItemId(itemId))&&(current.getItemQuant(itemId)>0))
			{
				
				facilities.add(current.getFacilityName());
				
			}
		}
		return facilities;
	}

	public void updateSchedule(String location, String processEndDay,String itemQuant) 
	{
	
		Facility facility=null;
		   for(Facility current:facilityList)
		   {
			 
			   if(current.getFacilityName().equals(location))
			   {
				   facility=current;
				   break;
			   }
		
	        }   
		
	  facility.updateSchedule(processEndDay,itemQuant);
	}

}
