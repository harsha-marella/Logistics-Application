package item;

public class ItemFactory {


	public static ItemImpl getItem(String itemId,int itemsPrice)
	{
		return new ItemImpl(itemId, itemsPrice); 
	}

	
}
