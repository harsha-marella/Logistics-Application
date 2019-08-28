package project;

import java.util.HashMap;
import java.util.Map;

public class FacilitySchedule{
	
	HashMap<Integer, Integer> scheduleMap = new HashMap<>();
    private int rate;
	public void setScheduleMap(int rate)
	{
		this.rate=rate;
		for(int i=1;i<100;i++)
		{
			scheduleMap.put(i,rate);
		}
	}
	
	
	public int getProcessEndDay(int itemQuant)
	{
		int endDay=0;
		int quantity=0;
		for(Map.Entry<Integer, Integer> current : scheduleMap.entrySet())
		{
			quantity+=current.getValue();
			if(quantity>=itemQuant)
			{
				endDay=current.getKey();
				break;
			}
		}
		return endDay;
	}
	
	
	   
	public void printOutput() 
	 {
	        System.out.println("\nSchedule: ");
	        System.out.print("Day:\t\t");
	        for (Map.Entry<Integer, Integer> entry : scheduleMap.entrySet()) {
	            System.out.print(entry.getKey()+"\t");
	        }
	        System.out.println("");
	        System.out.print("Available:\t");
	        for (Map.Entry<Integer, Integer> entry : scheduleMap.entrySet()) {
	            System.out.print(entry.getValue()+"\t");
	        }
	        System.out.println("");
	        System.out.println("");
	 }

	public void updateSchedule(String processEndDay,String itemQuant) 
	{
		int itemquant=Integer.parseInt(itemQuant);
		int processEndday=Integer.parseInt(processEndDay);
		for(int i=1;i<=processEndday;i++)
		{
		    int quantNeeded=(itemquant<rate) ? itemquant:rate;
			scheduleMap.put(i,rate-quantNeeded);
			itemquant-=rate;
		}
	}
}
