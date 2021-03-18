package google;
import java.io.IOException;
import java.util.ArrayList;

public class WebPage
{
	public String url;
	public String name;
	public WordCounter counter;
	public double score;
	
	public WebPage(String url,String name)
	{
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url);	
	}
	
	public void setScore(ArrayList<Keyword> keywords) throws Exception
	{
		score = 0;
		for(Keyword k : keywords)
		{			
			score += counter.countKeyword(k.name)* k.weight;	
		}
	}
	
}