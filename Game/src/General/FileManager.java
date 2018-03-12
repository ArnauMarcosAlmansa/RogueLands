package General;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.Scanner;

import MapStuff.GameMap;
import WorldStuff.WorldCfg;

public class FileManager 
{
	private String worldPresetsPath;
	
	private String savesPath;
	
	private String mapTilePresetsPath;
	
	private String currentPlayer = "arnau";
	
	private String gameMapPath;
	
	private static FileManager instance;
	
	/*
	public FileManager()
	{
		readPaths();
	}
	*/
	
	public void readPaths()
	{
		File main = new File("paths.cfg");
		
		Scanner fileReader = null;
		
		try 
		{
			fileReader = new Scanner(main);
		}
		catch(FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("paths.cfg file not found");
			return;
		}
		
		while(true)
		{
			String label = fileReader.next();
			
			if(label.equals("END")) break;
			
			if(label.charAt(0) == '#')
			{
				fileReader.nextLine();
				continue;
			}
			
			
			if(label.equals("worldPresets:"))
			{
				worldPresetsPath = fileReader.next();
				System.out.println(worldPresetsPath);
			}
			else if(label.equals("saves:"))
			{
				savesPath = fileReader.next();
			}
			else if(label.equals("gameMaps:"))
			{
				gameMapPath = fileReader.next();
			}
		}
		
		fileReader.close();
	}
	
	private void createFolders()
	{
		File temp = new File(this.getPathFromType(FileType.GAME_MAP));
		temp.mkdirs();
	}
	
	public WorldCfg loadWorldPreset(int preset)
	{
		WorldCfg cfg = new WorldCfg();
		
		return cfg;
	}
	
	public boolean saveFile(String data, String fileName, FileType fileType)
	{
		String path = getPathFromType(fileType) + fileName + "\\";
		/*
		File temp = new File(path);
		temp.mkdirs();
		*/
		PrintWriter writer = null;
		
		try 
		{
			writer = new PrintWriter(path, "UTF-8");
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		writer.print(data);
		
		writer.close();
		
		return true;
	}
	
	public String readFile(FileType type, String name) 
	{
		String path = new String();
		
		path = getPathFromType(type) + name;
		
		System.out.println(path);
		
		File fileToRead = new File(path);
		
		Scanner reader = null;
		
		try 
		{
			reader = new Scanner(fileToRead);
		} 
		catch(FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "";
		}
		
		String data = new String();
		
		while(reader.hasNextLine())
		{
			String line = reader.nextLine();
			
			if(line.trim().charAt(0) == '#')
			{
				//DO NOTHING BECAUSE IT'S A COMMENT
				//HAVE A NICE DAY :)
			}
			else
			{
				data = data + line + '\n';
			}
		}
		
		reader.close();
		
		return data;
	}
	
	private String getPathFromType(FileType type)
	{
		String path = new String();
		
		switch(type)
		{
			case SAVE:
				path = savesPath + '/' + currentPlayer;
				break;
			
			case MAP_TILE:
				path = mapTilePresetsPath;
				break;
				
			case WORLD_PRESET:
				path = worldPresetsPath;
				break;
			case GAME_MAP:
				path = gameMapPath;
				path = currentPlayer + '/' + path;
				break;
			default:
				path = "ERROR";
				break;
		}
		
		
		
		return path;
	}
	
	public static FileManager instance()
	{
		if(instance == null)
		{
			instance = new FileManager();
			instance.readPaths();
			instance.createFolders();
		}
		
		return instance;
	}
}