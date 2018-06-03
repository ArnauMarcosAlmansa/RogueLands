package WorldStuff;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

import CharacterStuff.PlayableCharacter;
import GameEngine.Game;
import General.Sorter;
import General.Vector2;
import Main.Program;
import MapStuff.GameMap;

public class World implements Serializable
{	
	/**
	 * Mundo de juego. Contiene GameMaps.
	 * @since Java 8.0
	 * @version 1.0
	 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
	 */
	//private static final long serialVersionUID = -3065830241243570246L;

	private String name;
	
	private GameMap[][] maps;
			
	public World(GameMap[][] maps)
	{
		this.name = name;
		
		this.maps = maps;
	}
	
	/**
	 * 
	 * @param position Posición del mapa a obtener,
	 * @return GameMap en la posición especificada. Puede ser null.
	 */
	public GameMap getMapOn(Vector2 position)
	{
		GameMap map = null;
		
		try
		{
			map = maps[position.row()][position.col()];
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * Busca al PlayableCharacter en el mundo.
	 * @return Array[2] de posiciones del PlayableCharacter. La primera es la posición en el mundo y la segunda, la posición en el mapa.
	 */
	public Vector2[] findPlayableCharacter()
	{
		Vector2[] positions = new Vector2[2];
		
		for(int i = 0; i < maps.length; i++)
		{
			for(int j = 0; j < maps[i].length; j++)
			{
				positions[0] = new Vector2(i, j);
				
				if(maps[i][j] != null)
				{
					for(int i2 = 0; i2 < maps.length; i2++)
					{
						for(int j2 = 0; j2 < maps[i2].length; j2++)
						{
							if(maps[i][j].charactersGrid()[i2][j2] instanceof PlayableCharacter)
							{
								positions[1] = new Vector2(i2, j2);
								
								return positions;
							}
						}
					}
				}
			}
		}
		
		return null;
	}

	/**
	 * 
	 * @return true si no hay enemigos en el mundo, false en caso contrario.
	 */
	public boolean isClear()
	{
		// TODO Auto-generated method stub
		boolean clear = true;
		
		for(int i = 0; i < maps.length; i++)
		{
			for(int j = 0; j < maps[i].length; j++)
			{
				if(maps[i][j] != null && !maps[i][j].isClear())
				{
					clear = false;
				}
			}
		}
		
		return clear;
	}

	/**
	 * 
	 * @return Matriz de ints que representa un esquema del mundo.
	 */
	public int[][] getWorldMap()
	{
		// TODO Auto-generated method stub
		int[][] map = new int[maps.length][maps[0].length];
		
		for(int i = 0; i < maps.length; i++)
		{
			for(int j = 0; j < maps[i].length; j++)
			{
				if(maps[i][j] != null)
				{
				
					map[i][j] = maps[i][j].status();
					
					if(Game.instance().currentScene().currentMap().pos().equals(new Vector2(i, j)))
					{
						map[i][j] = 21;
					}
				}
				else
				{
					map[i][j] = 22;
				}
			}
		}
		
		return map;
	}
}
