package GameEngine;

import java.io.FileNotFoundException;
import java.util.Scanner;

import General.*;
import General.DataParser;
import Graphics.*;
import MapStuff.*;

public class Game
{
	private static Game instance;
	
	private GameScene currentScene;
	
	private Taulell canvas;
	
	private Finestra window;
	
	
	private Game()
	{
		currentScene = new GameScene();
	}
	
	public void Load(Save save)
	{
		GameMap current = null;
		
		try
		{
			current = DataParser.parseGameMap(FileManager.instance().readFile(FileType.GAME_MAP, save.map));
		}
		catch (FileNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		GameMap[] around = new GameMap[4];
		
		Scanner auxiliarinput = new Scanner(save.map);
		
		int[] temp = Functions.extractInts(save.map);
		
		int row = temp[0];
		int col = temp[1];
		
		try
		{
			around[0] = DataParser.parseGameMap(FileManager.instance().readFile(FileType.GAME_MAP, (row - 1) + "-" + col + ".map"));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			around[1] = DataParser.parseGameMap(FileManager.instance().readFile(FileType.GAME_MAP, row + "-" + (col + 1) + ".map"));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			around[2] = DataParser.parseGameMap(FileManager.instance().readFile(FileType.GAME_MAP, (row + 1) + "-" + col + ".map"));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			around[3] = DataParser.parseGameMap(FileManager.instance().readFile(FileType.GAME_MAP, row + "-" + (col - 1) + ".map"));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		auxiliarinput.close();
		
		currentScene.setMaps(current, around);
	}
	
	public void Setup()
	{
		canvas = new Taulell();
		window = new Finestra(canvas);
		
		window.setName("Rogue Lands");
		
		canvas.setActcolors(false);
	}
	
	public void Play()
	{
		while(true)
		{
			currentScene.Update();
			canvas.dibuixa(currentScene.currentMap().layout());
		}
	}
	
	public static Game instance()
	{
		if(instance == null)
		{
			instance = new Game();
		}
		
		return instance;
	}
}
