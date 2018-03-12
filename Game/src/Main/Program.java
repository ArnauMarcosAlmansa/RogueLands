package Main;
import java.util.Scanner;

import GameEngine.GameManager;
import General.DataParser;
import General.FileManager;
import General.FileType;
import WorldStuff.World;
import WorldStuff.WorldCfg;

public class Program
{

	public static Scanner input = new Scanner(System.in);
	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		while(true)
		{
			String option = menu();
			
			if(option.equals("1"))
			{
				GameManager.instance().createGame(scan("Write a name for your game: "));
			}
			else if(option.equals("2"))
			{
				GameManager.instance().loadGame(scan("Write the name of your game: "));
			}
			else if(option.equals("3"))
			{
				System.out.println("Thanks for playing Rogue Lands");
				System.exit(0);
			}
			else
			{
				System.out.println("Invalid option");
			}
		}
	
	}

	public static void printArray(int[] array)
	{
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + " ");
		}
		
		System.out.println();
	}
	
	private static String menu()
	{
		System.out.println("Rogue Lands\nMenu\n1. New Game\n2. Continue Game\n3. Exit");
	
		return input.nextLine();
	}
	
	private static String scan(String text)
	{
		System.out.print(text);
		return input.nextLine();
	}
}
