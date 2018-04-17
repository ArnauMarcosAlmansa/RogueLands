package GameEngine;

import java.io.FileNotFoundException;
import java.util.Scanner;

import CharacterStuff.PlayableCharacter;
import General.*;
import General.DataParser;
import Graphics.*;
import MapStuff.*;

public class Game
{
	private static Game instance;
	
	private GameScene currentScene;
	
	private PlayableCharacter player;
	
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
		
		player = new PlayableCharacter();
		
		player.pos().set(save.playerPos);
	}
	
	public void Setup()
	{
		canvas = new Taulell();
		window = new Finestra(canvas);
		
		window.setName("Rogue Lands");
		
		canvas.setActcolors(false);
		
		canvas.setActborde(false);
		
		canvas.setActimatges(true);
		
		String[] imgs = {"resources/images/ground.png", "resources/images/wall.png", "resources/images/ground.png", "resources/images/personaje.png","resources/images/transparent.png", "resources/images/índice.jpg", "resources/images/ground.png", "resources/images/ground.png", "resources/images/ground.png", "resources/images/ground.png", "resources/images/ground.png", "resources/images/ground.png", "resources/images/ground.png", "resources/images/ground.png", "resources/images/ground.png", "resources/images/ground.png", "resources/images/ground.png", "resources/images/ground.png", "resources/images/ground.png", "resources/images/ground.png", "resources/images/ground.png", "resources/images/ground.png"};
		
		canvas.setImatges(imgs);
		
		currentScene.currentMap().charactersGrid()[player.pos().row()][player.pos().col()] = player;
	}
	
	public void Play()
	{
		while(true)
		{
			canvas.dibuixa(currentScene.currentMap().layout());
			
			canvas.overdibuixa(currentScene.currentMap().characterLayout());
			
			//System.out.println(Functions.intMatrixToString(currentScene.currentMap().characterLayout()));
			
			System.out.println("Playing...");
			
			//System.out.println(currentScene.currentMap().toString());
			
			
			player.Update();
			
			currentScene.Update();
			
			System.out.println("health: " + player.CurrentHealth());
		}
	}
	
	public void Save()
	{
		Vector2 mapPos = Game.instance().currentScene().currentMap().pos();
		String data = "map: " + mapPos.row() + "-" + mapPos.col() + ".map\nplayerpos: ";
		
		Vector2 playerPos = Game.instance().player().pos();
		data = data + playerPos.row() + " " + playerPos.col();
		
		FileManager.instance().saveFile(data, "save.sav", FileType.SAVE);
	}
	
	public static Game instance()
	{
		if(instance == null)
		{
			instance = new Game();
		}
		
		return instance;
	}
	
	public Taulell canvas()
	{
		return canvas;
	}
	
	public Finestra window()
	{
		return window;
	}
	
	public GameScene currentScene()
	{
		return currentScene;
	}
	
	public PlayableCharacter player()
	{
		return player;
	}
	
	public void setSprites(String[] paths)
	{
		canvas.setImatges(paths);
	}
}
