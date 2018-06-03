package General;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.UnexpectedClass;
import GameEngine.GameManager;
import MapStuff.*;
import WorldStuff.*;
/**
 * 
 * Clase para tratar con archivos.
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */
public class FileManager 
{
	private String worldPresetsPath;
	
	private String savesPath;
	
	private String mapTilePresetsPath;
		
	private String gameMapPath;
	
	private String spritesPath;
	
	private static FileManager instance;
	
	/**
	 * Lee las rutas de los recursos de un archivo.
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
			//e.printStackTrace();
			System.out.println("paths.cfg file not found");
			return;
		}
		
		while(true)
		{
			String label = fileReader.next();
			
			if(label.toUpperCase().equals("END")) break;
			
			if(label.charAt(0) == '#')
			{
				fileReader.nextLine();
				continue;
			}
			
			
			if(label.equals("worldPresets:"))
			{
				worldPresetsPath = fileReader.next();
				//System.out.println(worldPresetsPath);
			}
			else if(label.equals("saves:"))
			{
				savesPath = fileReader.next();
			}
			else if(label.equals("gameMaps:"))
			{
				gameMapPath = fileReader.next();
			}
			else if(label.equals("mapTiles:"))
			{
				mapTilePresetsPath = fileReader.next();
			}
			else if(label.equals("sprites:"))
			{
				spritesPath = fileReader.next();
			}
				
		}
		
		fileReader.close();
	}
	
	/**
	 * Crea la carpeta de usuario.
	 */
	private void createFolders()
	{
		File temp = new File(this.getPathFromType(FileType.GAME_MAP));
		
		temp.mkdirs();
	}
	
	/**
	 * Crea la carpeta de usuario.
	 */
	public void createUserFolders()
	{
		createFolders();
	}
	
