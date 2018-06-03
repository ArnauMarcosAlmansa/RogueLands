package MapStuff;
import CharacterStuff.GameCharacter;

/**
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */

public class Trap extends MapTile 
{
	float damage;
	
	int effect;
	int effectAmount;
	
	@Override
	public void stepInto(GameCharacter character)
	{
		character.damage(damage);
		character.setStatus(effect, effectAmount);
	}
	
	public void setDamage(float newDamage)
	{
		damage = newDamage;
	}
	
	public void setEffect(int newEffect)
	{
		effect = newEffect;
	}
	
	public void setEffectAmount(int newAmount)
	{
		effectAmount = newAmount;
	}
}
