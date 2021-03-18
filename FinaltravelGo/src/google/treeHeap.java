package google;
import java.util.PriorityQueue;

public class treeHeap 
{
	public PriorityQueue<WebTree> heap;
	
	public treeHeap(int capacity)
	{
		this.heap = new PriorityQueue<WebTree>(capacity, new treeComparator());
	}
	
	public void add(WebTree k)
	{
		heap.offer(k);
		System.out.println("Done");
	}
	
	public void peek()
	{
		if(heap.peek() == null)
		{
			System.out.println("InvalidOperation");
			return;
		}
		
		WebTree k = heap.peek();		
		System.out.println(k.toString());
	}
	
	public WebTree removeMax()
	{
		System.out.println(heap.peek().root.webPage.name);
		return heap.poll();
	}
	
	public void output()
	{
		StringBuilder sb = new StringBuilder();
		WebTree k;
		
		while((k = heap.poll()) != null)
		{
			sb.append(k.root.webPage.name);
			if(heap.peek() != null) 
				sb.append("");
		}
		
		System.out.println(sb.toString());	
	}
}