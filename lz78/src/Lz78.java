import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Compression
{
	private int num;
	private char chr;
	private String text;
	private String y;
	private List<Compression> v = new ArrayList<Compression>();
	private List<String> m = new ArrayList<String>();

	Compression()
	{
        text = "";
	}


    Compression(String text)
    {
    	this.text = text;
	    makeCompression();
    }

    private Compression(int num , char chr)
    {
    	this.num = num;
    	this.chr = chr;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }

    public String getRealText()
    {
    	return text;
	}

    public void makeCompression()
    {	        
    	boolean check = true;
    	for(int count = 0 ; count < text.length() ; count++)
	    {
    		boolean check_2 = false;
	        y = Character.toString(text.charAt(count));
	        for(int j = m.size()-1 ; j >= 0 ; j--)
	        {
	        	if(m.get(j).equals(y))
	        	{
	        		check_2 = true;
	        		num = j+1;
	        		count++;
	        		if(count == text.length())
	        		{
	        			check = false;
	        			chr = text.charAt(text.length()-1);
	        			break;
	                }
	        		y = y + text.charAt(count);
	        		j = m.size();
	        	}
	        }
	        if(check_2 == false || check == false)
	        	num = 0;
	        if(check!=false)
	        	chr = text.charAt(count);
	        if(check == true)
	        		m.add(y);
	        v.add(new Compression(num,chr));
	    }
    }

	public void printDictionary()
    {
		for(int i = 0 ;i < m.size() ; i++)
		{
			System.out.println(i+1 + " " + m.get(i));
	    }     
	}

	void printTages()
    {
		for(int i = 0 ; i < v.size() ; i++)
		{
			System.out.printf("< %d , %c > \n", v.get(i).num , v.get(i).chr );
		}
	}
}

class DeCompression
{
	private int num;
	private String chr;
	private String realWord;
    private List<DeCompression> d = new ArrayList<DeCompression>();
    private char lastChar;
    private List<String> c = new ArrayList<String>();
    
    private DeCompression(int num , String chr)
    {
    	this.num = num;
    	this.chr = chr;
    }
    
    public DeCompression()
    {
    	realWord = "";
    	lastChar =' ';
    }
    
    public String makeDeCompression()
    {
        for(int i = 0 ; i < d.size() ; i++)
        {
            if(d.get(i).num == 0 && i+1 != d.size())
            {
                c.add(d.get(i).chr);
            }
            else if(d.get(i).num != 0)
            {
                c.add(c.get(d.get(i).num-1)+d.get(i).chr);
            }
            else if (d.get(i).num == 0 && i+1 == d.size() )
                lastChar = d.get(i).chr.charAt(0);
        }
        for(int i = 0 ; i <= c.size() ; i++)
        {
            if(i==c.size())
            {
                realWord+=lastChar;
                break;
            }
            realWord+=c.get(i);
        }
        return realWord;
    }

    public void printDictinary()
    {
    	for(int i = 0 ;i < c.size() ; i++)
		{
			System.out.println(i+1 + " " + c.get(i));
	    }
    }

    public void printStringAfterDecomp()
    {
        System.out.println(realWord);
    }
    
    public void enterTheTags()
    {
		Scanner in = new Scanner(System.in);
    	System.out.println("Enter number of tags : ");
    	int x = in.nextInt();
    	for(int i = 0 ; i<x ; i++)
    		{
    			num = in.nextInt();
    			chr = in.next(); 
        		d.add(new DeCompression(num,chr));
    		}	
    }
}

public class Lz78 {
	static Scanner in = new Scanner(System.in);
	public static void main(String args[])
	{
		while(true)
	    {
			System.out.println("Enter the string you want to compress : ");
			String w = in.next();
			Compression c = new Compression(w);
			c.printDictionary();
			c.printTages();
			System.out.println(c.getRealText());

			DeCompression d = new DeCompression();
			d.enterTheTags();
			System.out.println(d.makeDeCompression());
			d.printDictinary();
		}
	}
}
