package interfaces;
import java.util.HashMap;

public interface Order {

public HashMap<String,Integer> getItemslist();

public void print();

public String getDestination();

public String getId();

public int getOrderTime();

}
