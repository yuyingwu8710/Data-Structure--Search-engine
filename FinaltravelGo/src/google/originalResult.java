package google;
import java.io.IOException;
import java.util.ArrayList;

public class originalResult 
{
	public ArrayList<WebTree> resultTrees;
	public ArrayList<Keyword> lst=new ArrayList<Keyword>();
	private String originalK;
	public originalResult(String[][] s,String ok)
	{
		originalK=ok;
		createKeywordList();
		createEachTree(s);
		setEachTreeScore();
		sortTree();
	}
	
	public void createKeywordList() 
	{
//		lst.add(new Keyword("&#x65C5;&#x904A;",5));//�ȹC
//		lst.add(new Keyword("&#x65C5;&#x884C;",5));//�Ȧ�
//		lst.add(new Keyword("&#x8DDF;&#x5718;",4));//���
//		lst.add(new Keyword("&#x81EA;&#x7531;&#x884C;",4));
//		lst.add(new Keyword("&#x6A5F;&#x7968;",4));
//		lst.add(new Keyword("&#x8A02;&#x623F;",4));
//		lst.add(new Keyword("&#x4F4F;&#x5BBF;",4));
//		lst.add(new Keyword("&#x666F;&#x9EDE;",4));
//		lst.add(new Keyword("&#x4EA4;&#x901A;",4));
//		lst.add(new Keyword("&#x5FC5;&#x8CB7;",4));
//		lst.add(new Keyword("&#x9152;&#x5E97;",3));
//		lst.add(new Keyword("&#x98EF;&#x5E97;",3));
//		lst.add(new Keyword("&#x6C11;&#x5BBF;",3));
//		lst.add(new Keyword("&#x9752;&#x65C5;",3));
//		lst.add(new Keyword("&#x4F34;&#x624B;&#x79AE;",2));
//		lst.add(new Keyword("&#x7C3D;&#x8B49;",2));
//		lst.add(new Keyword("&#x532F;&#x7387;",1));
		lst.add(new Keyword("�ȹC",5));
		lst.add(new Keyword("�Ȧ�",5));
		lst.add(new Keyword("���",4));
		lst.add(new Keyword("�ۥѦ�",4));
		lst.add(new Keyword("����",4));
		lst.add(new Keyword("�q��",4));
		lst.add(new Keyword("��J",4));
		lst.add(new Keyword("���I",4));
		lst.add(new Keyword("��q",4));
		lst.add(new Keyword("���R",4));
		lst.add(new Keyword("����",3));
		lst.add(new Keyword("���J",3));
		lst.add(new Keyword("�C��",3));
		lst.add(new Keyword("���§",2));
		lst.add(new Keyword("ñ��",2));
		lst.add(new Keyword("�ײv",1));
		lst.add(new Keyword("trip",5));
		lst.add(new Keyword("travel",5));
		lst.add(new Keyword("tour",5));
		lst.add(new Keyword("journey",5));
		lst.add(new Keyword("go traveling",4));
		lst.add(new Keyword("group tour",4));
		lst.add(new Keyword("travel with a group",4));
		lst.add(new Keyword("independent travel",4));
		lst.add(new Keyword("travel alone",4));
		lst.add(new Keyword("air ticket",4));
		lst.add(new Keyword("passenger ticket",4));
		lst.add(new Keyword("ticket",4));
		lst.add(new Keyword("booking",4));
		lst.add(new Keyword("check in",2));
		lst.add(new Keyword("accommodation",4));
		lst.add(new Keyword("scenic spot",4));
		lst.add(new Keyword("tourism",4));
		lst.add(new Keyword("transportation",4));
		lst.add(new Keyword("must buy",4));
		lst.add(new Keyword("hotel",3));
		lst.add(new Keyword("homestay",3));
		lst.add(new Keyword("bnb",3));
		lst.add(new Keyword("hostel",3));
		lst.add(new Keyword("gift",2));
		lst.add(new Keyword("souvenir",2));
		lst.add(new Keyword("visa",2));
		lst.add(new Keyword("exchange rate",1));
    }
	
	public void createEachTree(String[][] s)
	{
		
		resultTrees=new ArrayList<WebTree>();
		for(String[] s1:s)
		{	
			if(s1[1]==null)
			{
				continue;
			}
			
			WebTree t= new WebTree(new WebPage(s1[1],s1[0]));			
			resultTrees.add(t);
			try
			{
				t.root.findChildren();
			}
			catch(Exception e)
			{
				
//				e.printStackTrace();
				continue;
			}
		}
		
	}
	
	public void setEachTreeScore()
	{
//		if(!lst.contains(new Keyword(originalK,5)))//�쥻����r���[�v
//		{
//			lst.add(new Keyword(originalK,5));
//		}
		
//		System.out.println("tree��"+resultTrees.size()+"��");
		for(WebTree tree : resultTrees)
		{			
			try
			{
				tree.setPostOrderScore(lst);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			System.out.println("root: "+tree.root.webPage.name+" score: "+tree.root.nodeScore+"\n");
		}
	}
	
	public void sortTree()
	{
		treeHeap th=new treeHeap(resultTrees.size());
		for(WebTree t:resultTrees)
		{
			th.add(t);
		}
		
		
		
		
		resultTrees.clear();
		int size=th.heap.size();
//		System.out.println("!!");
		for(int i=0;i<size;i++)
		{
//			System.out.println("@@");
			resultTrees.add(th.removeMax());
		}
	}
	
	
	
}
