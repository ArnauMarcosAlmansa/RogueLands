package WorldStuff;

import java.util.Random;
import java.util.Scanner;

import General.Sorter;
import General.Vector2;
import MapStuff.GameMap;
import MapStuff.GameMapBuilder;
/**
 * Generador de mundos.
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */
public class WorldBuilder
{
	private int WORLD_HEIGHT = 16;
	
	private int WORLD_WIDTH = 16;
	
	private GameMapScheme[][] worldGrid;
	
	private String[] biomes;
		
	private Vector2 startingTilePos;
	
	private World product;
	
	private WorldCfg scheme;
	
	private GameMap[][] maps;
	
	/**
	 * 
	 * @param cfg Esquema básico a partir del cual se genera el mundo.
	 */
	public WorldBuilder(WorldCfg cfg)
	{
		this.WORLD_HEIGHT = cfg.height;
		this.WORLD_WIDTH = cfg.width;
		
		this.biomes = cfg.biomes;		
		
		worldGrid = new GameMapScheme[WORLD_HEIGHT][WORLD_WIDTH];
		
		maps = new GameMap[WORLD_HEIGHT][WORLD_WIDTH];
		
		scheme = cfg;
	}
	
	/**
	 * Crea el mundo.
	 * @return Un objeto World.
	 */
	public World build()
	{	
		generate();
		
		while(true)
		{			
			if(countWorldTiles() > WORLD_HEIGHT * WORLD_WIDTH / 10 * 6)
			{
				break;
			}
			else
			{
				complete();
			}
		}
		
		correctDoors();
		
		locate();
		
		setBiomes();
				
		createGameMaps();
		
		maps[startingTilePos.row()][startingTilePos.col()].setClear(true);
		maps[startingTilePos.row()][startingTilePos.col()].setVisited(true);
		
		//System.out.println(this);
		
		product = new World(maps);
		
		return product;
	}
	
	private void generate()
	{
		startingTilePos = new Vector2();
		
		startingTilePos.randomize(
				WORLD_HEIGHT / 4, 
				WORLD_HEIGHT / 4 * 3, 
				WORLD_WIDTH / 4, 
				WORLD_WIDTH / 4 * 3
				);
		
		worldGrid[startingTilePos.row()][startingTilePos.col()] = new GameMapScheme();
		
		int[] startingExits = {1,1,1,1};
		
		worldGrid[startingTilePos.row()][startingTilePos.col()].setExitsOnBorders(startingExits);
		
		
		expand(startingTilePos.sum(0, 1), 3);
		
		expand(startingTilePos.sum(0, -1), 1);
		
		expand(startingTilePos.sum(1, 0), 0);
		
		expand(startingTilePos.sum(-1, 0), 2);
		
	}
	
	private void complete()
	{
		
		Vector2 startingChunk = emptiestChunk();

		Vector2 startingTile = new Vector2();
		
		Random rand = new Random();
		
		while(true)
		{
			startingTile.randomize(startingChunk.row() * 4, 
					startingChunk.row() * 4 + 4, 
					startingChunk.col() * 4, 
					startingChunk.col() * 4 + 4
					);
			
			if(worldGrid[startingTile.row()][startingTile.col()] == null && countTilesAround(startingTile) > 0)
			{
				break;
			}
		}
		
		int direction = -1;
		
		while(true)
		{
			direction = rand.nextInt(4);
			
			Vector2 delta = Vector2.createFromDirection(direction, 1);
			
			delta.add(startingTile);
			
			if(delta.isInsideGrid(WORLD_HEIGHT, WORLD_WIDTH))
			{
				if(worldGrid[delta.row()][delta.col()] != null)
				{
					break;
				}
			}
		}

		expand(startingTile, direction);
	}
	
