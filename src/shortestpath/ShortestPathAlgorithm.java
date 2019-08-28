
package shortestpath;

import java.util.*;
import java.util.HashMap;
import java.util.HashSet;

import exception.InvalidDistanceException;
import exception.InvalidFacilityException;
import exception.NoNeighboursException;
import facilitynetwork.FacilityManager;

public class ShortestPathAlgorithm {

  private  HashMap<String,Integer> pairs = new HashMap<String,Integer>();
  private  HashSet<String> seen = new HashSet<String>();
  private  ArrayList<String> lowPath = new ArrayList<String>();
  private  int distance;
  private static ShortestPathAlgorithm shortestPath;  
    
  public static ShortestPathAlgorithm getInstance()
	{
		if(shortestPath == null)
		{
			shortestPath = new ShortestPathAlgorithm();
		
		}
		return shortestPath;
	}
    
   public double findBestPath(String start,String end)
     {
	   double travellingDays;
	   lowPath.clear();    	
   
	   try
	   {
    	
		   if(FacilityManager.getInstance().contains(start,end))
		   {
			   mapPairs(start);
			   ArrayList<String> pathList = new ArrayList<String>();
			   pathList.add(start);
			   findPath(start,end,pathList);
     
			   travellingDays = (double)distance/400;
			   travellingDays=Math.round(travellingDays*100.0)/100.0;         // rounding off a value
   
			   return travellingDays;
		   }
		   	else throw new InvalidFacilityException("Invalid Facility Names are entered");
	   }
	   		catch(InvalidFacilityException e)
	   			{
	   				e.printStackTrace();
	   				return 0;
	   			}
    }
    
    private void mapPairs(String init)
    {
    	seen.add(init);
    	try
    	{
    		ArrayList<String> neighboursList = FacilityManager.getInstance().getNeighbours(init);
    			if(neighboursList == null) 
    				{
    					throw new NoNeighboursException(init);
    				}
	 
	 
    			else
    			{
    				for(String current : neighboursList)
    				{
		
    					String node = init + "-" + current;
    					try {	
    							int distance = FacilityManager.getInstance().calculateLinkDistance(init,current);
    							if(distance<=0)
    								{
    									throw new InvalidDistanceException("the Distance is either Zero or Negative"); 
    								}
    									else
    										{
    											pairs.put(node,distance);
    											if(seen.contains(current) == false)
    												{
    												mapPairs(current);
    												}
    										}
    					}
    						
    					catch(InvalidDistanceException e)
    						{	
        						e.printStackTrace();
        					
    						}
    					
    						}
    				}
    			}
    	
    					catch(NoNeighboursException  e)
    						{
    							e.printStackTrace();
    						}
    			}
	 
    
    private  void findPath(String start,String end,ArrayList<String> pathList)
    {
    
	 if(start.equals(end))
	   {
	
		 if(lowPath.isEmpty())
		  {
		   lowPath=pathList;
			  }
	     else
	      {
				int pathlistlength=0;
			for(int i=0;i<pathList.size()-1;i++)
			{
				
			    String s=pathList.get(i)+"-"+pathList.get(i+1);
				pathlistlength+=pairs.get(s);
			}
			
			int lowpathlength = 0;
			
			for(int i=0;i<lowPath.size()-1;i++)
			{
				String s=lowPath.get(i)+"-"+lowPath.get(i+1);
				lowpathlength+=pairs.get(s);
			}
			
			
			if(pathlistlength<lowpathlength)
			{
				lowPath=pathList;
			    distance=pathlistlength;
			}
			else
			{
				distance=lowpathlength;
			}
			
		 }
	   }
	
	 else
	  {
		HashSet<String> fromHere=new HashSet<String>();
		for(Map.Entry<String,Integer> current:pairs.entrySet())
		{
			int index=current.getKey().indexOf("-");
			String Node=current.getKey().substring(0,index);
			
			if(Node.equals(start))
			{
			fromHere.add(current.getKey());	
			}
		}
		
		   for(String str:fromHere)
		   {
			   
		     int index=str.indexOf("-");
		     String secondNode=str.substring(index+1,str.length());
		       
		     if(!(pathList.contains(secondNode)))
		      {
		    	ArrayList<String>newPath=new ArrayList<String>();
		    	for(String g:pathList)
		    	 {
		    	  newPath.add(g);
		    	 }
		    	newPath.add(secondNode);
		    	findPath(secondNode,end,newPath);
		       }
		   }
	}
			
  }
}


