package Graphics;

import java.io.FileNotFoundException;

import General.DataParser;
import General.FileManager;
import General.FileType;

public class Sprites
{
	
	private static Sprites instance;
	
	public static final int SPRITES_LENGTH = 4000;
	
	private String[] sprites = new String[SPRITES_LENGTH];
	
	private Sprites()
	{
		load();
	}
	
	private void load()
	{
		try
		{
			sprites = DataParser.parseSprites(FileManager.instance().readFile(FileType.SPRITES, "sprites.cfg"));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("no hay archivo de sprites");
			System.exit(1);
		}
	}
	
	public String[] sprites()
	{
		return sprites;
	}
	
	public static Sprites instance()
	{
		if(instance == null)
		{
			instance = new Sprites();
			
			
		}
		
		return instance;
	}
}