	private Vector2 emptiestChunk()
	{
		Vector2 chunk = new Vector2(-1, -1);
		
		int last = 0;
		
		for(int row = 0; row < WORLD_HEIGHT / 4; row++)
		{
			for(int column = 0; column < WORLD_WIDTH / 4; column++)
			{
				
				int emptyTiles = 0;
				
				for(int i = row * 4; i < row * 4 + 4; i++)
				{
					for(int j = column * 4; j < column * 4 + 4; j++)
					{
						if(worldGrid[i][j] == null)
						{
							emptyTiles++;
						}
					}
				}
				
				if(emptyTiles < 4 * 4 && emptyTiles > 0)
				{
					if(emptyTiles > last)
					{
						last = emptyTiles;
						
						chunk.set(row, column);
					}
				}
				else if(emptyTiles == 4 * 4)
				{
					if(hasValidChunksNext(new Vector2(row, column)))
					{
						chunk.set(row, column);
					}
				}
			}
		}
		
		return chunk;
	}
	
	private boolean hasValidChunksNext(Vector2 chunk) 
	{
		// TODO Auto-generated method stub
		boolean hasValidChunks = false;
		
		rowloop: for(int i = chunk.row() * 4; i < chunk.row() * 4 + 4; i++)
		{
			for(int j = chunk.col() * 4; j < chunk.col() * 4 + 4; j++)
			{
				//System.out.println(countTilesAround(new Vector2(i, j)));
				
				if(countTilesAround(new Vector2(i, j)) > 0)
				{
					hasValidChunks = true;
					break rowloop;
				}
			}
		}
		
		return hasValidChunks;
	}

	private void expand(Vector2 currentTile, int comesFrom)
	{	
		/*System.out.println(this);
		System.out.println(currentTile + "\n" + comesFrom);
		*/
		try
		{
			//SI LA CASILLA ACTUAL ESTA VACÍA
			if(worldGrid[currentTile.row()][currentTile.col()] == null)
			{
				//CREA UNA CASILLA
				worldGrid[currentTile.row()][currentTile.col()] = new GameMapScheme();
				
				int numOfExits;
				
				int[] exits;
				
				int tilesAround = countTilesAround(currentTile);
				
				//SI NO ES UNA CASILLA DEL BORDE
				if(!currentTile.isBorder(WORLD_HEIGHT, WORLD_WIDTH))
				{
				
					numOfExits = GameMapScheme.numOfExits(tilesAround, false);
					
					exits = new int[4];
										
					for(int i = 0; i < numOfExits; i++)
					{
						exits[i] = 1;
					}
					
					//System.out.println(this);
					//System.out.println(numOfExits);
					
					do
					{
						Sorter.shuffle(exits);
					}
					while(exits[comesFrom] == 0);
				}
				else //SI ES UNA CASILLA DEL BORDE
				{
					numOfExits = GameMapScheme.numOfExits(tilesAround, true);
					
					exits = new int[4];
					
					int[] borders = currentTile.borders(WORLD_WIDTH, WORLD_HEIGHT);
					
					
					
					for(int i = 0; i < numOfExits; i++)
					{
						exits[i] = 1;
					}

					//BUG AQUÍ ABAJO
					while(true)
					{
						Sorter.shuffle(exits);

						if(exits[comesFrom] > 0 && exits[borders[0]] == 0 && exits[borders[1]] == 0) break;
					}
					
					//BUG AQUÍ ARRIBA
				}
					worldGrid[currentTile.row()][currentTile.col()].setExitsOnBorders(exits);
					
					if(numOfExits == 1) return;

					if(exits[0] > 0)
					{
						expand(currentTile.sum(-1, 0), 2);
					}
					
					if(exits[1] > 0)
					{
						expand(currentTile.sum(0, 1), 3);
					}
					
					if(exits[2] > 0)
					{
						expand(currentTile.sum(1, 0), 0);
					}
					
					if(exits[3] > 0)
					{
						expand(currentTile.sum(0, -1), 1);
					}
				
			}
			else
			{
				return;
			}
		
		}
		catch(Exception e)
		{
			return;
		}
		
		//METO RETURNS POR SI ACASO
		return;
	}
	
