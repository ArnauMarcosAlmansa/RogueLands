package MapStuff;
import CharacterStuff.GameCharacter;

public class MapTile 
{
	protected int id;
	
	protected String image;
	
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
	
	public void setImage(String newImage)
	{
		image = newImage;
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
