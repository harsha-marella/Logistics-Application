package orders;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import exception.InvalidCostException;
import exception.InvalidDaysException;
import exception.InvalidItemException;
import exception.InvalidQuantException;
import interfaces.Order;

public class OrderProcessor {

	private static OrderProcessor orderProcessor;
	private OrderManager orderMgr;
	private  int orderCount=1;
	private HashSet<HashMap<String,String>>itemSolutionList=new HashSet<HashMap<String,String>>();
	
	public static OrderProcessor getInstance()
	{
		if(orderProcessor == null)
		{
		orderProcessor = new OrderProcessor();
		}
	    return orderProcessor;
	}

	public void orderProcessing(Order order)
	{
	    orderMgr = OrderManager.getInstance();
		try
		{    
		System.out.println("\nOrder #"+orderCount++);
		System.out.println("  Order Id:       "+order.getId());
		System.out.println("  Order Time:     "+order.getOrderTime());
		System.out.println("  Destination:    "+order.getDestination());
		System.out.println("\n\n  List of Order Items:");
		
		
		int num = 1;
		
		for(Map.Entry<String,Integer> current: order.getItemslist().entrySet())
		  {
			System.out.println("       "+num+")Item ID:    "+current.getKey()+",     Quantity: "+current.getValue());
		    num++;
		  }
		System.out.println("\n  Processing Solution:\n");
		
		for(Map.Entry<String,Integer> current: order.getItemslist().entrySet())
		  {
			itemSolutionList.clear();   
			String itemId = current.getKey();
	
		
	
			if(orderMgr.checkvalidity(itemId))
		           {
		        	 	 
		      		int orderQuant = current.getValue();
	
		      		if(orderQuant <= 0)
		      		{
		      			throw new InvalidQuantException("Invalid orderQuant: "+orderQuant);
		      		}
		      			
		      		
		      		
		      		while(orderQuant > 0)
		      		{
		      		ArrayList <HashMap<String,String>> facilityRecordList = new ArrayList<HashMap<String,String>>();
				    
				     ArrayList<String> facilities = OrderManager.getInstance().getitemFacilities(itemId);
				     facilities.remove(order.getDestination());
				    
				    
				     
							     
				      if(facilities != null)
					     {
				    	  
				    	   for(String presentOne: facilities)
					        {
					    	  HashMap<String,String>facilityRecord=new HashMap<String,String>();
					    	  
						      int quantityNeeded = 0;
						      
					    	  int itemQuant = orderMgr.getItemQuant(presentOne,itemId);
		                    
					    	  if(itemQuant <= 0){
					    		  throw new InvalidQuantException("Invalid itemQuant: "+itemQuant);
					    		  }		 
						    	
					    	  quantityNeeded = (orderQuant<itemQuant)? orderQuant:itemQuant; 
						     
						      double travellingDays = orderMgr.shortestPath(presentOne,order.getDestination());
						      int processEndDay = orderMgr.getProcessEndDay(presentOne, quantityNeeded);
						      int arrivalDay = (int)(travellingDays+processEndDay+0.99);
						      
						      if((travellingDays <= 0.0) || ( processEndDay <= 0) || (arrivalDay <= 0))
						      { 
						    	  throw new InvalidDaysException("Invalid Days found \n" +
						    			  								" travellingDays: "+travellingDays+
						    			  								"\nprocessEndDay: "+processEndDay+
						    			  								"\narrivalDay: \n"+arrivalDay);
						      }
							
						      
						      
						      facilityRecord.put("itemId", itemId);  
						       facilityRecord.put("Name",presentOne);
							   facilityRecord.put("No.ofitems",Integer.toString(quantityNeeded));
							   facilityRecord.put("PED",Integer.toString(processEndDay));
							   facilityRecord.put("TravelTime",Double.toString(travellingDays));
							   facilityRecord.put("ArrivalDay",Integer.toString(arrivalDay));
						       facilityRecordList.add(facilityRecord);
					        }
					       
					       
					       
					     }
				      HashMap<String,String> finalisedFacility = null;
				     
				     
				 	  if(facilityRecordList.size() == 1)
				      
				      {
				    	finalisedFacility=facilityRecordList.get(0);  
				      }
				      else{
				    	  
				      for(int i = 1;i < facilityRecordList.size();i++)   
				      {
				    	  int a=Integer.parseInt(facilityRecordList.get(i-1).get("ArrivalDay"));
				    	  int b=Integer.parseInt(facilityRecordList.get(i).get("ArrivalDay"));
				    	  finalisedFacility = (a < b) ? facilityRecordList.get(i-1):facilityRecordList.get(i);
				      }
				      }
				      
				  
				      orderQuant -= Integer.parseInt(finalisedFacility.get("No.ofitems"));
				      processTop(finalisedFacility,itemId);
		      	}
		      		
			 }// if for item validity
			else{throw new InvalidItemException("Invalid Item with Id: "+itemId);}
			int totalitemsCost=0;
            int totalQnty=0;
            int min=100;
            int max=0;
            for(HashMap<String,String> e:itemSolutionList)
            {
            	
            	int no=Integer.parseInt(e.get("No.ofitems"));
            	totalQnty+=no;
            	int cst=orderMgr.getitemsCost(e.get("itemId"));
            			if(cst <= 0)
		            	{
		            		throw new InvalidCostException("Invalid cost for item: "+e.get("itemId")+" Cost: "+cst);
		            	}
            	totalitemsCost+=no*cst;
            }
            System.out.println("  "+itemId+":");
 			System.out.println("           Facility             Quantity         Cost               ArrivalDay");
            int k=1;
            
            
       		for(HashMap<String,String> e:itemSolutionList)
       		{
       		
       			int quanty=Integer.parseInt(e.get("No.ofitems"))*orderMgr.getitemsCost(e.get("itemId"));
       			int o=22;
       		    System.out.print("       "+k+") "+ e.get("Name"));
       			int length=e.get("Name").length();
           		
       			String f="";
       			o=22-length;	
       			for(int i=0;i<o;i++){f+=" ";}
       		
       			System.out.print(f+e.get("No.ofitems"));
       			
       			length=e.get("No.ofitems").length();
       			f="";
       			o=17-length;
       			DecimalFormat format = new DecimalFormat("0.00"); 
       	        String qnty=format.format(quanty);
       			for(int i=0;i<o;i++){f+=" ";}
       			System.out.print(f+"$"+qnty);
       			
       			length=qnty.length();
       			f="";
       			o=18-length;
       			for(int i=0;i<o;i++){f+=" ";}
       			int day=Integer.parseInt(e.get("ArrivalDay"));
       			min=(min<day)?min:day;
       			max=(max>day)?max:day;
       			System.out.print(f+day);
       			System.out.println();
       			k++;
       		}
       		DecimalFormat format = new DecimalFormat("0.00"); 
   	        String totalprice=format.format(totalitemsCost);
   			
  
       		System.out.println("           TOTAL                "+totalQnty+"              $"+totalprice+"         [ "+min+" - "+max+" ]");
       		System.out.println();  }//for loop getitemslist	
		}  
		catch(InvalidItemException | InvalidCostException | InvalidDaysException |InvalidQuantException  e)
		{
			e.printStackTrace();
		}   
      }//function ending

	private void processTop(HashMap<String, String> finalisedFacility,String itemId) 
	{
		orderMgr.updateInventory(finalisedFacility.get("Name"), itemId,Integer.parseInt(finalisedFacility.get("No.ofitems")));
		orderMgr.updateSchedule(finalisedFacility.get("Name"),finalisedFacility.get("PED"),finalisedFacility.get("No.ofitems"));
		itemSolutionList.add(finalisedFacility);
	}
}//class
	