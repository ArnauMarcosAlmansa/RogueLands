package MapStuff;

import java.util.ArrayList;
import java.util.Random;

import CharacterStuff.EnemyCharacter;
import CharacterStuff.EnemyFactory;
import CharacterStuff.GameCharacter;
import CharacterStuff.PlayableCharacter;
import General.Vector2;
import WorldStuff.GameMapScheme;
/**
 * 
 * Creador de GameMaps.
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */
public class GameMapBuilder
{
	private MapTile[][] tiles;
	
	private GameCharacter[][] characters;
	
	private String biome;
	
	private Vector2 pos;
	
	private GameMapScheme scheme;
	
	GameMap product;
	
	boolean isStartingMap = false;
	
	/**
	 * 
	 * @param scheme Esquema del mapa que se va a crear.
	 */
	public GameMapBuilder(GameMapScheme scheme)
	{
		
		characters = new GameCharacter[GameMap.MAP_HEIGHT][GameMap.MAP_WIDTH];
		this.scheme = scheme;
	}
	
	/**
	 * 
	 * @param scheme Esquema del mapa que se va a crear.
	 * @param b Booleano que indica si el mapa a crear seerá el mapa de inicio.
	 */
	public GameMapBuilder(GameMapScheme scheme, boolean b)
	{
		// TODO Auto-generated constructor stub
		characters = new GameCharacter[GameMap.MAP_HEIGHT][GameMap.MAP_WIDTH];
		this.scheme = scheme;
		
		isStartingMap = b;
	}

	/**
	 *  Genera el mapa.
	 * @return El GameMap creado.
	 */
	public GameMap build()
	{
		generate();
		
		if(isStartingMap)
		{
			PlayableCharacter temp = new PlayableCharacter(new Vector2(8, 8));
			
			this.characters[8][8] = temp;
		}
		else
		{
			placeEnemies();
		}
		
		return new GameMap(tiles, characters, biome, pos);
	}
	
	/**
	 * Coloca los enemigos.
	 */
	private void placeEnemies()
	{
		// TODO Auto-generated method stub
		ArrayList<Vector2> tempEnemiesPositions = new ArrayList<>();
		
		Random rand = new Random();
		
		int enemyType = rand.nextInt(2);
		
		EnemyFactory creator = new EnemyFactory();
		
		int enemyNumber = creator.howMany(enemyType);
		
		for(int i = 0; i < enemyNumber; i++)
		{
			Vector2 pos = new Vector2();
			
			while(true)
			{
				pos.randomize(4, 12, 4, 12);
				
				if(!tiles[pos.row()][pos.col()].isCollider()) break;
			}
			
			tempEnemiesPositions.add(pos);
		}
		
		for(int i = 0; i < tempEnemiesPositions.size(); i++)
		{
			Vector2 temp = tempEnemiesPositions.get(i);
			
			characters[temp.row()][temp.col()] = creator.create(enemyType ,temp);
		}
	}
	
	private void initiateTiles()
	{
		tiles = new MapTile[GameMap.MAP_HEIGHT][GameMap.MAP_WIDTH];
		
		for(int i = 0; i < tiles.length; i++)
		{
			for(int j = 0; j < tiles[i].length; j++)
			{
				tiles[i][j] = null;
				
				characters[i][j] = null;
			}
		}
	}
	
	/**
	 * Genera el mapa a partir del esquema.
	 */
	public void generate()
	{
		initiateTiles();
		
		biome = scheme.biome();
		
		if(biome == null) System.out.println("error");
		
		int id = 0;
		
		if(biome.equals("wood"))
		{
			id = 1;
		}
		else if(biome.equals("snow"))
		{
			id = 3;
		}
		else
		{
			id = 5;
		}
		
		pos = scheme.pos();
		
		Random rand = new Random();
		
		int[] exits = scheme.exitsOnBorders();
		
		for(int i = 0; i < tiles.length; i++)
		{
			for(int j = 0; j < tiles[i].length; j++)
			{
				if(i == 0 || j == 0 || i == tiles.length - 1 || j == tiles[0].length - 1)
				{
					tiles[i][j] = new MapTile();
					tiles[i][j].setCollider(true);
					tiles[i][j].setId(id + 1);
				}
				else
				{
					tiles[i][j] = new MapTile();
					tiles[i][j].setCollider(false);
					tiles[i][j].setId(id);

				}
			}
		}
		
		ArrayList<Vector2> decoration = new ArrayList<Vector2>();
		
		while(decoration.size() < 6)
		{
			Vector2 temp = new Vector2();
			
			temp.randomize(2, GameMap.MAP_HEIGHT - 2, 2, GameMap.MAP_WIDTH - 2);
			
			if(temp.equals(new Vector2(8, 8))) continue;
			
			decoration.add(temp);
		}
		
		
		for(int i = 0; i < decoration.size(); i++)
		{
			Vector2 temp = decoration.get(i);
			
			tiles[temp.row()][temp.col()].setId(id + 1);
			tiles[temp.row()][temp.col()].setCollider(true);
		}
		
		if(exits[0] > 0)
		{
			tiles[0][7] = new Exit(new Vector2(-1, 0));
			tiles[0][7].setCollider(false);
			tiles[0][7].setId(id);
			
			tiles[0][8] = new Exit(new Vector2(-1, 0));
			tiles[0][8].setCollider(false);
			tiles[0][8].setId(id);
		}
		
		if(exits[1] > 0)
		{
			tiles[7][tiles[0].length - 1] = new Exit(new Vector2(0, 1));
			tiles[7][tiles[0].length - 1].setCollider(false);
			tiles[7][tiles[0].length - 1].setId(id);
			
			tiles[8][tiles[0].length - 1] = new Exit(new Vector2(0, 1));
			tiles[8][tiles[0].length - 1].setCollider(false);
			tiles[8][tiles[0].length - 1].setId(id);
			
		}
		
		if(exits[2] > 0)
		{
			tiles[tiles.length - 1][7] = new Exit(new Vector2(1, 0));
			tiles[tiles.length - 1][7].setCollider(false);
			tiles[tiles.length - 1][7].setId(id);
			
			tiles[tiles.length - 1][8] = new Exit(new Vector2(1, 0));
			tiles[tiles.length - 1][8].setCollider(false);
			tiles[tiles.length - 1][8].setId(id);
			
		}
		
		if(exits[3] > 0)
		{
			tiles[7][0] = new Exit(new Vector2(0, -1));
			tiles[7][0].setCollider(false);
			tiles[7][0].setId(id);
					
			tiles[8][0] = new Exit(new Vector2(0, -1));
			tiles[8][0].setCollider(false);
			tiles[8][0].setId(id);
		}
	}
}