	/**
	 * Serializa un objeto.
	 * 
	 * @param obj El objeto a serializar.
	 * @param type El tipo de archivo que se quiere crear.
	 */
	public void serialize(Object obj, FileType type)
	{
		String path;
		try
		{
			path = this.getPathFromType(type) + this.getNameFromObject(obj);
		}
		catch (UnexpectedClass e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return;
		}
		
		try
		{
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(obj);
			
			oos.flush();
			
			oos.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Retorna un nombre de archivo dependiendo del tipo del objecto dado.
	 * 
	 * @param obj Objeto.
	 * @return String con el nombre de archivo.
	 * @throws UnexpectedClass
	 */
	private String getNameFromObject(Object obj) throws UnexpectedClass
	{
		String ret = new String();
		
		if(obj instanceof Save)
		{
			ret = "save.dat";
		}
		else
		{
			 throw new UnexpectedClass("Given object's class did not match any supported class.");
		}
		
		return ret;
	}

	/**
	 * 
	 * @param data String que se escribirá en el archivo.
	 * @param fileName Nombre del archivo a escribir.
	 * @param fileType Tipo del archivo a escribir.
	 * @return true en caso de exito, false en caso de error.
	 */
	public boolean writeFile(String data, String fileName, FileType fileType)
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
			//e.printStackTrace();
			return false;
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		
		writer.print(data);
		
		writer.close();
		
		return true;
	}
	
	/**
	 * Lee un archivo de texto.
	 * 
	 * @param type Tipo del archivo a ser leído.
	 * @param name Nombre del archivo a ser leído.
	 * @return Contenido del archivo.
	 * @throws FileNotFoundException
	 */
	public String readFile(FileType type, String name) throws FileNotFoundException 
	{
		String path = new String();
		
		path = getPathFromType(type) + name;
		
		//System.out.println(path);
		
		File fileToRead = new File(path);
		
		Scanner reader = null;
		
		
		reader = new Scanner(fileToRead);
		
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
	
	/**
	 * Obtiene una ruta de archivo segun el tipo de archivo.
	 * 
	 * @param type Tipo de archivo.
	 * @return Ruta hasta el archivo.
	 */
	private String getPathFromType(FileType type)
	{
		String path = new String();
		
		switch(type)
		{
			case SAVE:
				path = GameManager.instance().currentPlayer() + '/';
				break;
			
			case MAP_TILE:
				path = mapTilePresetsPath;
				break;
				
			case WORLD_PRESET:
				path = worldPresetsPath;
				break;
			case GAME_MAP:
				path = gameMapPath;
				path = GameManager.instance().currentPlayer() + '/' + path;
				break;
			case SPRITES:
				path = spritesPath;
				break;
			default:
				path = "";
				break;
		}
		
		return path;
	}
	
	/**
	 * Elimina todos los archivos de un jugador.
	 * 
	 * @param name Nombre del jugador, que determina la carpeta a borrar.
	 */
	public void deleteGame(String name)
	{
		try
		{
			Files.delete(Paths.get(name + "/save.dat"));
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		
		
		for(int i = 0; i < 16; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				try
				{
					Files.delete(Paths.get(name + "/maps/" + i + "-" + j + ".map"));
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		}
		
		try
		{
			Files.delete(Paths.get(name + "/maps"));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		try
		{
			Files.delete(Paths.get(name));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		GameManager.instance().players().remove(name);
		
		GameManager.instance().updateUsersFile();
	}
	
	/**
	 * 
	 * @return Instancia de FileManager.
	 */
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

	/**
	 * Deserializa un objeto de un archivo dado.
	 * 
	 * @param type Tipo del archivo a deserializar.
	 * @param name Nombre del archivo a deserializar.
	 * @return Objeto deserializado.
	 * @throws FileNotFoundException
	 */
	public Object deserialize(FileType type, String name) throws FileNotFoundException
	{
		// TODO Auto-generated method stub
		String path = getPathFromType(type) + name;
		
		Object ret = null;
		
		try
		{
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			ret = ois.readObject();
			
			ois.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
	/**
	 * Crea un archivo de texto con un contenido dado.
	 * 
	 * @param path Ruta completa del archivo.
	 * @param content Contenido del archivo.
	 */
	public void createTextFile(String path, String content)
	{
		try
		{
			File f = new File(path);
			
			if(f.exists()) return;
			
			FileWriter fis = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fis);
			
			bw.write(content);
			
			bw.newLine();
			
			bw.flush();
			
			bw.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * añade una líne a un archivo CSV.
	 * 
	 * @param path Ruta completa del archivo.
	 * @param line Line a ser escrita.
	 */
	public void addCsvLineTo(String path, String[] line)
	{
		// TODO Auto-generated method stub
		try
		{
			FileWriter fis = new FileWriter(path, true);
			BufferedWriter bw = new BufferedWriter(fis);
			
			for(int i = 0; i < line.length; i++)
			{
				bw.write(line[i]);
				
				if(i < line.length - 1)
				{
					bw.write(",");
				}
			}
			
			bw.newLine();
			
			bw.flush();
			
			bw.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Lee un archivo CSV i retorna su contenido.
	 * 
	 * @param path Ruta del archivo.
	 * @return Matriz con el contenido del archivo.
	 */
	public String[][] readCsv(String path)
	{
		try
		{
			FileReader fr = new FileReader(path);
		
			BufferedReader br = new BufferedReader(fr);
			
			String[] head = br.readLine().split(",");
			
			ArrayList<String> lineas = new ArrayList<>();
			
			while(br.ready())
			{
				lineas.add(br.readLine());
			}
			
			String[][] parsed = new String[lineas.size() + 1][head.length];
			
			for(int i = 0; i < head.length; i++)
			{
				parsed[0][i] = head[i];
			}
			
			for(int i = 1; i < lineas.size() + 1; i++)
			{
				String[] values = lineas.get(i - 1).split(",");
				
				for(int j = 0; j < head.length; j++)
				{
					parsed[i][j] = values[j];
				}
			}
			
			return parsed;
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
