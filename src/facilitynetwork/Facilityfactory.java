package facilitynetwork;

import java.util.HashMap;

import interfaces.Facility;

public class Facilityfactory {
 private static HashMap<String,Facility> facilitiesList=new HashMap<String,Facility>();
   
 public static Facility getinstance(String location) 
    {
	return facilitiesList.get(location);
    }
    
  public static Facility getinstance(String facilityLocation, int FacilityRate,int facilityCostPerDay,HashMap<String,Integer> linksToFacilities)
   {
	 FacilityImpl fclty=new FacilityImpl(facilityLocation,FacilityRate,facilityCostPerDay,linksToFacilities);
     facilitiesList.put(facilityLocation,fclty);
     return fclty;
   }
}