	private void correctDoors()
	{
		for(int i = 0; i < WORLD_HEIGHT; i++)
		{
			for(int j = 0; j < WORLD_WIDTH; j++)
			{	
				if(worldGrid[i][j] != null)
				{
					for(int orientation = 0; orientation < 4; orientation++)
					{
						if(worldGrid[i][j].exitsOnBorders()[orientation] > 0)
						{
							Vector2 pos = new Vector2(i, j);
							
							pos.add(Vector2.createFromDirection(orientation, 1));
							
							try
							{
								worldGrid[pos.row()][pos.col()].setExitsOnBorders(1, reverseOrientation(orientation));
							}
							catch(Exception e)
							{

							}
						}
					}
				}
			}
		}

	}
	
	public String toString()
	{
		String ret = new String();
		
		for(int i = 0; i < WORLD_HEIGHT; i++)
		{
			String interlinea = new String();
			
			for(int j = 0; j < WORLD_WIDTH; j++)
			{
				int a = 0;
				
				if(worldGrid[i][j] != null)
				{
					a = 1;
					
					if(worldGrid[i][j].exitsOnBorders()[2] > 0)
					{
						interlinea = interlinea + "| ";
					}
					else
					{
						interlinea = interlinea + "  ";
					}
					
					ret = ret + a;
					
					if(worldGrid[i][j].exitsOnBorders()[1] > 0)
					{
						ret = ret + '-';
					}
					else
					{
						ret = ret + ' ';
					}
				}
				else
				{
					ret = ret + "0 ";
					interlinea = interlinea + "  ";
				}
				
			}
			
			ret = ret + '\n' +interlinea + '\n';
		}
		
		return ret;
	}
	
