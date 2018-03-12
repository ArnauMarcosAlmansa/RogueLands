package General;

import java.util.List;

public class Functions 
{
	private Functions() {};
	
	public static int ocurrencies(String text, String search)
	{
		int lastIndex = 0;
		int ocurrencies = 0;
		
		while(lastIndex != -1)
		{

		    lastIndex = text.indexOf(search,lastIndex);

		    if(lastIndex != -1)
		    {
		        ocurrencies++;
		        
		        lastIndex += search.length();
		    }
		}
		
		return ocurrencies;
	}
	
	public static String listToString(List list)
	{
		String data = new String();
		
		for(int i = 0; i < list.size(); i++)
		{
			data = data + list.get(i) + '\n';
		}
		
		return data;
	}
}
