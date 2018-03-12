package General;

import java.util.ArrayList;
import java.util.Scanner;

import MapStuff.Exit;
import MapStuff.GameMap;
import MapStuff.MapTile;
import MapStuff.Trap;
import WorldStuff.World;
import WorldStuff.WorldCfg;
import WorldStuff.WorldTile;

public class DataParser 
{
	public static MapTile[][] parseMapChunk(String data)
	{
		MapTile[][] ret = new MapTile[GameMap.MAP_HEIGHT][GameMap.MAP_WIDTH];
		
		Scanner reader = new Scanner(data);
		
		for(int i = 0; i < ret.length; i++)
		{
			for(int j = 0; j < ret[0].length; j++)
			{
				int id = reader.nextInt();
				
				ret[i][j] = parseMapTile(FileManager.instance().readFile(FileType.MAP_TILE, String.valueOf(id)));
			}
		}
		
		reader.close();
		
		return ret;
	}
	
	public static MapTile parseMapTile(String data)
	{
		MapTile ret = null;
		
		Scanner reader = new Scanner(data);
		
		reader.next();
		
		String tileType = reader.next();
		
		if(tileType.toLowerCase().equals("default"))
		{
			ret = new MapTile();
			
			while(reader.hasNextLine())
			{
				String label = reader.next();
				
				if(label.toLowerCase().equals("sprite:"))
				{
					ret.setImage(reader.next());
				}
				else if(label.toLowerCase().equals("collider:"))
				{
					if(reader.next().toLowerCase().equals("true"))
					{
						ret.setCollider(true);
					}
					else
					{
						ret.setCollider(false);
					}
				}
				else
				{
					reader.next();
					System.err.println("Error en el archivo");
				}
			}
		}
		else if(tileType.toLowerCase().equals("exit"))
		{
			Exit temp = new Exit();
			
			while(reader.hasNextLine())
			{
				String label = reader.next();
				
				if(label.toLowerCase().equals("sprite:"))
				{
					temp.setImage(reader.next());
				}
				else if(label.toLowerCase().equals("collider:"))
				{
					if(reader.next().toLowerCase().equals("true"))
					{
						temp.setCollider(true);
					}
					else
					{
						temp.setCollider(false);
					}
				}
				else if(label.toLowerCase().equals("nextmap:"))
				{
					temp.setNextMap(reader.next());
				}
				else if(label.toLowerCase().equals("exitcondition:"))
				{
					temp.setExitCondition(new Vector2(reader.nextInt(), reader.nextInt()));
					reader.nextLine();
				}
				else
				{
					reader.next();
					System.err.println("Error en el archivo");
				}
			}
			
			ret = temp;
		}
		else if(tileType.toLowerCase().equals("trap"))
		{
			Trap temp = new Trap();
			
			while(reader.hasNextLine())
			{
				String label = reader.next();
				
				if(label.toLowerCase().equals("sprite:"))
				{
					temp.setImage(reader.next());
				}
				else if(label.equals("collider:"))
				{
					if(reader.next().toLowerCase().equals("true"))
					{
						temp.setCollider(true);
					}
					else
					{
						temp.setCollider(false);
					}
				}
				else if(label.toLowerCase().equals("damage:"))
				{
					temp.setDamage(Float.parseFloat(reader.next()));
				}
				else if(label.toLowerCase().equals("effect:"))
				{
					temp.setEffect(Integer.parseInt(reader.next()));
				}
				else if(label.toLowerCase().equals("effectamount:"))
				{
					temp.setEffectAmount(Integer.parseInt(reader.next()));
				}
				else
				{
					reader.next();
					System.err.println("Error en el archivo");
				}
			}
			
			ret = temp;
		}
		
		reader.close();
		
		return ret;
	}
	
	public static WorldCfg[] parseWorldCfg(String data)
	{
		Scanner reader = new Scanner(data);
		
		int presetcount = Functions.ocurrencies(data.toUpperCase(), "END");
		
		WorldCfg[] presets = new WorldCfg[presetcount];
		
		for(int i = 0; i < presets.length; i++)
		{
			presets[i] = new WorldCfg();
		}
		
		for(int i = 0; i < presets.length; i++)
		{
			while(true)
			{
				String label = reader.next();
				
				if(label.toUpperCase().equals("END")) break;
				
				if(label.toLowerCase().equals("name:"))
				{
					presets[i].name = reader.next();
				}
				else if(label.toLowerCase().equals("height:"))
				{
					presets[i].height = Integer.parseInt(reader.next());
				}
				else if(label.toLowerCase().equals("width:"))
				{
					presets[i].width = Integer.parseInt(reader.next());
				}
				else if(label.toLowerCase().equals("biomes:"))
				{
					for(int j = 0; j < presets[i].biomes.length; j++)
					{
						presets[i].biomes[j] = reader.next();
					}
				}
				else
				{
					reader.next();
					System.err.println("Error en el archivo");
				}
			}
		}
		
		reader.close();
		
		return presets;
	}
	
	public static ArrayList<String> parseList(String data)
	{
		ArrayList<String> ret = new ArrayList<>();
		
		Scanner reader = new Scanner(data);
		
		while(reader.hasNext())
		{
			ret.add(reader.next());
		}
		
		reader.close();
		
		return ret;
	}
}
