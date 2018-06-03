package WorldStuff;
import java.util.Random;

import General.Vector2;
import MapStuff.GameMap;
/**
 * Esquema de GameMap.
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */
public class GameMapScheme 
{
	private int[] exitsOnBorders;
	
	private boolean hasDungeon = false;
	
	private String biome = null;
	
	private Vector2 pos = null;
	
	public GameMapScheme()
	{
		this.exitsOnBorders = new int[4]; 
	}
	
	public void setExitsOnBorders(int[] exitArray)
	{
		this.exitsOnBorders = exitArray;
	}
	
	public void setExitsOnBorders(int exits, int border)
	{
		this.exitsOnBorders[border] = exits;
	}
	
	public void setDungeonFlag(boolean flag)
	{
		this.hasDungeon = flag;
	}
	
	public void setBiome(String newBiome)
	{
		this.biome = newBiome;
	}
	
	public void setPos(Vector2 newPos)
	{
		pos = newPos;
	}
	
	public int[] exitsOnBorders()
	{
		return this.exitsOnBorders;
	}
	
	public String biome()
	{
		return this.biome;
	}
	
	public boolean hasDungeon()
	{
		return this.hasDungeon;
	}
	
	public Vector2 pos()
	{
		return pos;
	}
	
	public static int numOfExits(int tilesAround, boolean isBorder)
	{
		/* 50% --> 2
		 * 35% --> 3
		 * 15% --> 4
		 */
		
		int exits;
		
		Random rand = new Random();
		
		if(isBorder)
		{
			int num = rand.nextInt(4);
			if(tilesAround > 1)
			{
				exits = 1;
			}
			else
			{
				if(num == 0)
				{
					exits = 2;
				}
				else
				{
					exits = 1;
				}
			}
		}
		else
		{
			int num;
			
			if(tilesAround == 4)
			{
				exits = 1;
			}
			else if(tilesAround == 3)
			{
				num = rand.nextInt(3);
				
				if(num == 0)
				{
					exits = 1;
				}
				else
				{
					exits = 2;
				}
			}
			else if(tilesAround == 2)
			{
				num = rand.nextInt(10);
				
				if(num == 0)
				{
					exits = 1;
				}
				else if(num < 7)
				{
					exits = 2;
				}
				else
				{
					exits = 3;
				}
			}
			else
			{
				num = rand.nextInt(20);
				
				if(num == 0)
				{
					exits = 1;
				}
				else if(num < 10)
				{
					exits = 2;
				}
				else if(num < 17)
				{
					exits = 3;
				}
				else
				{
					exits = 4;
				}
			}
		}
		
		return exits;
	}
	
	public String toString()
	{
		String ret = new String();
		
		ret = "Exits to " + exitsOnBorders[0] + " " + exitsOnBorders[1] + " " 
				+ exitsOnBorders[2] + " " + exitsOnBorders[3] 
				+ "\nDungeonFlag: " + hasDungeon 
				+ "\nTheme: " + biome;
		
		return ret;
	}
}
