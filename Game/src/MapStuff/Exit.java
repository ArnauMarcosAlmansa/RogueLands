package MapStuff;
import CharacterStuff.GameCharacter;
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
			//change map
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
