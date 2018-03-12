package CharacterStuff;
import General.Vector2;

public class GameCharacter 
{
	private String name;
	
	private int[] idRange;
	private String[] spriteRange;
	
	private Vector2 pos;
	private Vector2 movement;
	
	private float maxHealth;
	private float currentHealth;
	
	private int[] status = new int[4];
	
	private float attack;
	
	private int effect;
	private int effectAmount;
	
	private float damageReflection;
	
	private float damageAbsortion;
	
	private float damageOnTouch;
	
	
	public float damage(float amount)
	{
		this.currentHealth = this.currentHealth - amount * damageAbsortion;
		
		return amount * damageReflection;
	}
	
	public boolean heal(float amount)
	{
		boolean hasHealed = false;
		
		if(currentHealth < maxHealth)
		{
			currentHealth += amount;
			
			if(currentHealth > maxHealth)
			{
				currentHealth = maxHealth;
			}
			
			hasHealed = true;
		}
		
		return hasHealed;
	}
	
	public void bumpInto(GameCharacter character)
	{
		character.damage(damageOnTouch);
		
		this.damage(character.getDamageOnTouch());
	}
	
	public void setStatus(int effect, int amount)
	{
		status[effect] += amount;
	}
	
	public float getDamageOnTouch()
	{
		return damageOnTouch;
	}
	
	public float getAttack()
	{
		return attack;
	}
	
	public float CurrentHealth()
	{
		return currentHealth;
	}
	
	public float MaxHealth()
	{
		return maxHealth;
	}
	
	public float HealthRatio()
	{
		return currentHealth / maxHealth;
	}
	
	public Vector2 Movement()
	{
		return movement;
	};
	
}
