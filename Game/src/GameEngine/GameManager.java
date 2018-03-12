package GameEngine;

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
	
	public boolean createGame(String name)
	{
		players = DataParser.parseList(FileManager.instance().readFile(FileType.USERS, "users.cfg"));
				
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
		Game.instance().
	}
	
	public String currentPlayer()
	{
		return currentPlayer;
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
