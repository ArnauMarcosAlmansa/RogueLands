package GameEngine;

import MapStuff.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import CharacterStuff.*;
import General.DataParser;
import General.FileManager;
import General.FileType;
import General.Vector2;

public class GameScene
{
	private GameMap currentMap;
	
	private GameMap[] aroundMaps = new GameMap[4];
	
	private ArrayList<GameCharacter> characters = new ArrayList<GameCharacter>();
	
	public void Update()
	{
		for(int i = 0; i < characters.size(); i++)
		{
			characters.get(i).Update();
		}
	}
	
	public void changeMap(GameMap newMap)
	{
		currentMap = newMap;
		
		currentMap.correctExits();
		
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
		
		placeEnemies(2);
	}
	
	private void placeEnemies(int n)
	{
		// TODO Auto-generated method stub
		characters.clear();
		
		for(int i = 0; i < n; i++)
		{
			EnemyCharacter temp = new EnemyCharacter();
			
			while(true)
			{
				Vector2 rand = new Vector2();
				
				rand.randomize(1, 14, 1, 14);
				
				if(Game.instance().currentScene().currentMap().charactersGrid()[rand.row()][rand.col()] == null)
				{
					temp.setPos(rand);
					
					Game.instance().currentScene().currentMap().charactersGrid()[rand.row()][rand.col()] = temp;
					
					break;
				}
			}
			
			characters.add(temp);
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
	
	public ArrayList<GameCharacter> characters()
	{
		return characters;
	}
}
