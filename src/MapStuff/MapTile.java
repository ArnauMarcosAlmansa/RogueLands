package MapStuff;
import java.io.Serializable;

import CharacterStuff.GameCharacter;

public class MapTile implements Serializable
{
	/**
	 * Casilla.
	 * 
	 * @since Java 8.0
	 * @version 1.0
	 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
	 */
	//private static final long serialVersionUID = -5334635578500106051L;

	protected int id;
	
	protected boolean isCollider;
	
	public void stepInto(GameCharacter character)
	{
		
	}
	
	public void stepOut(GameCharacter character)
	{
		
	}
	
	public int Id()
	{
		return id;
	}
	
	public void setId(int newId)
	{
		id = newId;
	}
	
	public void setCollider(boolean isColl)
	{
		isCollider = isColl;
	}
	
	public boolean isCollider()
	{
		return isCollider;
	}
}
