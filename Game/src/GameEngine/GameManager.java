package GameEngine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeMap;

import General.DataParser;
import General.FileManager;
import General.FileType;
import General.Functions;
import General.PresetType;
import Main.Program;
import WorldStuff.World;
import WorldStuff.WorldCfg;


public class GameManager 
{
	
	private ArrayList<String> players;
	
	private String currentPlayer;
	
	private long sessionStart;
	
	private static GameManager instance;
	
	private TreeMap<PresetType, Integer[]> presetRanges = new TreeMap<PresetType, Integer[]>();
	
	private GameManager()
	{
		try
		{
			players = DataParser.parseList(FileManager.instance().readFile(FileType.USERS, "users.cfg"));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		presetRanges.put(PresetType.MAP_TILE, new Integer[] {0, 100});
		
		setupSprites();
	}
	
	private void setupSprites()
	{
		//TODO hacer esto
		
		TreeMap<Integer, String> spritesMap =  ;
		
		ArrayList<String> paths = DataParser.parseSpritesId(spritesMap);
		
		Game.instance().setSprites((String[]) paths.toArray());
	}
	
	public boolean createGame(String name)
	{
		try
		{
			players = DataParser.parseList(FileManager.instance().readFile(FileType.USERS, "users.cfg"));
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
				
		if(players.contains(name))
		{
			return false;
		}
		else
		{
			players.add(name);
			
			currentPlayer = name;
			
			FileManager.instance().createUserFolders();
			
			FileManager.instance().saveFile(Functions.listToString(players), "users.cfg", FileType.USERS);
			
			WorldCfg selected = null;
			
			while(true)
			{
				System.out.println("On which dificulty would you like to play?");
				
				int difficulty = -1;
				
				try {
					difficulty = Program.input.nextInt();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Invalid input");
					continue;
				}
				
				try
				{
					selected = DataParser.parseWorldCfg(FileManager.instance().readFile(FileType.WORLD_PRESET, "presets.cfg"))[difficulty];
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
					System.out.println("Invalid number");
				}
				
				break;
			}
			
			World temp = new World(selected);
			
			String save = "map: " + temp.startingMapName() + "\nplayerpos: 8 8\nEND";
			
			FileManager.instance().saveFile(save, "save.sav", FileType.SAVE);
			
			return true;
		}
	}
	
	public void startGame()
	{
		
	}
	
	public void loadGame(String playerName) throws FileNotFoundException
	{
		Game.instance().Load(DataParser.parseSave(FileManager.instance().readFile(FileType.SAVE, "save.sav")));
	}
	
	public void updateUsersFile()
	{
		FileManager.instance().saveFile(Functions.listToString(players), "users.cfg", FileType.USERS);
	}
	
	public String currentPlayer()
	{
		return currentPlayer;
	}
	
	public void setCurrentPlayer(String newPlayer)
	{
		currentPlayer = newPlayer;
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
