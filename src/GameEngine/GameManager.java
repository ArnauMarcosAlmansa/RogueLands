package GameEngine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import General.FileManager;
import General.FileType;
import General.Functions;
import General.PresetType;
import Main.Program;
import WorldStuff.World;
import WorldStuff.WorldBuilder;
import WorldStuff.WorldCfg;

/**
 * Gestor de las partidas y los jugadores.
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */
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
			players = Functions.parseList(FileManager.instance().readFile(FileType.USERS, "users.cfg"));
		
			FileManager.instance().createTextFile("HallOfFame.csv", "name,date");
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		presetRanges.put(PresetType.MAP_TILE, new Integer[] {0, 100});
		
	}
	
	/**
	 * Crea una partida.
	 * @param name Nombre del jugador.
	 * @return true si se pudo crear partida, false en caso contrario (si el jugador ya existe).
	 */
	public boolean createGame(String name)
	{
		try
		{
			players = Functions.parseList(FileManager.instance().readFile(FileType.USERS, "users.cfg"));
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
			
			FileManager.instance().writeFile(Functions.listToString(players), "users.cfg", FileType.USERS);
			
			WorldCfg selected = new WorldCfg();
			
			selected.biomes = new String[] {"snow", "wood", "desert"};
			
			selected.height = 16;
			
			selected.width = 16;
			
			WorldBuilder wb = new WorldBuilder(selected);
			
			World world = wb.build();
			
			Game.instance().setWorld(world);
			
			Game.instance().AssembleScene();
			
			Game.instance().Save();
			
			return true;
		}
	}
	
	/**
	 * Carga partida del jugador actual.
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadGame() throws FileNotFoundException
	{
		Game.instance().Load();
	}
	
	/**
	 * Inserta el nuevo jugador el el archivo de jugadores.
	 */
	public void updateUsersFile()
	{
		FileManager.instance().writeFile(Functions.listToString(players), "users.cfg", FileType.USERS);
	}
	
	/**
	 * 
	 * @return retorna el nombre del jugador actual.
	 */
	public String currentPlayer()
	{
		return currentPlayer;
	}
	
	/**
	 * Establece el nombre del jugador.
	 * 
	 * @param newPlayer El nuevo nombre de jugador.
	 */
	public void setCurrentPlayer(String newPlayer)
	{
		currentPlayer = newPlayer;
	}
	
	/**
	 * 
	 * @return Retorna la lista de jugadores que tienen partida guardada.
	 */
	public ArrayList<String> players()
	{
		return players;
	}
	
	/**
	 * 
	 * @return Retorna la instancia de GameManager.
	 */
	public static GameManager instance()
	{
		if(instance == null)
		{
			instance = new GameManager();
		}
		
		return instance;
	}

	/**
	 * Registra al jugador que ha completado elm juego.
	 */
	public void recordVictory()
	{
		// TODO Auto-generated method stub
		
		Date date = java.util.Calendar.getInstance().getTime();
		
		FileManager.instance().addCsvLineTo("HallOfFame.csv", new String[] {currentPlayer, date.getDay() + "/" + date.getMonth() + "/" + date.getYear()});
	}

	/**
	 * Visualiza a los jugadores que se han pasado el juego.
	 */
	public void viewHallOfFame()
	{
		// TODO Auto-generated method stub
		System.out.println("HALL OF FAME");
		
		String[][] table = FileManager.instance().readCsv("HallOfFame.csv");
		
		for(int i = 1; i < table.length; i++)
		{
			for(int j = 0; j < table[i].length; j++)
			{
				System.out.print(table[i][j] + "    ");
			}
			
			System.out.println();
		}
	}
}
