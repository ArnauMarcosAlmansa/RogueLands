package GameEngine;

import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.Scanner;

import CharacterStuff.PlayableCharacter;
import General.*;
import Graphics.*;
import MapStuff.*;
import WorldStuff.World;

/**
 * 
 * Singleton que gestiona la partida: cragado, jugar, guardado.
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */

public class Game
{
	private static Game instance;
	
	private GameScene currentScene;
	
	private PlayableCharacter player;
	
	private Taulell canvas;
	
	private Finestra window;
	
	private World world;

	private int status = 0;

	private boolean isOnMap = false;
	
	private Game()
	{
		currentScene = new GameScene();
	}
	
	/**
	 * Método que intenta cargar la partida del jugador actual.
	 * 
	 * @return true si se ha podido cargar partida, false en caso contrario.
	 */
	public boolean Load()
	{	
		Save save;
		
		try
		{
			save = (Save) FileManager.instance().deserialize(FileType.SAVE, "save.dat");
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			return false;
		}
		
		world = save.world;
		
		currentScene.setMap(world.getMapOn(save.playerPosOnWorld));
		
		player = currentScene.getPlayableCharacter();
		
		return true;
	}
	
	/**
	 * Prepara la partida. Crea un Taulell y una Finestra, prepara el array de imagenes y coloca al jugador.
	 */
	public void Setup()
	{
		canvas = new Taulell();
		window = new Finestra(canvas);
		
		window.setName("Rogue Lands");
		
		canvas.setActcolors(false);
		
		canvas.setActborde(false);
		
		canvas.setActimatges(true);
		
		String[] imgs = {
				"resources/images/transparent.png",
				"resources/images/ground.png",
				"resources/images/wall.png",
				"resources/images/ice.png",
				"resources/images/icewall.png",
				"resources/images/sand.png",
				"resources/images/rock.png",
				/* 7 */
				"resources/images/personajeup.png",
				"resources/images/personajeright.png",
				"resources/images/personajedown.png",
				"resources/images/personajeleft.png",
				/* 11 */
				"resources/images/enemyup.png",
				"resources/images/enemyright.png",
				"resources/images/enemydown.png",
				"resources/images/enemyleft.png",
				/* 15 */
				"resources/images/slime1.png",
				"resources/images/slime2.png",
				"resources/images/slime3.png",
				/* 18 */
				"resources/images/unknown.png",
				"resources/images/visited.png",
				"resources/images/clear.png",
				"resources/images/current.png",
				"resources/images/water.png",
				/* 23 */
				"resources/images/bonfire.png",
				"resources/images/ground.png",
				"resources/images/ground.png",
				"resources/images/ground.png",
				"resources/images/ground.png",
				"resources/images/ground.png"
				};
		
		canvas.setImatges(imgs);
		
		currentScene.
			currentMap().
				charactersGrid()[player.pos().row()][player.pos().col()] = player;
	}
	
	/**
	 * Método principal de la partida. contiene el bucle principal de juego.
	 */
	public void Play()
	{
		status = 0;
		
		while(status == 0)
		{
			if(!isOnMap)
			{
				Display();

				//System.out.println(Functions.intMatrixToString(currentScene.currentMap().characterLayout()));
				
				System.out.println("Playing...");
				
				//System.out.println(currentScene.currentMap().toString());

				player.Update();
				
				currentScene.Update();
				
				System.out.println("health: " + player.CurrentHealth());
			}
			else
			{
				canvas.dibuixa(world.getWorldMap());
				
				canvas.overdibuixa(new int[1][1]);
				
				while(true)
				{
					char input = Game.instance().window().getActualChar();
					
					System.out.println("el input es " + input);
					
					if(input == 'm')
					{
						toggleMap();
						break;
					}
					
					try
					{
						Thread.sleep(100);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		if(status == 1)
		{
			canvas.setActimgbackground(true);
			
			canvas.setImgbackground("resources/images/victory.png");
			
			canvas.dibuixa(new int[1][1]);
			
			canvas.overdibuixa(new int[1][1]);
			
			GameManager.instance().recordVictory();
		}
		else if(status == -1)
		{
			canvas.setActimgbackground(true);
			
			canvas.setImgbackground("resources/images/defeat.png");
			
			canvas.dibuixa(new int[1][1]);
			
			canvas.overdibuixa(new int[1][1]);
			
			FileManager.instance().deleteGame(GameManager.instance().currentPlayer());
		}
		else
		{
			canvas.setActimgbackground(true);
			
			canvas.setImgbackground("resources/images/quitting.png");
			
			canvas.dibuixa(new int[1][1]);
			
			canvas.overdibuixa(new int[1][1]);
		}
		
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	}
	
	/**
	 * Actualiza la imagen del Taulell.
	 */
	private void Display()
	{
		// TODO Auto-generated method stub
		canvas.dibuixa(currentScene.currentMap().layout());
		
		canvas.overdibuixa(currentScene.currentMap().characterLayout());
	}

	/**
	 * Guarda partida.
	 */
	public void Save()
	{
		Save save = new Save();
		
		save.world = this.world;
		
		System.out.println(currentScene.currentMap() == null);
		
		save.playerPosOnWorld = this.currentScene.currentMap().pos();
		
		save.playerPosOnMap = this.currentScene.getPlayableCharacter().pos();
		
		FileManager.instance().serialize(save, FileType.SAVE);
	}
	
	/**
	 * Establece los elementos de la escena.
	 */
	public void AssembleScene()
	{
		currentScene = new GameScene();
		
		currentScene.setMap(world.getMapOn(world.findPlayableCharacter()[0]));
	}
	
	/**
	 * 
	 * @return Retorna la instancia de Game.
	 */
	public static Game instance()
	{
		if(instance == null)
		{
			instance = new Game();
		}
		
		return instance;
	}
	
	/**
	 * 
	 * @return Retorna el Taulell.
	 */
	public Taulell canvas()
	{
		return canvas;
	}
	
	/**
	 * 
	 * @return Retorna la Finestra.
	 */
	public Finestra window()
	{
		return window;
	}
	
	/**
	 * 
	 * @return retorna la escena actual.
	 */
	public GameScene currentScene()
	{
		return currentScene;
	}
	
	/**
	 * 
	 * @return Retorna el PlayableCharacter que controla el jugador.
	 */
	public PlayableCharacter player()
	{
		return player;
	}
	
	/**
	 * 
	 * @return Devuelve el mundo de la partida.
	 */
	public World world()
	{
		return world;
	}
	
	/**
	 * Setter del mundo de la partida.
	 * 
	 * @param world El mundo que se quiere esablecer como nuevo mundo.
	 */
	public void setWorld(World world)
	{
		this.world = world;
	}
	
	/**
	 * Establece el estado de la partida a victoria.
	 */
	public void win()
	{
		// TODO Auto-generated method stub
		status = 1;
	}
	
	/**
	 * Establece el estado de la partida a derrota.
	 */
	public void lose()
	{
		// TODO Auto-generated method stub
		status = -1;
	}
	
	/**
	 * Establece el estado de la partida a salir del juego.
	 */
	public void quit()
	{
		status = 2;
	}
	
	/**
	 * Alterna el booleano que indica si se está mirando el mapa o jugando.
	 */
	public void toggleMap()
	{
		// TODO Auto-generated method stub
		isOnMap  = !isOnMap;
	}
}
