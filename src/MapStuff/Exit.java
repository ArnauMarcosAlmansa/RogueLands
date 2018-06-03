package MapStuff;
import CharacterStuff.GameCharacter;
import GameEngine.Game;
import General.Vector2;
/**
 * 
 * Casilla de salida. Para cambiar de mapa.
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */
public class Exit extends MapTile
{
	private Vector2 exitCondition;
	
	/**
	 * 
	 * @param condition Condición del movimiento del jugador para cambiar de mapa.
	 */
	public Exit(Vector2 condition)
	{
		exitCondition = new Vector2();
		exitCondition.set(condition);
	}
	
	@Override
	public void stepInto(GameCharacter character)
	{
		
	}
	
	/**
	 * Método llamado cuando un personaje sale de la casilla.
	 * 
	 * Si el movimiento del personaje es igual a la condición de salida, cambia de mapa.
	 */
	@Override
	public void stepOut(GameCharacter character)
	{
		if(character.Movement().equals(exitCondition))
		{
			int direction = exitCondition.toDirection();
			
			Game.instance().currentScene().currentMap().charactersGrid()[character.pos().row()][character.pos().col()] = null;
			
			GameMap newMap = Game.instance().world().getMapOn(Game.instance().currentScene().currentMap().pos().sum(exitCondition));
			
			Game.instance().currentScene().changeMap(newMap);
		
			if(direction == 0)
			{
				character.setPos(new Vector2(GameMap.MAP_HEIGHT - 1, character.pos().col()));
			}
			else if(direction == 2)
			{
				character.setPos(new Vector2(0, character.pos().col()));
			}
			else if(direction == 1)
			{
				character.setPos(new Vector2(character.pos().row(), 0));
			}
			else if(direction == 3)
			{
				character.setPos(new Vector2(character.pos().row(), GameMap.MAP_WIDTH - 1));
			}
			
			character.Movement().set(0, 0);
			
			Game.instance().currentScene().currentMap().charactersGrid()[character.pos().row()][character.pos().col()] = character;
		
		}
	}
	
	public Vector2 getExitCondition()
	{
		return exitCondition;
	}
	
	public void setExitCondition(Vector2 condition)
	{
		this.exitCondition = condition;
	}
}
