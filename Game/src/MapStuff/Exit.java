package MapStuff;
import CharacterStuff.GameCharacter;
import GameEngine.Game;
import General.Vector2;

public class Exit extends MapTile
{
	private String mapTo;
	
	private Vector2 exitCondition;
	
	public Exit(String nextMap, Vector2 condition)
	{
		this.mapTo = new String();
		mapTo.concat(nextMap);
		
		exitCondition = new Vector2();
		exitCondition.set(condition);
	}
	
	public Exit()
	{
		
	}
	
	@Override
	public void stepInto(GameCharacter character)
	{
		
	}
	
	@Override
	public void stepOut(GameCharacter character)
	{
		if(character.Movement().equals(exitCondition))
		{
			int direction = exitCondition.toDirection();
			
			Game.instance().currentScene().changeMap(Game.instance().currentScene().aroundMaps()[direction]);
		
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
						
			Game.instance().currentScene().currentMap().charactersGrid()[character.pos().row()][character.pos().col()] = character;
		
			
		}
	}
	
	public String getNextMap()
	{
		return mapTo;
	}
	
	public Vector2 getExitCondition()
	{
		return exitCondition;
	}
	
	public void setNextMap(String mapName)
	{
		this.mapTo = mapName;
	}
	
	public void setExitCondition(Vector2 condition)
	{
		this.exitCondition = condition;
	}
}
