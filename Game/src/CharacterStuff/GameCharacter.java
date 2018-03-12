package CharacterStuff;
import GameEngine.Game;
import General.Vector2;

public class GameCharacter 
{
	protected String name;
	
	protected int[] idRange;
	protected int currentId;
	protected String[] spriteRange;
	
	protected Vector2 pos;
	protected Vector2 movement;
	
	protected float maxHealth;
	protected float currentHealth;
	
	protected int[] status = new int[4];
	
	protected float attack;
	
	protected int effect;
	protected int effectAmount;
	
	protected float damageReflection;
	
	protected float damageAbsortion;
	
	protected float damageOnTouch;
	
	
	public void Update()
	{
		
	}
	
	
	public float damage(float amount)
	{
		this.currentHealth = this.currentHealth - amount * damageAbsortion;
		
		if(this.currentHealth <= 0)
		{
			die();
		}
		
		return amount * damageReflection;
	}
	
	private void die()
	{
		Game.instance().currentScene().characters().remove(this);
		
		Game.instance().currentScene().currentMap().charactersGrid()[pos.row()][pos.col()] = null;
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
	
	public Vector2 pos()
	{
		return pos;
	}
	
	public void setPos(Vector2 newPos)
	{
		pos = newPos;
	}
	
	public Vector2 Movement()
	{
		return movement;
	};
	
	public int currentId()
	{
		return currentId;
	}
}
