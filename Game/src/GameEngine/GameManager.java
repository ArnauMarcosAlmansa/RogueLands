package GameEngine;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import General.DataParser;
import General.FileManager;
import General.FileType;
import General.Functions;


public class GameManager 
{
	
	private ArrayList<String> players;
	private String currentPlayer;
	
	private long sessionStart;
	
	private static GameManager instance;
	
	private GameManager()
	{
		try
		{
			players = DataParser.parseList(FileManager.instance().readFile(FileType.USERS, "users.cfg"));
		}catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean createGame(String name)
	{
		try
		{
			players = DataParser.parseList(FileManager.instance().readFile(FileType.USERS, "users.cfg"));
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		if(players.contains(name))
		{
			return false;
		}
		else
		{
			players.add(name);
			
			FileManager.instance().saveFile(Functions.listToString(players), "users.cfg", FileType.USERS);
			
			return true;
		}
	}
	
	public void startGame()
	{
		
	}
	
	public void loadGame(String playerName)
	{
		//Game.instance().
	}
	
	public void updateUsersFile()
	{
		FileManager.instance().saveFile(Functions.listToString(players), "users.cfg", FileType.USERS);
	}
	
	public String currentPlayer()
	{
		return currentPlayer;
	}
	
	public ArrayList<String> players()
	{
		return players;
	}
	
	public static GameManager instance()
	{
		if(instance == null)
		{
			instance = new GameManager();
		}
		
		return instance;
	}
}
