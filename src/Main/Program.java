package Main;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import GameEngine.Game;
import GameEngine.GameManager;
import General.FileManager;
import General.FileType;
import General.Functions;
import WorldStuff.World;
import WorldStuff.WorldCfg;

/**
 * Clase de entrada.
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */
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
			
			System.out.println("\n\n\n\n\n\n");
			
			if(option.equals("1"))
			{
				createGame();
				playing = true;
			}
			else if(option.equals("2"))
			{
				continueGame();
				playing = true;
			}
			else if(option.equals("3"))
			{
				deleteGames();
			}
			else if(option.equals("4"))
			{
				GameManager.instance().viewHallOfFame();
				input.nextLine();
			}
			else if(option.equals("5"))
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
				boolean ans = Game.instance().Load();
				
				if(!ans) continue;
				
				Game.instance().AssembleScene();
				
				Game.instance().Setup();
				
				Game.instance().Play();
				
				playing = false;
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
	
	/**
	 * Muestra el menú y lee la entrada.
	 * @return La entrada.
	 */
	private static String menu()
	{
		System.out.println("Rogue Lands\nMenu\n1. New Game\n2. Continue Game\n3. Delete games\n4. View Hall Of Fame\n5. Exit");
	
		return input.nextLine();
	}
	
	
	/**
	 * Lee una entrada precedida por un texto.
	 * @param text Texto a mostrar.
	 * @return Entrada.
	 */
	private static String scan(String text)
	{
		System.out.print(text);
		return input.nextLine();
	}
	
	/**
	 * Inicia el proceso de creación de partida.
	 */
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
	
	/**
	 * Inicia el proceso de continuar partida.
	 */
	private static void continueGame()
	{
		String name = "";
		while(true)
		{
			name = scan("Write the name of your game: ");
			
			if(GameManager.instance().players().contains(name)) break;
		}
		
		GameManager.instance().setCurrentPlayer(name);
		
		try
		{
			GameManager.instance().loadGame();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Something happened");
		}
	}
	
	/**
	 * Permite eliminar partidas a voluntad del usuario.
	 */
	private static void deleteGames()
	{
		String option;
		
		while(true)
		{
			System.out.println("Which game would you want to delete?");
			System.out.println(Functions.listToString(GameManager.instance().players()));
			System.out.println("or press enter to exit");
			
			option = input.nextLine();
			
			if(option.equals("")) break;
			
			if(GameManager.instance().players().contains(option))
			{
				System.out.println("Are you sure you want to delete this game? (Y/n)");
				
				String ans = input.nextLine();
				
				if(ans.equalsIgnoreCase("Y"))
				{
					GameManager.instance().players().remove(option);
					
					FileManager.instance().deleteGame(option);
					
					System.out.println("Deleted.");
				}
			}
		}
	}
}
