package GameEngine;

public class Game
{
	private static Game instance;
	
	private GameScene currentScene;
	
	private Game()
	{
		currentScene = new GameScene();
	}
	
	private void Play()
	{
		while(true)
		{
			
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
