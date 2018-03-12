package General;

import java.util.ArrayList;
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
	
	public static <T> String listToString(List<T> list)
	{
		String data = new String();
		
		for(int i = 0; i < list.size(); i++)
		{
			data = data + list.get(i).toString() + '\n';
		}
		
		return data;
	}
	
	public static int[] extractInts(String text)
	{
		ArrayList<String> temp = new ArrayList<String>();
				
		for(int i = 0; i < text.length(); i++)
		{
			if(Character.isDigit(text.charAt(i)))
			{
				String number = new String();
				
				
				
				for(int j = i; j < text.length(); j++)
				{
					if(Character.isDigit(text.charAt(j)))
					{
						System.out.println(text.charAt(j));
						number = number + text.charAt(j);
					}
					else
					{
						temp.add(number);
						i = j;
						break;
					}
				}
				
			}
		}
		
		for(int i = 0; i < temp.size(); i++)
		{
			System.out.println(temp.get(i));
		}
		
		int[] ret = new int[temp.size()];
		
		for(int i = 0; i < temp.size(); i++)
		{
			System.out.println(temp.get(i));
			
			ret[i] = Integer.parseInt(temp.get(i));
		}
		
		return ret;
	}
	
	public static String intMatrixToString(int[][] matrix)
	{
		String ret = new String();
		
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[i].length; j++)
			{
				ret = ret + matrix[i][j] + ' ';
			}
			
			ret = ret + '\n';
		}
		
		return ret;
	}
}
