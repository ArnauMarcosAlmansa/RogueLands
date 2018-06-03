package General;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase para ordenar y desordenar arrays y listas.
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */

public class Sorter 
{
	//bubble method for all primitives and a generic type
	public static void bubble(byte[] array)
	{
		byte aux = 0;
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 1; j < array.length - i; j++)
			{
				if(array[j - 1] > array[j])
				{
					aux = array[j - 1];
					array[j - 1] = array[j];
					array[j] = aux;
				}
			}
		}
	}
	
	public static void bubble(short[] array)
	{
		short aux = 0;
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 1; j < array.length - i; j++)
			{
				if(array[j - 1] > array[j])
				{
					aux = array[j - 1];
					array[j - 1] = array[j];
					array[j] = aux;
				}
			}
		}
	}
	
	public static void bubble(int[] array)
	{
		int aux = 0;
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 1; j < array.length - i; j++)
			{
				if(array[j - 1] > array[j])
				{
					aux = array[j - 1];
					array[j - 1] = array[j];
					array[j] = aux;
				}
			}
		}
	}
	
	public static void bubble(long[] array)
	{
		long aux = 0;
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 1; j < array.length - i; j++)
			{
				if(array[j - 1] > array[j])
				{
					aux = array[j - 1];
					array[j - 1] = array[j];
					array[j] = aux;
				}
			}
		}
	}
	
	public static void bubble(float[] array)
	{
		float aux = 0;
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 1; j < array.length - i; j++)
			{
				if(array[j - 1] > array[j])
				{
					aux = array[j - 1];
					array[j - 1] = array[j];
					array[j] = aux;
				}
			}
		}
	}
	
	public static void bubble(double[] array)
	{
		double aux = 0;
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 1; j < array.length - i; j++)
			{
				if(array[j - 1] > array[j])
				{
					aux = array[j - 1];
					array[j - 1] = array[j];
					array[j] = aux;
				}
			}
		}
	}
	
	public static void bubble(char[] array)
	{
		char aux = 0;
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 1; j < array.length - i; j++)
			{
				if(array[j - 1] > array[j])
				{
					aux = array[j - 1];
					array[j - 1] = array[j];
					array[j] = aux;
				}
			}
		}
	}

	public static void bubble(boolean[] array)
	{
		boolean aux;
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 1; j < array.length - i; j++)
			{
				if(array[j - 1] && !array[j])
				{
					aux = array[j - 1];
					array[j - 1] = array[j];
					array[j] = aux;
				}
			}
		}
	}
	
	public static <T> void bubble(T[] array, Comparator<T> comparator)
	{
		T aux = null;
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 1; j < array.length - i; j++)
			{
				if(comparator.compare(array[j - 1], array[j]) < 0)
				{
					aux = array[j - 1];
					array[j - 1] = array[j];
					array[j] = aux;
				}
			}
		}
	}
	
	
	//bogo method for all primitives and a generic type (and for the LOLs)
	public static void bogo(byte[] array)
	{
		while(!isSorted(array))
		{
			shuffle(array);
		}
	}
	
	public static void bogo(short[] array)
	{
		while(!isSorted(array))
		{
			shuffle(array);
		}
	}
	
	public static void bogo(int[] array)
	{
		while(!isSorted(array))
		{
			shuffle(array);
		}
	}
	
	public static void bogo(long[] array)
	{
		while(!isSorted(array))
		{
			shuffle(array);
		}
	}
	
	public static void bogo(float[] array)
	{
		while(!isSorted(array))
		{
			shuffle(array);
		}
	}
	
	public static void bogo(double[] array)
	{
		while(!isSorted(array))
		{
			shuffle(array);
		}
	}
	
	public static void bogo(char[] array)
	{
		while(!isSorted(array))
		{
			shuffle(array);
		}
	}
	
	public static void bogo(boolean[] array)
	{
		while(!isSorted(array))
		{
			shuffle(array);
		}
	}
	
	public static <T> void bogo(T[] array, Comparator<T> comparator)
	{
		while(!isSorted(array, comparator))
		{
			shuffle(array);
		}
	}
	
	
	//insertion method for all primitives and a generic type
	public static void insertion(byte[] array)
	{				
		byte aux;
		
		for(int i = 1; i < array.length; i++)
		{
			aux = array[i];
			
			int j = i;
			
			while(j > 0 && array[j - 1] > aux)
			{
				array[j] = array[j - 1];
				j--;
			}
			array[j] = aux;
		}
	}
	
	public static void insertion(short[] array)
	{				
		short aux;
		
		for(int i = 1; i < array.length; i++)
		{
			aux = array[i];
			
			int j = i;
			
			while(j > 0 && array[j - 1] > aux)
			{
				array[j] = array[j - 1];
				j--;
			}
			array[j] = aux;
		}
	}
	
	public static void insertion(int[] array)
	{				
		int aux;
		
		for(int i = 1; i < array.length; i++)
		{
			aux = array[i];
			
			int j = i;
			
			while(j > 0 && array[j - 1] > aux)
			{
				array[j] = array[j - 1];
				j--;
			}
			array[j] = aux;
		}
	}
	
	public static void insertion(long[] array)
	{				
		long aux;
		
		for(int i = 1; i < array.length; i++)
		{
			aux = array[i];
			
			int j = i;
			
			while(j > 0 && array[j - 1] > aux)
			{
				array[j] = array[j - 1];
				j--;
			}
			array[j] = aux;
		}
	}
	
	public static void insertion(float[] array)
	{				
		float aux;
		
		for(int i = 1; i < array.length; i++)
		{
			aux = array[i];
			
			int j = i;
			
			while(j > 0 && array[j - 1] > aux)
			{
				array[j] = array[j - 1];
				j--;
			}
			array[j] = aux;
		}
	}
	
	public static void insertion(double[] array)
	{				
		double aux;
		
		for(int i = 1; i < array.length; i++)
		{
			aux = array[i];
			
			int j = i;
			
			while(j > 0 && array[j - 1] > aux)
			{
				array[j] = array[j - 1];
				j--;
			}
			array[j] = aux;
		}
	}
	
	public static void insertion(char[] array)
	{				
		char aux;
		
		for(int i = 1; i < array.length; i++)
		{
			aux = array[i];
			
			int j = i;
			
			while(j > 0 && array[j - 1] > aux)
			{
				array[j] = array[j - 1];
				j--;
			}
			array[j] = aux;
		}
	}
	
	public static void insertion(boolean[] array)
	{				
		boolean aux;
		
		for(int i = 1; i < array.length; i++)
		{
			aux = array[i];
			
			int j = i;
			
			while(j > 0 && array[j - 1] && !aux)
			{
				array[j] = array[j - 1];
				j--;
			}
			array[j] = aux;
		}
	}

	public static <T> void insertion(T[] array, Comparator<T> comparator)
	{				
		T aux;
		
		for(int i = 1; i < array.length; i++)
		{
			aux = array[i];
			
			int j = i;
			
			while(j > 0 && comparator.compare(array[j - 1], aux) < 0)
			{
				array[j] = array[j - 1];
				j--;
			}
			array[j] = aux;
		}
	}
	
	
	//selection method for all primitives and a generic type
	public static void selection(byte[] array)
	{
		byte min, aux;
		int swapIndex = 0;
				
		for(int i = 0; i < array.length; i++)
		{
			min = array[i];
			
			for(int j = i; j < array.length; j++)
			{
				if(!(min < array[j]))
				{
					min = array[j];
					swapIndex = j;
				}
			}
			
			aux = array[i];
			array[i] = array[swapIndex];
			array[swapIndex] = aux;
		}
	}

	public static void selection(short[] array)
	{
		short min, aux;
		int swapIndex = 0;
				
		for(int i = 0; i < array.length; i++)
		{
			min = array[i];
			
			for(int j = i; j < array.length; j++)
			{
				if(!(min < array[j]))
				{
					min = array[j];
					swapIndex = j;
				}
			}
			
			aux = array[i];
			array[i] = array[swapIndex];
			array[swapIndex] = aux;
		}
	}
	
	public static void selection(int[] array)
	{
		int min, aux;
		int swapIndex = 0;
				
		for(int i = 0; i < array.length; i++)
		{
			min = array[i];
			
			for(int j = i; j < array.length; j++)
			{
				if(!(min < array[j]))
				{
					min = array[j];
					swapIndex = j;
				}
			}
			
			aux = array[i];
			array[i] = array[swapIndex];
			array[swapIndex] = aux;
		}
	}
	
	public static void selection(long[] array)
	{
		long min, aux;
		int swapIndex = 0;
				
		for(int i = 0; i < array.length; i++)
		{
			min = array[i];
			
			for(int j = i; j < array.length; j++)
			{
				if(!(min < array[j]))
				{
					min = array[j];
					swapIndex = j;
				}
			}
			
			aux = array[i];
			array[i] = array[swapIndex];
			array[swapIndex] = aux;
		}
	}
	
	public static void selection(float[] array)
	{
		float min, aux;
		int swapIndex = 0;
				
		for(int i = 0; i < array.length; i++)
		{
			min = array[i];
			
			for(int j = i; j < array.length; j++)
			{
				if(!(min < array[j]))
				{
					min = array[j];
					swapIndex = j;
				}
			}
			
			aux = array[i];
			array[i] = array[swapIndex];
			array[swapIndex] = aux;
		}
	}
	
	public static void selection(double[] array)
	{
		double min, aux;
		int swapIndex = 0;
				
		for(int i = 0; i < array.length; i++)
		{
			min = array[i];
			
			for(int j = i; j < array.length; j++)
			{
				if(!(min < array[j]))
				{
					min = array[j];
					swapIndex = j;
				}
			}
			
			aux = array[i];
			array[i] = array[swapIndex];
			array[swapIndex] = aux;
		}
	}
	
	public static void selection(char[] array)
	{
		char min, aux;
		int swapIndex = 0;
				
		for(int i = 0; i < array.length; i++)
		{
			min = array[i];
			
			for(int j = i; j < array.length; j++)
			{
				if(!(min < array[j]))
				{
					min = array[j];
					swapIndex = j;
				}
			}
			
			aux = array[i];
			array[i] = array[swapIndex];
			array[swapIndex] = aux;
		}
	}
	
	public static void selection(boolean[] array)
	{
		boolean min, aux;
		int swapIndex = 0;
				
		for(int i = 0; i < array.length; i++)
		{
			min = array[i];
			
			for(int j = i; j < array.length; j++)
			{
				if(!(!min && array[j]))
				{
					min = array[j];
					swapIndex = j;
				}
			}
			
			aux = array[i];
			array[i] = array[swapIndex];
			array[swapIndex] = aux;
		}
	}

	public static <T> void selection(T[] array, Comparator<T> comparator)
	{
		T min, aux;
		int swapIndex = 0;
				
		for(int i = 0; i < array.length; i++)
		{
			min = array[i];
			
			for(int j = i; j < array.length; j++)
			{
				if(!(comparator.compare(min, array[j]) < 0))
				{
					min = array[j];
					swapIndex = j;
				}
			}
			
			aux = array[i];
			array[i] = array[swapIndex];
			array[swapIndex] = aux;
		}
	}

	static Scanner cosa = new Scanner(System.in);
	
	public static void merge(byte[] array)
	{	
		byte[] left = new byte[array.length / 2];
		byte[] right = new byte[array.length / 2 + array.length % 2];
			
		if(array.length < 3)
		{
			if(array.length == 2)
			{
				if(!(array[0] < array[1]))
				{
					byte aux = array[0];
					array[0] = array[1];
					array[1] = aux;
				}	
			}
		}
		else
		{
			for(int i = 0; i < array.length / 2; i++)
			{
				left[i] = array[i];
			}
			
			for(int i = array.length / 2; i < array.length; i++)
			{
				right[i - array.length / 2] = array[i];
			}
			
			merge(left);
			merge(right);	
			
		
			int leftIndex = 0;
			int rightIndex = 0;
			int index = 0;
			
			while(leftIndex < left.length || rightIndex < right.length)
			{
				if(leftIndex == left.length)
				{
					array[index] = right[rightIndex];
					rightIndex++;
				}
				else if(rightIndex == right.length)
				{
					array[index] = left[leftIndex];
					leftIndex++;
				}	
				else if(left[leftIndex] < right[rightIndex])
				{
					array[index] = left[leftIndex];
					leftIndex++;
				}
				else
				{
					array[index] = right[rightIndex];
					rightIndex++;
				}
				
				index++;
			}
		}
	}
	
	
	//isSorted method for all primitives and a generic type
	public static boolean isSorted(byte[] array)
	{
		for(int i = 1; i < array.length; i++)
		{
			if(array[i - 1] > array[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isSorted(short[] array)
	{
		for(int i = 1; i < array.length; i++)
		{
			if(array[i - 1] > array[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isSorted(int[] array)
	{
		for(int i = 1; i < array.length; i++)
		{
			if(array[i - 1] > array[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isSorted(long[] array)
	{
		for(int i = 1; i < array.length; i++)
		{
			if(array[i - 1] > array[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isSorted(float[] array)
	{
		for(int i = 1; i < array.length; i++)
		{
			if(array[i - 1] > array[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isSorted(double[] array)
	{
		for(int i = 1; i < array.length; i++)
		{
			if(array[i - 1] > array[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isSorted(char[] array)
	{
		for(int i = 1; i < array.length; i++)
		{
			if(array[i - 1] > array[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isSorted(boolean[] array)
	{
		for(int i = 1; i < array.length; i++)
		{
			if(array[i - 1] && !array[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static <T> boolean isSorted(T[] array, Comparator<T> comparator)
	{
		for(int i = 1; i < array.length; i++)
		{
			if(comparator.compare(array[i - 1], array[i]) < 0)
			{
				return false;
			}
		}
		
		return true;
	}
		
	
	//shuffle method for all primitives and a generic type
	public static void shuffle(byte[] array)
	{
		Random random = new Random();
		int index1, index2;
		
		byte aux = 0;
		
		for(int i = 0; i < array.length * 2; i++)
		{
			index1 = random.nextInt(array.length);
			index2 = random.nextInt(array.length);
			
			aux = array[index1];
			array[index1] = array[index2];
			array[index2] = aux;
		}
	}
	
	public static void shuffle(short[] array)
	{
		Random random = new Random();
		int index1, index2;
		
		short aux = 0;
		
		for(int i = 0; i < array.length * 2; i++)
		{
			index1 = random.nextInt(array.length);
			index2 = random.nextInt(array.length);
			
			aux = array[index1];
			array[index1] = array[index2];
			array[index2] = aux;
		}
	}
	
	public static void shuffle(int[] array)
	{
		Random random = new Random();
		int index1, index2;
		
		int aux = 0;
		
		for(int i = 0; i < array.length * 2; i++)
		{
			index1 = random.nextInt(array.length);
			index2 = random.nextInt(array.length);
			
			aux = array[index1];
			array[index1] = array[index2];
			array[index2] = aux;
		}
	}
	
	public static void shuffle(long[] array)
	{
		Random random = new Random();
		int index1, index2;
		
		long aux = 0;
		
		for(int i = 0; i < array.length * 2; i++)
		{
			index1 = random.nextInt(array.length);
			index2 = random.nextInt(array.length);
			
			aux = array[index1];
			array[index1] = array[index2];
			array[index2] = aux;
		}
	}
	
	public static void shuffle(float[] array)
	{
		Random random = new Random();
		int index1, index2;
		
		float aux = 0;
		
		for(int i = 0; i < array.length * 2; i++)
		{
			index1 = random.nextInt(array.length);
			index2 = random.nextInt(array.length);
			
			aux = array[index1];
			array[index1] = array[index2];
			array[index2] = aux;
		}
	}
	
	public static void shuffle(double[] array)
	{
		Random random = new Random();
		int index1, index2;
		
		double aux = 0;
		
		for(int i = 0; i < array.length * 2; i++)
		{
			index1 = random.nextInt(array.length);
			index2 = random.nextInt(array.length);
			
			aux = array[index1];
			array[index1] = array[index2];
			array[index2] = aux;
		}
	}
	
	public static void shuffle(char[] array)
	{
		Random random = new Random();
		int index1, index2;
		
		char aux = 0;
		
		for(int i = 0; i < array.length * 2; i++)
		{
			index1 = random.nextInt(array.length);
			index2 = random.nextInt(array.length);
			
			aux = array[index1];
			array[index1] = array[index2];
			array[index2] = aux;
		}
	}
	
	public static void shuffle(boolean[] array)
	{
		Random random = new Random();
		int index1, index2;
		
		boolean aux = false;
		
		for(int i = 0; i < array.length * 2; i++)
		{
			index1 = random.nextInt(array.length);
			index2 = random.nextInt(array.length);
			
			aux = array[index1];
			array[index1] = array[index2];
			array[index2] = aux;
		}
	}
	
	public static <T> void shuffle(T[] array)
	{
		Random random = new Random();
		int index1, index2;
		
		T aux = null;
		
		for(int i = 0; i < array.length * 2; i++)
		{
			index1 = random.nextInt(array.length);
			index2 = random.nextInt(array.length);
			
			aux = array[index1];
			array[index1] = array[index2];
			array[index2] = aux;
		}
	}
}


