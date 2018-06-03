package General;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * 
 * Clase con métodos de utilidad.
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */
public class Functions 
{
	/**
	 * La clase no se puede instanciar.
	 */
	private Functions() {};
	
	/**
	 * 
	 * @param text Texto.
	 * @param search String a buscar dentro del texto.
	 * @return Cuantas vecesse ha encontrado search dentro de text.
	 */
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
	/**
	 * Convierte una lista a String.
	 * 
	 * @param list Lista a ser convertida.
	 * @return String a partir de la lista.
	 */
	public static <T> String listToString(List<T> list)
	{
		String data = new String();
		
		for(int i = 0; i < list.size(); i++)
		{
			data = data + list.get(i).toString() + '\n';
		}
		
		return data;
	}
	
	/**
	 * Extrae todos digitos de un string. Sin necesidad de que el String sea un número.
	 * 
	 * @param text String del cual se extraen los digitos.
	 * @return Array de digitos extraidos.
	 */
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
	
	/**
	 * Convierte una matriz de ints a String.
	 * 
	 * @param matrix Matriz a ser convertida.
	 * @return String que representa visualmente la matriz.
	 */
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
	
	/**
	 * Crea una lista de las palabras que contiene un String.
	 * @param data String a procesar.
	 * @return ArrayList de palabras.
	 */
	public static ArrayList<String> parseList(String data)
	{
		ArrayList<String> ret = new ArrayList<>();
		
		Scanner reader = new Scanner(data);
		
		while(reader.hasNext())
		{
			ret.add(reader.next());
		}
		
		reader.close();
		
		return ret;
	}
}
