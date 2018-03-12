package MapStuff;

import java.util.Random;

import General.FileManager;
import General.FileType;
import General.Vector2;
import WorldStuff.WorldTile;

public class GameMap 
{
	public static final int MAP_HEIGHT = 16;
	public static final int MAP_WIDTH = 16;
	
	private MapTile[][] tiles;
	
	private String biome;
	
	private Vector2 pos;
	
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
	
	private void initiateTiles()
	{
		tiles = new MapTile[MAP_HEIGHT][MAP_WIDTH];
		
		for(int i = 0; i < tiles.length; i++)
		{
			for(int j = 0; j < tiles[i].length; j++)
			{
				tiles[i][j] = null;
			}
		}
	}
	
	public void generateFromWorldTile(WorldTile scheme)
	{
		initiateTiles();
		
		biome = scheme.biome();
		
		if(biome == null) System.out.println("error");
		
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
					tiles[i][j].setId(1);
				}
				else if(rand.nextInt(8) < 7)
				{
					tiles[i][j] = new MapTile();
					tiles[i][j].setCollider(false);
					tiles[i][j].setId(0);

				}
				else
				{
					tiles[i][j] = new Trap();
				}
			}
		}
		
		if(exits[0] > 0)
		{
			tiles[0][7] = new Exit();
			tiles[0][8] = new Exit();
		}
		
		if(exits[1] > 0)
		{
			tiles[7][tiles[0].length - 1] = new Exit();
			tiles[8][tiles[0].length - 1] = new Exit();
		}
		
		if(exits[2] > 0)
		{
			tiles[tiles.length - 1][7] = new Exit();
			tiles[tiles.length - 1][8] = new Exit();
		}
		
		if(exits[3] > 0)
		{
			tiles[7][0] = new Exit();
			tiles[8][0] = new Exit();
		}
		
		FileManager.instance().saveFile(this.toString(), pos.row() + "-" + pos.col() + ".map", FileType.GAME_MAP);
	}
	
	public void setBiome(String newBiome)
	{
		biome = newBiome;
	}
	
	public MapTile[][] tiles()
	{
		return tiles;
	}
	
	public String toString()
	{
		String data = new String();
		
		data = data + biome + '\n';
		
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
}
