package item;

import interfaces.Item;

public class ItemImpl implements Item {

	private String id;
	private int itemPrice;
	
	public ItemImpl(String id,int itemPrice)
	{
		this.id=id;
	this.itemPrice=itemPrice;
	}
	
	public void printItemsList() 
	{
		System.out.println("Item Id->> "+id);
		System.out.println("Item Price->> "+itemPrice);
		System.out.println("======================================");
	}

	public String getId() 
	{
	return id;	
	}

	public int getitemPrice() {
		return itemPrice;
	}

	

}
