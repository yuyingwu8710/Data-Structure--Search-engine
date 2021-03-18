package google;
import java.util.HashMap;

public class MyHashMap<String,K> extends HashMap<String,String> 
{
	public String put(String key, String value)
	{ 
		String newK = key; 
		if (containsKey(key))
		{
			String oldK = key; 
			newK = (String) (oldK + "---" + newK);
		} 
		return super.put(newK, value);
	}
	
	

}
