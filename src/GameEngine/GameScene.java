package GameEngine;

import MapStuff.*;

import java.util.ArrayList;

import CharacterStuff.*;
import General.Vector2;
/**
 * Escena de juego. Contiene un GameMap y un ArrayList de enemigos.
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */
public class GameScene
{
	private GameMap currentMap;
	
	private ArrayList<GameCharacter> characters = new ArrayList<GameCharacter>();
	
	private ArrayList<GameCharacter> waitingCharacters = new ArrayList<GameCharacter>();
	
	private boolean clearStage = false;
	
	/**
	 * Método que actualiza la escena cada turno.
	 */
	public void Update()
	{
			if(waitingCharacters.size() > 0)
			{
				characters.addAll(waitingCharacters);
				waitingCharacters.clear();
			}
			
			for(int i = 0; i < characters.size(); i++)
			{
				characters.
					get(i).
						Update();
			}
			
			if(!clearStage && characters.size() == 0)
			{
				currentMap.setClear(true);
				clearStage = true;
				
				currentMap.placeBonfire();
				
				if(Game.instance().world().isClear())
				{
					Game.instance().win();
				}
			}
		
		//System.out.println(Functions.intMatrixToString(currentMap.layout()));
	}
	
	/**
	 * Cambia de mapa.
	 * 
	 * @param newMap GameMap que se quiere establecer como nuevo mapa.
	 */
	public void changeMap(GameMap newMap)
	{
		currentMap = newMap;
		
		currentMap.setVisited(true);
		
		characters.clear();
		
		characters.addAll(currentMap.getNonPlayableCharacters());
		
		clearStage = currentMap.isClear();
	}
	
	/**
	 * Establece un un mapa de juego.
	 * @param map Mapa que se quiere establecer.
	 */
	public void setMap(GameMap map)
	{
		currentMap = map;
		
		clearStage = currentMap.isClear();
	}
	
	/**
	 * 
	 * @return Retorna el GameMap actual.
	 */
	public GameMap currentMap()
	{
		return currentMap;
	}
	
	/**
	 * 
	 * @return Retorna una lista con todos los GameCharacter de la escena.
	 */
	public ArrayList<GameCharacter> characters()
	{
		return characters;
	}
	
	/**
	 * 
	 * @return Retorna el PlayableCharacter que hay en la escena.
	 */
	public PlayableCharacter getPlayableCharacter()
	{
		// TODO Auto-generated method stub
		PlayableCharacter temp = null;
		
		for(GameCharacter c : characters)
		{
			if(c instanceof PlayableCharacter)
			{
				temp = (PlayableCharacter) c;
			}
		}
		
		if(temp != null) return temp;
		
		
		temp = this.currentMap.getPlayableCharacter();
		
		return temp;
	}
	
	/**
	 * Añade un GameCharacter a una lista para ser introducido el siguiente turno.
	 * @param character El GameCharacter que se quiere añadir.
	 */
	public void addCharacterNextTurn(GameCharacter character)
	{
		waitingCharacters.add(character);
	}


}
