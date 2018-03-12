package MapStuff;
import CharacterStuff.GameCharacter;

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
