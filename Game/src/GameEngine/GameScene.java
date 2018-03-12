package GameEngine;

import MapStuff.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import CharacterStuff.*;
import General.DataParser;
import General.FileManager;
import General.FileType;

public class GameScene
{
	private GameMap currentMap;
	
	private GameMap[] aroundMaps = new GameMap[4];
	
	private ArrayList<GameCharacter> characters = new ArrayList<GameCharacter>();
	
	public void Update()
	{
		
	}
	
	public void changeMap(GameMap newMap)
	{
		currentMap = newMap;
		
		int row = currentMap.pos().row();
		int col = currentMap.pos().col();
		
		try
		{
			aroundMaps[0] = DataParser.parseGameMap(FileManager.instance().readFile(FileType.GAME_MAP, (row - 1) + "-" + col + ".map"));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			aroundMaps[1] = DataParser.parseGameMap(FileManager.instance().readFile(FileType.GAME_MAP, row + "-" + (col + 1) + ".map"));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			aroundMaps[2] = DataParser.parseGameMap(FileManager.instance().readFile(FileType.GAME_MAP, (row + 1) + "-" + col + ".map"));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			aroundMaps[3] = DataParser.parseGameMap(FileManager.instance().readFile(FileType.GAME_MAP, row + "-" + (col - 1) + ".map"));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setMaps(GameMap current, GameMap[] around)
	{
		currentMap = current;
		aroundMaps = around;
	}
	
	public GameMap currentMap()
	{
		return currentMap;
	}
	
	public GameMap[] aroundMaps()
	{
		return aroundMaps;
	}
}
