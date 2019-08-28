package interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface Facility {

	//public void setFacilitydata(String facilityname,int facilityRate, int facilityCostPerDay, HashMap<String, Integer> linksToFacilities);
	public int getLinkDistance(String name);
    public String getFacilityName();
	//public void setInventoryData(HashMap<String,Integer> itemInfo);
    public void printReport();
    public void printSchedule();
    public ArrayList<String> getNeighbours();
    public int getProcessEndDay(int itemQuant);
	public void setItemData(HashMap<String, Integer> itemDescription);
	public int getItemQuant(String itemId);
	public void updateInventory(String itemId, int quantity);
	public boolean containsItemId(String itemId);
	public void updateSchedule(String processEndDay,String itemQuant);

}