	private int countWorldTiles()
	{
		int count = 0;
		
		for(int i = 0; i < WORLD_HEIGHT; i++)
		{
			for(int j = 0; j < WORLD_WIDTH; j++)
			{	
				if(worldGrid[i][j] != null)
				{
					count++;
				}
			}
		}
		
		return count;
	}
	
	
	//UNUSED
	private void connectTilesAround(Vector2 pos)
	{
		try
		{
			if(worldGrid[pos.row() + 1][pos.col()] != null)
			{
				worldGrid[pos.row()][pos.col()].setExitsOnBorders(1, 0);
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		try
		{
			if(worldGrid[pos.row() - 1][pos.col()] != null)
			{
				worldGrid[pos.row()][pos.col()].setExitsOnBorders(1, 2);
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		try
		{
			if(worldGrid[pos.row()][pos.col() + 1] != null)
			{
				worldGrid[pos.row()][pos.col()].setExitsOnBorders(1, 3);
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		try
		{
			if(worldGrid[pos.row()][pos.col() - 1] != null)
			{
				worldGrid[pos.row()][pos.col()].setExitsOnBorders(1, 1);
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	private int countTilesAround(Vector2 pos)
	{
		int count = 0;
		
		try
		{
			if(worldGrid[pos.row() + 1][pos.col()] != null)
			{
				count++;
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		try
		{
			if(worldGrid[pos.row() - 1][pos.col()] != null)
			{
				count++;
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		try
		{
			if(worldGrid[pos.row()][pos.col() + 1] != null)
			{
				count++;
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		try
		{
			if(worldGrid[pos.row()][pos.col() - 1] != null)
			{
				count++;
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return count;
	}
	
	public GameMapScheme getTile(int row, int col)
	{
		return worldGrid[row][col];
	}
	
	private void cleanGrid()
	{
		for(int i = 0; i < WORLD_HEIGHT; i++)
		{
			for(int j = 0; j < WORLD_WIDTH; j++)
			{	
				worldGrid[i][j] = null;
			}
		}
	}
	
	private int reverseOrientation(int current)
	{
		int reverse = -1;
		
		if(current == 0)
		{
			reverse = 2;
		}
		else if(current == 1)
		{
			reverse = 3;
		}
		else if(current == 2)
		{
			reverse = 0;
		}
		else if(current == 3)
		{
			reverse = 1;
		}
		
		return reverse;
	}

	private void setBiomes()
	{
		Random rand = new Random();
		
		int northFrontier = WORLD_HEIGHT / 3;
		
		int southFrontier = WORLD_HEIGHT / 3 * 2;
		
		int frontiers[] = {northFrontier, southFrontier};
		
		Scanner input = new Scanner(System.in);
		
		for(int biome = 0; biome < biomes.length; biome++)
		{
			if(biome < biomes.length - 1)
			{
				int noise = 0;
				
				for(int col = 0; col < WORLD_WIDTH; col++)
				{
					try
					{
						if(col == 0)
						{
							noise = rand.nextInt(3) - 1;
							
							worldGrid[frontiers[biome] + noise][col].setBiome(biomes[biome]);
						}
						else
						{
							if(noise == -1)
							{
								noise = rand.nextInt(2) - 1;
							}
							else if(noise == 0)
							{
								noise = rand.nextInt(3) - 1;
							}
							else
							{
								noise = rand.nextInt(2);
							}

							worldGrid[frontiers[biome] + noise][col].setBiome(biomes[biome]);
						}
						
						int row = frontiers[biome] + noise - 1;
						
						while(true)
						{
							if(row < 0) break;
							
							try
							{
								if(worldGrid[row][col].biome() == null)
								{
									worldGrid[row][col].setBiome(biomes[biome]);
									row--;
								}
								else
								{
									break;
								}
							}
							catch(ArrayIndexOutOfBoundsException e)
							{
								System.out.println("cosa");
								row--;
								break;
							}
							catch(NullPointerException e)
							{
								row--;
								continue;
							}
						}
						
						
						
					}
					catch(Exception e)
					{
						//e.printStackTrace();
					}
				}
			}
			else
			{
				for(int col = 0; col < WORLD_WIDTH; col++)
				{
					int row = WORLD_HEIGHT - 1;
					
					while(true)
					{
						if(row < 0) break;
						
						try
						{
							if(worldGrid[row][col].biome() == null)
							{
								worldGrid[row][col].setBiome(biomes[biome]);
								row--;
							}
							else
							{
								break;
							}
						}
						catch(ArrayIndexOutOfBoundsException e)
						{
							System.out.println("cosa");
							row--;
							break;
						}
						catch(NullPointerException e)
						{
							row--;
							continue;
						}
					}
				}
			}
		}
	}
	
	public String getBiomeName(int n)
	{
		return biomes[n];
	}
	
	private void locate()
	{
		for(int i = 0; i < WORLD_HEIGHT; i++)
		{
			for(int j = 0; j < WORLD_WIDTH; j++)
			{
				if(worldGrid[i][j] != null)
				{
					worldGrid[i][j].setPos(new Vector2(i, j));
				}
			}
		}
	}
	
	private void createGameMaps()
	{
		for(int i = 0; i < WORLD_HEIGHT; i++)
		{
			for(int j = 0; j < WORLD_WIDTH; j++)
			{
				if(worldGrid[i][j] != null)
				{
					if(startingTilePos.equals(new Vector2(i, j)))
					{
						GameMapBuilder mapBuilder = new GameMapBuilder(worldGrid[i][j], true);
						
						GameMap map = mapBuilder.build();
						
						maps[i][j] = map;
					}
					else
					{
						GameMapBuilder mapBuilder = new GameMapBuilder(worldGrid[i][j]);
						
						GameMap map = mapBuilder.build();
						
						maps[i][j] = map;
					}
				}
			}
		}
	}
}
