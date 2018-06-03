package MapStuff;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.TreeSet;

import CharacterStuff.GameCharacter;
import CharacterStuff.PlayableCharacter;
import GameEngine.Game;
import General.FileManager;
import General.FileType;
import General.Vector2;
import WorldStuff.GameMapScheme;
/**
 * Mapa de juego. Contiene casillas y personajes.
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */
public class GameMap implements Serializable
{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 6073279618300530632L;
	public static final int MAP_HEIGHT = 16;
	public static final int MAP_WIDTH = 16;
	
	private MapTile[][] tiles;
	
	private GameCharacter[][] characters;
	
	private String biome;
	
	private Vector2 pos;
	
	private boolean clear = false;
	
	private boolean visited = false;
	
	public GameMap(MapTile[][] tiles, GameCharacter[][] characters, String biome, Vector2 position)
	{
		this.tiles = tiles;
		
		this.characters = characters;
		
		this.biome = biome;
		
		this.pos = position;
		
	}
	
	/**
	 * Obtiene las id de las casillas y las introduce en una matriz de int.
	 * 
	 * @return La matriz de ids.
	 */
	public int[][] layout()
	{
		int[][] layout = new int[tiles.length][tiles[0].length];
		
		for(int i = 0; i < tiles.length; i++)
		{
			for(int j = 0; j < tiles[0].length; j++)
			{
				layout[i][j] = tiles[i][j].Id();
			}
		}
		
		return layout;
	}
	
	
	
	public void setBiome(String newBiome)
	{
		biome = newBiome;
	}
	
	/**
	 * Establece la posición del mapa en el mundo.
	 * @param newPos La nueva posición.
	 */
	public void setPos(Vector2 newPos)
	{
		pos = newPos;
	}
	
	public Vector2 pos()
	{
		return pos;
	}
	
	public MapTile[][] tiles()
	{
		return tiles;
	}
	
	public GameCharacter[][] charactersGrid()
	{
		return characters;
	}
	
	/**
	 * Obtiene los ids de los personajes y los introduce en una matriz.
	 * 
	 * @return Matriz de ints con las ids de los personajes.
	 */
	public int[][] characterLayout()
	{
		int[][] ret = new int[characters.length][characters[0].length];
		
		for(int i = 0; i < characters.length; i++)
		{
			for(int j = 0; j < characters[i].length; j++)
			{
				if(characters[i][j] != null)
				{
					ret[i][j] = characters[i][j].Id();
				}
				else
				{
					ret[i][j] = 0;
				}
			}
		}
		
		return ret;
	}
	
	public String toString()
	{
		String data = new String();
		
		data = data + biome + '\n';
		
		data = data + pos.row() + ' ' + pos.col() + '\n';
		
		for(int i = 0; i < GameMap.MAP_HEIGHT; i++)
		{
			for(int j = 0; j < GameMap.MAP_WIDTH; j++)
			{
				data = data + tiles[i][j].id + ' ';
			}
			
			data = data + '\n';
		}
		
		return data;
	}
	
	/**
	 * Busca el PlayableCharacter y lo retorna si lo encuentra.
	 * @return El PlayableCharcter o null en caso de no encontrarlo.
	 */
	public PlayableCharacter getPlayableCharacter()
	{
		for(int i = 0; i < characters.length; i++)
		{
			for(int j = 0; j < characters[i].length; j++)
			{
				if(characters[i][j] instanceof PlayableCharacter)
				{
					return (PlayableCharacter) characters[i][j];
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Obtiene todos los personajes no jugables del mapa.
	 * @return ArrayList con todos los personajes no jugables.
	 */
	public ArrayList<GameCharacter> getNonPlayableCharacters()
	{
		// TODO Auto-generated method stub
		
		ArrayList<GameCharacter> enemies = new ArrayList<>();
		
		for(int i = 0; i < characters.length; i++)
		{
			for(int j = 0; j < characters[i].length; j++)
			{
				if(characters[i][j]  != null &&  !(characters[i][j] instanceof PlayableCharacter))
				{
					enemies.add(characters[i][j]);
				}
			}
		}
			
		return enemies;
	}
	
	/**
	 * 
	 * @return true si en el mapa no quedan enemigos, false en caso contrario.
	 */
	public boolean isClear()
	{
		return clear;
	}
	
	public void setClear(boolean status)
	{
		clear = status;
	}
	
	/**
	 * 
	 * @return true si el mapa ha sido visitado por el jugador, false en caso contrario.
	 */
	public boolean isVisited()
	{
		return visited;
	}
	
	public void setVisited(boolean status)
	{
		visited = status;
	}

	/**
	 * 
	 * @return Un numero de sprite.
	 */
	public int status()
	{
		// TODO Auto-generated method stub
		if(!visited)
		{
			return 18;
		}
		else if(!clear)
		{
			return 19;
		}
		else
		{
			return 20;
		}
	}
	
	/**
	 * Coloca la hoguera.
	 */
	public void placeBonfire()
	{
		tiles[8][8] = new Bonfire();
		
		if(Game.instance().player().pos().equals(new Vector2(8, 8)))
		{
			tiles[8][8].stepInto(Game.instance().player());
		}
	}
}
