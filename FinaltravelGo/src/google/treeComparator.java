package google;
import java.util.Comparator;

public class treeComparator implements Comparator<WebTree>
{
	@Override
	public int compare(WebTree o1, WebTree o2)
	{		
		if(o1.root.nodeScore>o2.root.nodeScore)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
}