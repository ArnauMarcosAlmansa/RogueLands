package GameEngine;

import General.*;
import MapStuff.*;

public class Game
{
	private static Game instance;
	
	private GameScene currentScene;
	
	private Game()
	{
		currentScene = new GameScene();
	}
	
	public void Load(Save save)
	{
		GameMap current = DataParser.
		
		currentScene.setMaps(current, around);
	}
	
	public void Setup()
	{
		
	}
	
	public void Play()
	{
		while(true)
		{
			currentScene.Update();
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
