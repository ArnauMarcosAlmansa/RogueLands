package Main;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import GameEngine.Game;
import GameEngine.GameManager;
import General.DataParser;
import General.FileManager;
import General.FileType;
import General.Functions;
import WorldStuff.World;
import WorldStuff.WorldCfg;

public class Program
{

	public static Scanner input = new Scanner(System.in);
	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		boolean playing = false;
		
		while(true)
		{
			String option = menu();
			
			if(option.equals("1"))
			{
				createGame();
				playing = true;
			}
			else if(option.equals("2"))
			{
				GameManager.instance().loadGame(scan("Write the name of your game: "));
				playing = true;
			}
			else if(option.equals("3"))
			{
				deleteGames();
			}		
			else if(option.equals("4"))
			{
				System.out.println("Thanks for playing Rogue Lands");
				System.exit(0);
			}
			else
			{
				System.out.println("Invalid option");
			}
			
			if(playing)
			{
				try
				{
					Game.instance().Load(DataParser.parseSave(FileManager.instance().readFile(FileType.SAVE, "save.sav")));
				}
				catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					System.out.println("A severe error ocurred");
					playing = false;
					continue;
				}
				
				Game.instance().Setup();
				
				Game.instance().Play();
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
		System.out.println("Rogue Lands\nMenu\n1. New Game\n2. Continue Game\n3. Delete games\n4. Exit");
	
		return input.nextLine();
	}
	
	private static String scan(String text)
	{
		System.out.print(text);
		return input.nextLine();
	}
	
	private static void createGame()
	{
		boolean succes = false;
		
		while(true)
		{
			succes = GameManager.instance().createGame(scan("Write a name for your game: "));
			
			if(succes) break;
			
			System.out.println("That name already exists.");
		}
	}
	
	private static void deleteGames()
	{
		String option;
		
		while(true)
		{
			System.out.println("Which game would you (kindly) want to delete?");
			System.out.println(Functions.listToString(GameManager.instance().players()));
			System.out.println("or type \"none\" to exit");
			
			option = input.nextLine();
			
			if(option.equals("none")) break;
			
			if(GameManager.instance().players().contains(option))
			{
				System.out.println("Are you sure you want to delete this game? (Y/n)");
				
				String ans = input.nextLine();
				
				if(ans.equals("Y"))
				{
					GameManager.instance().players().remove(option);
					
					FileManager.instance().deleteGame(option);
					
					System.out.println("Deleted.");
				}
				else
				{
					System.out.println("Aborted.");
				}
			}
		}
	}
}
